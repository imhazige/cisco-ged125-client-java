package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.HeartBeatConfMessage;

public class HeartBeatConfMessageParser extends AbstractMessageParser<HeartBeatConfMessage> {
	@Override
	protected HeartBeatConfMessage parseFixPart() {
		HeartBeatConfMessage message = new HeartBeatConfMessage();

		message.setInvokeId(readUint());

		return message;
	}

	@Override
	protected void onFloatField(HeartBeatConfMessage msg, int tag, byte[] value) {

	}

}
