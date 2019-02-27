package com.kazge.cisco.ged125.message.response.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class InitServiceCtrlEndMessage extends ServiceCtrlMessage {

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_INIT_SERVICE_CTRL_END;
	}

}
