package com.kazge.cisco.ged125.message.response.servicecontrol;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;

public class RunScriptResultMessage extends ServiceCtrlMessage {
	private boolean resultCode;

	// float
	private String ced;
	private Boolean newTransaction;

	private String callVariable1;
	private String callVariable2;
	private String callVariable3;
	private String callVariable4;
	private String callVariable5;
	private String callVariable6;
	private String callVariable7;
	private String callVariable8;
	private String callVariable9;
	private String callVariable10;

	private Map<Long, String> eccValueVariables;
	private Map<Long, Map<Integer, String>> eccArrayVariables;

	public boolean isResultCode() {
		return resultCode;
	}

	public void setResultCode(boolean resultCode) {
		this.resultCode = resultCode;
	}

	public String getCed() {
		return ced;
	}

	public void setCed(String ced) {
		this.ced = ced;
	}

	public Boolean getNewTransaction() {
		return newTransaction;
	}

	public void setNewTransaction(Boolean newTransaction) {
		this.newTransaction = newTransaction;
	}

	public String getCallVariable1() {
		return callVariable1;
	}

	public void setCallVariable1(String callVariable1) {
		this.callVariable1 = callVariable1;
	}

	public String getCallVariable2() {
		return callVariable2;
	}

	public void setCallVariable2(String callVariable2) {
		this.callVariable2 = callVariable2;
	}

	public String getCallVariable3() {
		return callVariable3;
	}

	public void setCallVariable3(String callVariable3) {
		this.callVariable3 = callVariable3;
	}

	public String getCallVariable4() {
		return callVariable4;
	}

	public void setCallVariable4(String callVariable4) {
		this.callVariable4 = callVariable4;
	}

	public String getCallVariable5() {
		return callVariable5;
	}

	public void setCallVariable5(String callVariable5) {
		this.callVariable5 = callVariable5;
	}

	public String getCallVariable6() {
		return callVariable6;
	}

	public void setCallVariable6(String callVariable6) {
		this.callVariable6 = callVariable6;
	}

	public String getCallVariable7() {
		return callVariable7;
	}

	public void setCallVariable7(String callVariable7) {
		this.callVariable7 = callVariable7;
	}

	public String getCallVariable8() {
		return callVariable8;
	}

	public void setCallVariable8(String callVariable8) {
		this.callVariable8 = callVariable8;
	}

	public String getCallVariable9() {
		return callVariable9;
	}

	public void setCallVariable9(String callVariable9) {
		this.callVariable9 = callVariable9;
	}

	public String getCallVariable10() {
		return callVariable10;
	}

	public void setCallVariable10(String callVariable10) {
		this.callVariable10 = callVariable10;
	}

	public Map<Long, String> getEccValueVariables() {
		if (null == eccValueVariables) {
			eccValueVariables = new HashMap<Long, String>();
		}
		return eccValueVariables;
	}

	public Map<Long, Map<Integer, String>> getEccArrayVariables() {
		if (null == eccArrayVariables) {
			eccArrayVariables = new HashMap<Long, Map<Integer, String>>();
		}
		return eccArrayVariables;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_RUN_SCRIPT_RESULT;
	}

}
