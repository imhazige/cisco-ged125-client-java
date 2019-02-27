package com.kazge.cisco.ged125.message.request.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class ReleaseMessage extends ServiceCtrlMessage {
	private long cause;
	
	public long getCause() {
		return cause;
	}

	public void setCause(long cause) {
		this.cause = cause;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_RELEASE;
	}

}
