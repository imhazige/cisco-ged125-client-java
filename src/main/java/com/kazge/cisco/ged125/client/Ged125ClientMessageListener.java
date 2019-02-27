package com.kazge.cisco.ged125.client;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.response.OpenConfMessage;

public interface Ged125ClientMessageListener {
	void onMessage(Ged125Message message);
	void onOpen(OpenConfMessage message);
	void onClose();
}
