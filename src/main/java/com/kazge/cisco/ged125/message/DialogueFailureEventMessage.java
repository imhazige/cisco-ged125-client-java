package com.kazge.cisco.ged125.message;

public class DialogueFailureEventMessage extends ServiceCtrlMessage{
	private long errorCode;

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_DIALOGUE_FAILURE_EVENT;
	}

}
