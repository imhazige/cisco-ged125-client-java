package com.kazge.cisco.ged125.message.request;

public class OpenReqMessage extends RequestMessage{
	private long version;
	private long idleTimeout;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(long idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	@Override
	public long getMessageType() {
		return 3;
	}
	
	
}
