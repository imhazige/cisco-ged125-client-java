package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.FailureConfMessage;

public class FailureConfMessageParser extends AbstractMessageParser<FailureConfMessage> {

	@Override
	protected FailureConfMessage parseFixPart() {
		FailureConfMessage rmsg = new FailureConfMessage();

		rmsg.setInvokeId(readUint());
		rmsg.setStatus(readUint());

		return rmsg;
	}

	@Override
	protected void onFloatField(FailureConfMessage msg, int tag, byte[] value) {

	}

}
