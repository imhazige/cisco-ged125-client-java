package com.kazge.cisco.ged125.message.response.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class InitServiceCtrlDataMessage extends ServiceCtrlMessage {
	private long serviceFeatures;
	
	public long getServiceFeatures() {
		return serviceFeatures;
	}

	public void setServiceFeatures(long serviceFeatures) {
		this.serviceFeatures = serviceFeatures;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_INIT_SERVICE_CTRL_DATA;
	}

}
