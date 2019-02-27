package com.kazge.cisco.ged125.message;

public abstract class ServiceCtrlMessage extends Ged125Message {
	private ServiceControlHeader serviceControlHeader;
	
	public ServiceControlHeader getServiceControlHeader() {
		if (null == serviceControlHeader){
			serviceControlHeader = new ServiceControlHeader();
			serviceControlHeader.setMessageSubType(getMessageSubType());
		}
		
		return serviceControlHeader;
	}

	public abstract long getMessageSubType();

	public static class ServiceControlHeader {
		private long messageSubType;
		private long dialogueID;
		private long sendSeqNo;
		
		private void setMessageSubType(long messageSubType) {
			this.messageSubType = messageSubType;
		}

		public void setDialogueID(long dialogueID) {
			this.dialogueID = dialogueID;
		}

		public void setSendSeqNo(long sendSeqNo) {
			this.sendSeqNo = sendSeqNo;
		}

		public long getMessageSubType() {
			return messageSubType;
		}

		public long getDialogueID() {
			return dialogueID;
		}

		public long getSendSeqNo() {
			return sendSeqNo;
		}
	}



	@Override
	public long getMessageType() {
		return 47;
	}
}
