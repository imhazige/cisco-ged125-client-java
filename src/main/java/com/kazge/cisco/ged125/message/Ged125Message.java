package com.kazge.cisco.ged125.message;

public abstract class Ged125Message {
	public abstract long getMessageType();
	private String classType;
	
	private long invokeId = MessageEnum.NULL_DATA_ELEMENT;

	public long getInvokeId() {
		return invokeId;
	}

	public void setInvokeId(long invokeId) {
		this.invokeId = invokeId;
	}
	
	public String getClassType(){
		if (null == classType){
			classType = getClass().getSimpleName();
		}
		return classType;
	}
}
