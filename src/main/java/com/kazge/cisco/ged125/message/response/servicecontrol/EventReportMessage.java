package com.kazge.cisco.ged125.message.response.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class EventReportMessage extends ServiceCtrlMessage {
	private long eventId;
	
	//float part
	private Long causeCode;

	public Long getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(Long causeCode) {
		this.causeCode = causeCode;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_EVENT_REPORT;
	}

}
