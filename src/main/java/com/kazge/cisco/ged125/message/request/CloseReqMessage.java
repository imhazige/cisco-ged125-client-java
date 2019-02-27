package com.kazge.cisco.ged125.message.request;

public class CloseReqMessage extends RequestMessage{
	private long status;
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	@Override
	public long getMessageType() {
		return 7;
	}

}
