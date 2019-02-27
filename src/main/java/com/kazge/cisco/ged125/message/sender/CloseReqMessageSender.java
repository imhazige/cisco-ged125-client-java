package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.request.CloseReqMessage;

public class CloseReqMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message requestMessage) {
		CloseReqMessage message = (CloseReqMessage)requestMessage;
		
		addUint(message.getInvokeId());
		//status
		addUint(message.getStatus());
	}

}
