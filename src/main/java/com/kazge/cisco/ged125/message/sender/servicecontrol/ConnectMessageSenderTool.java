package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.ConnectMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class ConnectMessageSenderTool implements ServiceCtrlMessageSenderTool{

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		ConnectMessage rmsg = (ConnectMessage)msg;
		
		sender.addUint(rmsg.getLabelType());
		
		//float part
		sender.addStringF(MessageEnum.TAG_Label,rmsg.getLabel());
		
		//TODO other part
	}

}
