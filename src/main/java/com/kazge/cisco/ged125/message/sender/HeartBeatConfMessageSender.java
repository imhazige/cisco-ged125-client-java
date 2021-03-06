package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.response.HeartBeatConfMessage;

public class HeartBeatConfMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message message) {
		HeartBeatConfMessage msg = (HeartBeatConfMessage)message;
		
		addUint(message.getInvokeId());
	}

}
