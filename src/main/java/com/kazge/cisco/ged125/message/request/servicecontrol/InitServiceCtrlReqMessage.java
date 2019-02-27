package com.kazge.cisco.ged125.message.request.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class InitServiceCtrlReqMessage extends ServiceCtrlMessage {
	@Override
	public long getMessageSubType() {
		return 1;
	}

}
