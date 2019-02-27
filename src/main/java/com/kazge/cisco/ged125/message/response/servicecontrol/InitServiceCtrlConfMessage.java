package com.kazge.cisco.ged125.message.response.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class InitServiceCtrlConfMessage extends ServiceCtrlMessage {

	@Override
	public long getMessageSubType() {
		return 2;
	}

}
