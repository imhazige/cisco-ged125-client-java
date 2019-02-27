package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.response.CloseConfMessage;

public class CloseConfMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message message) {
		CloseConfMessage msg = (CloseConfMessage)message;
		
		addUint(message.getInvokeId());
		
	}

}
