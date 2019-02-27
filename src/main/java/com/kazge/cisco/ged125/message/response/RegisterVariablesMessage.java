package com.kazge.cisco.ged125.message.response;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.EccName;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.common.BitUtils;

public class RegisterVariablesMessage extends ResponseMessage {

	private int callVarsMask;
	private Map<String, EccName> eccTags;

	public RegisterVariablesMessage() {
		callVarsMask = (int) BitUtils.set(MessageEnum.MASK_CALL_VARS_Call_Variable_1,
				MessageEnum.MASK_CALL_VARS_Call_Variable_2, MessageEnum.MASK_CALL_VARS_Call_Variable_3,
				MessageEnum.MASK_CALL_VARS_Call_Variable_4, MessageEnum.MASK_CALL_VARS_Call_Variable_5,
				MessageEnum.MASK_CALL_VARS_Call_Variable_6, MessageEnum.MASK_CALL_VARS_Call_Variable_7,
				MessageEnum.MASK_CALL_VARS_Call_Variable_8, MessageEnum.MASK_CALL_VARS_Call_Variable_9,
				MessageEnum.MASK_CALL_VARS_Call_Variable_10);
	}

	public int getCallVarsMask() {
		return callVarsMask;
	}

	public void setCallVarsMask(int callVarsMask) {
		this.callVarsMask = callVarsMask;
	}

	public Map<String, EccName> getEccTags() {
		if (null == eccTags) {
			eccTags = new HashMap<String, EccName>();
		}
		return eccTags;
	}

	@Override
	public long getMessageType() {
		return MessageEnum.MessageType_RegisterVariables;
	}

}
