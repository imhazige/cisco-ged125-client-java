package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlDataMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class InitServiceCtrlDataMessageSenderTool implements ServiceCtrlMessageSenderTool {

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		InitServiceCtrlDataMessage smsg = (InitServiceCtrlDataMessage)msg;
		
		sender.addUint(msg.getInvokeId());
		sender.addUint(smsg.getServiceFeatures());
	}

}
