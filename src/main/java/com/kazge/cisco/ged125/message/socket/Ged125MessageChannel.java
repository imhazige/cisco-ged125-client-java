package com.kazge.cisco.ged125.message.socket;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.UntypedMessage;
import com.kazge.cisco.ged125.message.parser.AbstractMessageParser;
import com.kazge.cisco.ged125.message.parser.CloseConfMessageParser;
import com.kazge.cisco.ged125.message.parser.CloseReqMessageParser;
import com.kazge.cisco.ged125.message.parser.FailureConfMessageParser;
import com.kazge.cisco.ged125.message.parser.FailureEventMessageParser;
import com.kazge.cisco.ged125.message.parser.HeartBeatConfMessageParser;
import com.kazge.cisco.ged125.message.parser.HeartBeatReqMessageParser;
import com.kazge.cisco.ged125.message.parser.OpenConfMessageParser;
import com.kazge.cisco.ged125.message.parser.OpenReqMessageParser;
import com.kazge.cisco.ged125.message.parser.RegisterVariablesMessageParser;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.sender.AbstractMessageSender;
import com.kazge.cisco.ged125.message.sender.CloseConfMessageSender;
import com.kazge.cisco.ged125.message.sender.CloseReqMessageSender;
import com.kazge.cisco.ged125.message.sender.HeartBeatConfMessageSender;
import com.kazge.cisco.ged125.message.sender.HeartBeatReqMessageSender;
import com.kazge.cisco.ged125.message.sender.OpenConfMessageSender;
import com.kazge.cisco.ged125.message.sender.OpenReqMessageSender;
import com.kazge.cisco.ged125.message.sender.RegisterVariablesMessageSender;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;
import com.com.kazge.common.midware.common.ExceptionUtils;
import com.com.kazge.common.midware.common.Log;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Ged125MessageChannel {
	private long lastInvokeId = 0;
	@SuppressWarnings("rawtypes")
	private static final Map<Long, Class<? extends AbstractMessageParser>> parsers = new HashMap<Long, Class<? extends AbstractMessageParser>>();
	private static final Map<Long, Class<? extends AbstractMessageSender>> senders = new HashMap<Long, Class<? extends AbstractMessageSender>>();

	private boolean closed;
	private ChannelHandlerContext channelHandlerContext;

	public Ged125MessageChannel(ChannelHandlerContext ctx) {
		channelHandlerContext = ctx;
	}

	public void close() {
		closed = true;
		channelHandlerContext.close();
		channelHandlerContext = null;
	}
	
	public SocketAddress getRemoteAddress(){
		if (null == channelHandlerContext){
			return null;
		}
		return channelHandlerContext.channel().remoteAddress();
	}

	public boolean isClosed() {
		return closed;
	}

	public Ged125Message readMessage(long type, byte[] data) {
		try {
			@SuppressWarnings("rawtypes")
			Class<? extends AbstractMessageParser> parser = parsers.get(type);

			if (null == parser) {
				Ged125Message msg = new UntypedMessage(type);

				return msg;
			}

			Ged125Message msg = null;

			msg = parser.newInstance().parse(data);
			
			return msg;
		} catch (Exception e) {
			Log.error("read message type %s error.", type);
			throw ExceptionUtils.silence(e);
		}
	}

	static {
		senders.put(3L, OpenReqMessageSender.class);
		senders.put(4L, OpenConfMessageSender.class);
		senders.put(5L, HeartBeatReqMessageSender.class);
		senders.put(6L, HeartBeatConfMessageSender.class);
		senders.put(7L, CloseReqMessageSender.class);
		senders.put(MessageEnum.MessageType_CloseConf, CloseConfMessageSender.class);
		senders.put(47L, ServiceCtrlMessageSender.class);
		senders.put(MessageEnum.MessageType_RegisterVariables, RegisterVariablesMessageSender.class);

		parsers.put(1L, FailureConfMessageParser.class);
		parsers.put(2L, FailureEventMessageParser.class);
		parsers.put(3L, OpenReqMessageParser.class);
		parsers.put(4L, OpenConfMessageParser.class);
		parsers.put(5L, HeartBeatReqMessageParser.class);
		parsers.put(6L, HeartBeatConfMessageParser.class);
		parsers.put(7L, CloseReqMessageParser.class);
		parsers.put(MessageEnum.MessageType_CloseConf, CloseConfMessageParser.class);
		parsers.put(47L, ServiceCtrlMessageParser.class);
		parsers.put(MessageEnum.MessageType_RegisterVariables, RegisterVariablesMessageParser.class);

	}

	public void sendMessage(Ged125Message msg) {
		Class<? extends AbstractMessageSender> sender = senders.get(msg.getMessageType());
		if (null == sender) {
			throw new RuntimeException("unimplemented sender for message type " + msg.getMessageType());
		}
		try {
			if (MessageEnum.NULL_DATA_ELEMENT == msg.getInvokeId()) {
				msg.setInvokeId(getNextInvokedId());
			}
			updateLastInvokeId(msg.getInvokeId());
			// Log.debug("sending msg %s",JacksonUtils.toJsonString(msg));
			sender.newInstance().sendMessage(this, msg);
		} catch (Exception e) {
			throw ExceptionUtils.silence(e);
		}
	}

	public int sendMessage(List<byte[]> data) {
		if (null == data || data.isEmpty()) {
			return 0;
		}
		try {
			int len = 0;
			for (int i = 0; i < data.size(); i++) {
				byte[] bs = data.get(i);
				len += bs.length;
			}
			ByteBuf bf = channelHandlerContext.alloc().buffer(len);
			for (int i = 0; i < data.size(); i++) {
				byte[] bs = data.get(i);
				bf.writeBytes(bs);
			}
			ChannelFuture cf = channelHandlerContext.writeAndFlush(bf);
			cf.addListener(new GenericFutureListener<Future<? super Void>>() {

				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if(future.isSuccess()) {
	                    System.out.println("Wrote message on active");
	                }else{
	                	Throwable t = future.cause();
	                	Log.error("error when send message:%s" , t.getMessage());
	                }
				}
				
			});
			return len;
		} catch (Exception e) {
			throw ExceptionUtils.silence(e);
		}
	}

	public void updateLastInvokeId(long id) {
		if (lastInvokeId < id) {
			lastInvokeId = id;
		}
	}

	public long getNextInvokedId() {
		long id = lastInvokeId;
		if (id == Long.MAX_VALUE || id == 0) {
			id = 1;
		} else {
			id++;
		}
		return id;
	}
}
