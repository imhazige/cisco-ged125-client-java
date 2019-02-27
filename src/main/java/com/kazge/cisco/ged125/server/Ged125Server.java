package com.kazge.cisco.ged125.server;

import java.util.concurrent.atomic.AtomicBoolean;

import com.kazge.common.ExceptionUtils;
import com.kazge.common.Log;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Ged125Server {
	private AtomicBoolean started;
	private final String name;
	private Ged125ServerSessionManager sessionManager;
	private ServerBootstrap b;
	private ChannelFuture f;
	private int port;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private int maxConnections;
	private int coreThreadCount;
	private int bufferSize;

	public int getPort() {
		return port;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public int getCoreThreadCount() {
		return coreThreadCount;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public Ged125ServerSessionManager getSessionManager() {
		return sessionManager;
	}

	public Ged125Server(Ged125ServerSessionManager manager, String name, int port) {
		this(manager, name, port, -1, -1, -1);
	}

	/**
	 * 
	 * @param manager
	 * @param name
	 * @param port
	 * @param coreThreadCount worker thread count valie less than 1 will ignore and
	 *                        use default
	 * @param maxConnections  reserved parameter, have no use for now
	 * @param bufferSize
	 */
	public Ged125Server(Ged125ServerSessionManager manager, String name, int port, int coreThreadCount,
			int maxConnections, int bufferSize) {
		this.name = name;
		this.port = port;
		this.started = new AtomicBoolean(false);
		sessionManager = manager;
		this.coreThreadCount = coreThreadCount;
		this.maxConnections = maxConnections;
		this.bufferSize = bufferSize;
	}

	public void start() {
		boolean hasStarted = started.compareAndSet(false, true);
		if (!hasStarted)
			throw new IllegalStateException("Server has been already started");

		Log.info("Starting the server %s at port %s", getName(), getPort());
		bossGroup = new NioEventLoopGroup(); // (1)
		if (0 < coreThreadCount) {
			workerGroup = new NioEventLoopGroup(coreThreadCount);
		} else {
			workerGroup = new NioEventLoopGroup();
		}

		try {
			b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new Ged125ServerHandler(sessionManager));
						}
					});

			// http://stackoverflow.com/questions/14075688/what-does-channeloption-so-backlog-do
			// this is not the max connection
			b.option(ChannelOption.SO_BACKLOG, 128); // (5)
			// ged125 required
			// but if set, the balancer heartbeat check will make many many error, so do not
			// set
			// b.childOption(ChannelOption.SO_LINGER, 0);
			// b.childOption(ChannelOption.TCP_NODELAY, false);
			// //other
			// b.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
			// if (0 < bufferSize){
			// b.childOption(ChannelOption.SO_SNDBUF, bufferSize);
			// b.childOption(ChannelOption.SO_RCVBUF, bufferSize);
			// }
			// Bind and start to accept incoming connections.
			f = b.bind(port).sync(); // (7)
			Log.info("Started the server %s at port %s", getName(), getPort());

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} catch (Exception ex) {
			throw ExceptionUtils.silence(ex);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public String getName() {
		return name;
	}

	public boolean isStarted() {
		return started.get();
	}
}
