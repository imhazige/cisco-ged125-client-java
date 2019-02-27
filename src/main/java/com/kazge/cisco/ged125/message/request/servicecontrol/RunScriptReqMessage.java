package com.kazge.cisco.ged125.message.request.servicecontrol;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.type.UnSpec;

public class RunScriptReqMessage extends ServiceCtrlMessage {
	// float
	private String scriptID;
	private String scriptConfiguration;
	private String ani;
	private String ced;
	private Long routerCallKey;
	private Long routerCallKeyDay;
	private Long routerCallKeySequenceNumber;
	private String callGUID;
	private UnSpec userToUserInfo;

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
	private Map<Long, Map<Integer,String>> eccArrayVariables;

	private String featureType;
	private String featureParam1;
	private String featureParam2;

	public String getScriptID() {
		return scriptID;
	}

	public void setScriptID(String scriptID) {
		this.scriptID = scriptID;
	}

	public String getScriptConfiguration() {
		return scriptConfiguration;
	}

	public void setScriptConfiguration(String scriptConfiguration) {
		this.scriptConfiguration = scriptConfiguration;
	}

	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}

	public String getCed() {
		return ced;
	}

	public void setCed(String ced) {
		this.ced = ced;
	}

	public Long getRouterCallKey() {
		return routerCallKey;
	}

	public void setRouterCallKey(Long routerCallKey) {
		this.routerCallKey = routerCallKey;
	}

	public Long getRouterCallKeyDay() {
		return routerCallKeyDay;
	}

	public void setRouterCallKeyDay(Long routerCallKeyDay) {
		this.routerCallKeyDay = routerCallKeyDay;
	}

	public Long getRouterCallKeySequenceNumber() {
		return routerCallKeySequenceNumber;
	}

	public void setRouterCallKeySequenceNumber(Long routerCallKeySequenceNumber) {
		this.routerCallKeySequenceNumber = routerCallKeySequenceNumber;
	}

	public String getCallGUID() {
		return callGUID;
	}

	public void setCallGUID(String callGUID) {
		this.callGUID = callGUID;
	}

	public UnSpec getUserToUserInfo() {
		return userToUserInfo;
	}

	public void setUserToUserInfo(UnSpec userToUserInfo) {
		this.userToUserInfo = userToUserInfo;
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

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public String getFeatureParam1() {
		return featureParam1;
	}

	public void setFeatureParam1(String featureParam1) {
		this.featureParam1 = featureParam1;
	}

	public String getFeatureParam2() {
		return featureParam2;
	}

	public void setFeatureParam2(String featureParam2) {
		this.featureParam2 = featureParam2;
	}
	
	public Map<Long, String> getEccValueVariables() {
		if (null == eccValueVariables){
			eccValueVariables = new HashMap<Long, String>();
		}
		return eccValueVariables;
	}
	
	public Map<Long, Map<Integer, String>> getEccArrayVariables() {
		if (null == eccArrayVariables){
			eccArrayVariables = new HashMap<Long, Map<Integer,String>>();
		}
		return eccArrayVariables;
	}

	@Override
	public long getMessageSubType() {
		return MessageEnum.MessageSubType_RUN_SCRIPT_REQ;
	}

}
