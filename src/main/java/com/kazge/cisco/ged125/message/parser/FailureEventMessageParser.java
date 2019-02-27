package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.FailureEventMessage;

public class FailureEventMessageParser extends AbstractMessageParser<FailureEventMessage> {

	@Override
	protected FailureEventMessage parseFixPart() {
		FailureEventMessage rmsg = new FailureEventMessage();

		rmsg.setStatus(readUint());

		return rmsg;
	}

	@Override
	protected void onFloatField(FailureEventMessage msg, int tag, byte[] value) {

	}

}
