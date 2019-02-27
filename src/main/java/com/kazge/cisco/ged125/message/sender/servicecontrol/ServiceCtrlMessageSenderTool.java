package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public interface ServiceCtrlMessageSenderTool {

	void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg);

}
