package com.kazge.cisco.ged125.client;

import java.util.List;
import java.util.concurrent.Executors;

import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.request.CloseReqMessage;
import com.kazge.cisco.ged125.message.request.OpenReqMessage;
import com.kazge.cisco.ged125.message.response.CloseConfMessage;
import com.kazge.cisco.ged125.message.response.OpenConfMessage;
import com.kazge.cisco.ged125.message.socket.Ged125MessageChannel;
import com.kazge.common.Log;
import com.kazge.common.StringUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

public class Ged125SessionClient implements Runnable {
	private Ged125MessageChannel channel = null;
	private String id;
	private Ged125ClientMessageListener listener;
	private String server;
	private int port;
	private int version;
	private int idelTimeout;

	public String getId() {
		return id;
	}

	public Ged125SessionClient(String aserver, int aport, int aversion, int aidelTimeout) {
		id = StringUtils.uniqueString("");
		server = aserver;
		port = aport;
		version = aversion;
		idelTimeout = aidelTimeout;
		if (StringUtils.isBlank(server)) {
			throw new RuntimeException("invalid server.");
		}
		if (port <= 0) {
			throw new RuntimeException("invalid port.");
		}
		if (version <= 0) {
			throw new RuntimeException("invalid version.");
		}
	}

	public synchronized void open(Ged125ClientMessageListener alistener) {
		listener = alistener;
		Executors.newSingleThreadExecutor().execute(this);
	}

	public synchronized void close() {
		Log.debug("session " + getId() + " Closed.");
		if (null == channel || channel.isClosed()) {
			return;
		}

		CloseReqMessage cm = new CloseReqMessage();
		cm.setInvokeId(channel.getNextInvokedId());
		cm.setStatus(0);
		sendMessage(cm);
		clearClose();
	}

	private void clearClose() {
		if (null != listener) {
			listener.onClose();
		}
		listener = null;
		if (null == channel || channel.isClosed()) {
			return;
		}
		channel.close();
		channel = null;
	}

	public void sendMessage(Ged125Message requestMessage) {
		if (null == channel || channel.isClosed()) {
			return;
		}
		channel.sendMessage(requestMessage);
	}

	private void handleMessage(Ged125Message rmsg) {
		if (rmsg instanceof CloseConfMessage) {
			clearClose();
		} else if (rmsg instanceof OpenConfMessage) {
			if (null != listener) {
				listener.onOpen((OpenConfMessage) rmsg);
			}
		} else {
			if (null != listener) {
				listener.onMessage(rmsg);
			}
		}
	}

	private void sendOpenReq() {
		OpenReqMessage openMsg = new OpenReqMessage();

		openMsg.setInvokeId(channel.getNextInvokedId());
		// version
		openMsg.setVersion(version);
		// IdleTimeout:uint
		// addUint(30);
		openMsg.setIdleTimeout(idelTimeout);

		channel.sendMessage(openMsg);
	}

	@Override
	public void run() {

		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.option(ChannelOption.SO_LINGER, 0);
			b.option(ChannelOption.TCP_NODELAY, false);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ByteToMessageDecoder() {
						private int gedLength = -1;
						private int gedType = -1;

						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
							super.exceptionCaught(ctx, cause);
							reset();
						}

						@Override
						protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
								throws Exception {
							consume(ctx, in);
						}

						@Override
						public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
							super.handlerAdded(ctx);
							channel = new Ged125MessageChannel(ctx);
						}

						@Override
						protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
							super.handlerRemoved0(ctx);
							channel.close();
							reset();
						}

						private void consume(ChannelHandlerContext ctx, ByteBuf in) {
							if (gedLength < 0) {
								// need read length
								if (in.readableBytes() < 8) {
									return; // (3)
								}
								byte[] bs = new byte[8];
								in.readBytes(bs);
								gedLength = (int) Ged125DataUtils.bytes2Uint(bs, 0);
								gedType = (int) Ged125DataUtils.bytes2Uint(bs, 4);
							}

							if (in.readableBytes() < gedLength) {
								return;
							}

							// msg body
							byte[] data = new byte[gedLength];
							in.readBytes(data);

							Ged125Message ged125Message = channel.readMessage(gedType, data);

							handleMessage(ged125Message);

							reset();

							// the case that have more readable message
							consume(ctx, in);
						}

						private void reset() {
							gedLength = -1;
							gedType = -1;
						}
					});
				}
			});

			// Start the client.
			ChannelFuture f = b.connect(server, port).sync(); // (5)

			sendOpenReq();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} catch (Exception ex) {

		} finally {
			workerGroup.shutdownGracefully();
		}

	}

}
