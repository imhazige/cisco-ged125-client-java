package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.request.HeartBeatReqMessage;

public class HeartBeatReqMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message requestMessage) {
		HeartBeatReqMessage message = (HeartBeatReqMessage)requestMessage;
		
		addUint(message.getInvokeId());
	}

}
