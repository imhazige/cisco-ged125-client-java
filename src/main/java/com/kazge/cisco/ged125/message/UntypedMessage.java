package com.kazge.cisco.ged125.message;

public class UntypedMessage extends Ged125Message{
	private long type;

	public UntypedMessage(long at) {
		type = at;
	}
	
	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	@Override
	public long getMessageType() {
		return type;
	}
}
