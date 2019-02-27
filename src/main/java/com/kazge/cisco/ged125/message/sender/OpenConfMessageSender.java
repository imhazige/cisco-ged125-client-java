package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.response.OpenConfMessage;

public class OpenConfMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message message) {
		OpenConfMessage msg = (OpenConfMessage)message;
		
		addUint(msg.getInvokeId());
		
		addBoolean(msg.isUseEventFeed());
		addBoolean(msg.isUsePolledFeed());
		addBoolean(msg.isUseCallRouting());
		addBoolean(msg.isUseTimeSynch());
		addBoolean(msg.isUseServiceControl());
	}

}
