package com.kazge.cisco.ged125.message.response;


public class FailureConfMessage extends ResponseMessage{
	private long status;

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	@Override
	public long getMessageType() {
		return 1;
	}
}
