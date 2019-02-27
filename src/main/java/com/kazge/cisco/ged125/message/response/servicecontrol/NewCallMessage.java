package com.kazge.cisco.ged125.message.response.servicecontrol;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.type.UnSpec;

public class NewCallMessage extends ServiceCtrlMessage {

	private long trunkGroupID;
	private long trunkNumber;
	private long serviceID;

	// float
	private String dialedNumber;
	private String ani;
	private String callerEnteredDigits;;
	private UnSpec userToUserInfo;
	private String calledNumber;
	private String dnis;
	private Long routerCallKey;
	private Long routerCallKeyDay;
	private Long routerCallKeySequenceNumber;
	private String callGUID;
	private String locationParamPKID;
	private String locationParamName;
	private String pstnTrunkGroupID;
	private Long pstnTrunkGroupChannelNumber;
	private String sipHeader;
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

	public long getTrunkGroupID() {
		return trunkGroupID;
	}

	public void setTrunkGroupID(long trunkGroupID) {
		this.trunkGroupID = trunkGroupID;
	}

	public long getTrunkNumber() {
		return trunkNumber;
	}

	public void setTrunkNumber(long trunkNumber) {
		this.trunkNumber = trunkNumber;
	}

	public long getServiceID() {
		return serviceID;
	}

	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
	}

	public String getDialedNumber() {
		return dialedNumber;
	}

	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}

	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}

	public String getCallerEnteredDigits() {
		return callerEnteredDigits;
	}

	public void setCallerEnteredDigits(String callerEnteredDigits) {
		this.callerEnteredDigits = callerEnteredDigits;
	}

	public UnSpec getUserToUserInfo() {
		return userToUserInfo;
	}

	public void setUserToUserInfo(UnSpec userToUserInfo) {
		this.userToUserInfo = userToUserInfo;
	}

	public String getCalledNumber() {
		return calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
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

	public String getLocationParamPKID() {
		return locationParamPKID;
	}

	public void setLocationParamPKID(String locationParamPKID) {
		this.locationParamPKID = locationParamPKID;
	}

	public String getLocationParamName() {
		return locationParamName;
	}

	public void setLocationParamName(String locationParamName) {
		this.locationParamName = locationParamName;
	}

	public String getPstnTrunkGroupID() {
		return pstnTrunkGroupID;
	}

	public void setPstnTrunkGroupID(String pstnTrunkGroupID) {
		this.pstnTrunkGroupID = pstnTrunkGroupID;
	}

	public Long getPstnTrunkGroupChannelNumber() {
		return pstnTrunkGroupChannelNumber;
	}

	public void setPstnTrunkGroupChannelNumber(Long pstnTrunkGroupChannelNumber) {
		this.pstnTrunkGroupChannelNumber = pstnTrunkGroupChannelNumber;
	}

	public String getSipHeader() {
		return sipHeader;
	}

	public void setSipHeader(String sipHeader) {
		this.sipHeader = sipHeader;
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
		return MessageEnum.MessageSubType_NEW_CALL;
	}

}
