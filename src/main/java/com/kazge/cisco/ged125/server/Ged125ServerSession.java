package com.kazge.cisco.ged125.server;

import com.kazge.cisco.ged125.message.Ged125Message;

public interface Ged125ServerSession {
	public String getId();

	public void onMessage(Ged125Message msg);
	public void sendMessage(Ged125Message msg);

	public void onChannelException(Throwable cause);
}
