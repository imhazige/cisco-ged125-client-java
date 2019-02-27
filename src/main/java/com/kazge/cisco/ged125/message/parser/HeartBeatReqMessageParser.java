package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.request.HeartBeatReqMessage;

public class HeartBeatReqMessageParser extends AbstractMessageParser<HeartBeatReqMessage> {
	@Override
	protected HeartBeatReqMessage parseFixPart() {
		HeartBeatReqMessage msg = new HeartBeatReqMessage();

		msg.setInvokeId(readUint());

		return msg;
	}

	@Override
	protected void onFloatField(HeartBeatReqMessage msg, int tag, byte[] value) {

	}

}
