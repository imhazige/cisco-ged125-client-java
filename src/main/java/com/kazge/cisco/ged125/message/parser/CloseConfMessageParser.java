package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.CloseConfMessage;

public class CloseConfMessageParser extends AbstractMessageParser<CloseConfMessage> {

	@Override
	protected CloseConfMessage parseFixPart() {
		CloseConfMessage msg = new CloseConfMessage();

		msg.setInvokeId(readUint());

		return msg;
	}

	@Override
	protected void onFloatField(CloseConfMessage msg, int tag, byte[] value) {

	}

}
