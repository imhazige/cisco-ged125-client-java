package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.request.CloseReqMessage;

public class CloseReqMessageParser extends AbstractMessageParser<CloseReqMessage> {
	@Override
	protected CloseReqMessage parseFixPart() {
		CloseReqMessage msg = new CloseReqMessage();

		msg.setInvokeId(readUint());

		return msg;
	}

	@Override
	protected void onFloatField(CloseReqMessage msg, int tag, byte[] value) {

	}

}
