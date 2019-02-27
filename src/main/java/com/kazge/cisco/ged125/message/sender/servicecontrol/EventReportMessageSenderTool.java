package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.EventReportMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class EventReportMessageSenderTool implements ServiceCtrlMessageSenderTool{

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		EventReportMessage rmsg = (EventReportMessage)msg;
		sender.addUint(rmsg.getEventId());
	}

}
