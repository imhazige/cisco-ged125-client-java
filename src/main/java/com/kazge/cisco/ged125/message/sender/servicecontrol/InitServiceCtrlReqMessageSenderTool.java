package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class InitServiceCtrlReqMessageSenderTool implements ServiceCtrlMessageSenderTool {

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		sender.addUint(msg.getInvokeId());
	}


}
