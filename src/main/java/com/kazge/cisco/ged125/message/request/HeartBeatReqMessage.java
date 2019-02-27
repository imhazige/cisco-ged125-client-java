package com.kazge.cisco.ged125.message.request;

public class HeartBeatReqMessage extends RequestMessage{

	@Override
	public long getMessageType() {
		return 5;
	}

}
