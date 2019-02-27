package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.OpenConfMessage;

public class OpenConfMessageParser extends AbstractMessageParser<OpenConfMessage> {
	@Override
	protected OpenConfMessage parseFixPart() {
		OpenConfMessage rmsg = new OpenConfMessage();

		rmsg.setInvokeId(readUint());

		rmsg.setUseEventFeed(readBoolean());
		rmsg.setUsePolledFeed(readBoolean());
		rmsg.setUseCallRouting(readBoolean());
		rmsg.setUseTimeSynch(readBoolean());
		rmsg.setUseServiceControl(readBoolean());

		return rmsg;
	}

	@Override
	protected void onFloatField(OpenConfMessage msg, int tag, byte[] value) {

	}
}
