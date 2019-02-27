package com.kazge.cisco.ged125.server;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.socket.Ged125MessageChannel;
import com.com.kazge.common.midware.common.Log;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class Ged125ServerHandler extends ByteToMessageDecoder {
	private int gedLength = -1;
	private int gedType = -1;
	private Ged125ServerSessionManager sessionManager;
	private Ged125MessageChannel ged125Channel;
	private Ged125ServerSession ged125Session;

	public Ged125ServerHandler(Ged125ServerSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		reset();
		if (null != ged125Session){
			ged125Session.onChannelException(cause);
		}else if (cause instanceof SocketException){
			//socket exception are normal
//			Log.debug("SocketException:" + cause.getMessage());
			if ("Connection reset by peer".equalsIgnoreCase(cause.getMessage())){
				//do nothing, heartbeat check from balancer will throw this error
			}else{
				Log.debug("SocketException:" +  cause.getMessage());
			}
		} else if (cause instanceof IOException){
			//io exception is normal
			if ("Connection reset by peer".equalsIgnoreCase(cause.getMessage())){
				//do nothing, heartbeat check from balancer will throw this error
			}else{
				Log.debug("IOException:" +  cause.getMessage());
			}
		} else{
			super.exceptionCaught(ctx, cause);
		}
//		Log.error(new RuntimeException(cause),"exceptionCaught-----------");
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		consume(ctx, in);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
	}

	@Override
	protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved0(ctx);
		
		if (null != ged125Channel){
			sessionManager.remove(ged125Session.getId());
			ged125Channel.close();
			reset();
		}
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
		
		//only create real session when we know it is from PG and it is GED125 data
		if (null == ged125Channel){
			ged125Channel = new Ged125MessageChannel(ctx);

			ged125Session = sessionManager.create(ged125Channel);
		}
		
		Ged125Message ged125Message = ged125Channel.readMessage(gedType,data);
		
		ged125Session.onMessage(ged125Message);

		reset();

		// the case that have more readable message
		consume(ctx, in);
	}

	private void reset() {
		gedLength = -1;
		gedType = -1;
	}
}
