package com.kazge.cisco.ged125.message.sender;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.request.OpenReqMessage;

public class OpenReqMessageSender extends AbstractMessageSender{
	@Override
	protected void prepareData(Ged125Message requestMessage) {
		OpenReqMessage message = (OpenReqMessage)requestMessage;
		
		addUint(requestMessage.getInvokeId());
		//version
		addUint(message.getVersion());
		//IdleTimeout:uint
		addUint(message.getIdleTimeout());
	}

}
