package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.request.OpenReqMessage;

public class OpenReqMessageParser extends AbstractMessageParser<OpenReqMessage>{

	@Override
	protected OpenReqMessage parseFixPart() {
		OpenReqMessage msg = new OpenReqMessage();
		
		msg.setInvokeId(readUint());

		msg.setVersion(readUint());
		msg.setIdleTimeout(readUint());
		
		return msg;
	}

	@Override
	protected void onFloatField(OpenReqMessage msg, int tag, byte[] value) {
		
	}

}
