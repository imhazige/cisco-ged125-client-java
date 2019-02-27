package com.kazge.cisco.ged125.message.response;

import com.kazge.cisco.ged125.message.MessageEnum;

public class CloseConfMessage extends ResponseMessage{

	@Override
	public long getMessageType() {
		return MessageEnum.MessageType_CloseConf;
	}

}
