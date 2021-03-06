package com.kazge.cisco.ged125.message;

public interface MessageEnum {
	long FEATURE_RUN_SCRIPT = 0x00000001;
	long FEATURE_CONNECT = 0x00000002;
	long FEATURE_CANCEL = 0x00000004;
	long FEATURE_RELEASE = 0x00000008;
	long FEATURE_BLIND_TRANSFER = 0x00000010;
	long FEATURE_NEED_RELEASE = 0x00000020;
	long FEATURE_RUN_MICROAPP = 0x00000040;
	long FEATURE_NO_REQUERY = 0x00000080;

	long NULL_DATA_ELEMENT = 0xFFFFFFFF;

	long LABEL_TYPE_NORMAL = 1;
	long LABEL_TYPE_BUSY = 2;
	long LABEL_TYPE_RING = 3;
	long LABEL_TYPE_DEFAULT = 5;

	int TAG_Invalid = 0;
	int TAG_Text = 1;
	int TAG_ANI = 18;
	int TAG_UUI = 19;
	int TAG_DNIS = 20;
	int TAG_Digits_Dialed = 21;
	int TAG_Call_Variable_1 = 22;
	int TAG_Call_Variable_2 = 23;
	int TAG_Call_Variable_3 = 24;
	int TAG_Call_Variable_4 = 25;
	int TAG_Call_Variable_5 = 26;
	int TAG_Call_Variable_6 = 27;
	int TAG_Call_Variable_7 = 28;
	int TAG_Call_Variable_8 = 29;
	int TAG_Call_Variable_9 = 30;
	int TAG_Call_Variable_10 = 31;
	int TAG_Dialed_Number = 32;
	int TAG_CED = 33;
	int TAG_Label = 34;
	int TAG_Trunk_Group_ID = 35;
	int TAG_Trunk_Number = 36;
	int TAG_Called_Number = 37;
	int TAG_Script_ID = 38;
	int TAG_Script_Configuration = 39;
	int TAG_Correlation_ID = 40;
	int TAG_Cause_Code = 41;
	int TAG_ExpCallVarName = 42;
	int TAG_ExpCallVarValue = 43;
	int TAG_ExpCallVarArray = 44;
	int TAG_NewTransactionTag = 45;
	int TAG_TransferHintTag = 46;
	int TAG_Media_Specifier = 47;
	int TAG_Initial_Prompt = 48;
	int TAG_Invalid_Entry_Prompt = 49;
	int TAG_Timeout_Prompt = 50;
	int TAG_CustomerID = 51;
	int TAG_Application_media_library = 52;
	int TAG_System_media_library = 53;
	int TAG_Locale = 54;
	int TAG_Media_server_set = 55;
	int TAG_Microapp_Error_Text = 56;
	int TAG_ASR_Grammar = 57;
	int TAG_Currency = 58;
	int TAG_RouterCallKey = 59;
	int TAG_RouterCallKeyDay = 60;
	int TAG_RouterCallKeySequenceNumber = 61;
	int TAG_Call_GUID = 62;
	int TAG_Location_Parameter_PKID = 63;
	int TAG_Location_Parameter_Name = 64;
	int TAG_PSTN_Trunk_Group_ID = 65;
	int TAG_PSTN_Trunk_Group_Channel_Number = 66;
	int TAG_SIP_Header = 67;
	int TAG_WhisperAnnounce = 68;
	int TAG_FeatureType = 69;
	int TAG_FeatureParam1 = 70;
	int TAG_FeatureParam2 = 71;
	int TAG_AgentGreetingHint = 72;

	long MessageSubType_INIT_SERVICE_CTRL_REQ = 1;
	long MessageSubType_INIT_SERVICE_CTRL_CONF = 2;
	long MessageSubType_INIT_SERVICE_CTRL_DATA = 3;
	long MessageSubType_INIT_SERVICE_CTRL_END = 4;
	long MessageSubType_NEW_CALL = 5;
	long MessageSubType_REQUEST_INSTRUCTION = 6;
	long MessageSubType_RUN_SCRIPT_REQ = 7;
	long MessageSubType_RUN_SCRIPT_RESULT = 8;
	long MessageSubType_CONNECT = 9;
	long MessageSubType_EVENT_REPORT = 10;
	long MessageSubType_DIALOGUE_FAILURE_CONF = 11;
	long MessageSubType_DIALOGUE_FAILURE_EVENT = 12;
	long MessageSubType_INIT_SERVICE_CTRL_TRKGRP = 13;
	long MessageSubType_INIT_SERVICE_CTRL_SERVICE = 14;
	long MessageSubType_INIT_SERVICE_CTRL_VRU = 15;
	long MessageSubType_TRKGRP_STATUS = 16;
	long MessageSubType_SERVICE_STATUS = 17;
	long MessageSubType_VRU_STATUS = 18;
	long MessageSubType_CANCEL = 19;
	long MessageSubType_RELEASE = 20;
	long MessageSubType_NEW_DIALOGUE = 21;
	long MessageSubType_CONNECT_TO_RESOURCE = 22;
	long MessageSubType_RESOURCE_CONNECTED = 23;
	long MessageSubType_MICROAPP_CONTEXT = 24;
	long MessageSubType_MICROAPP_PLAY = 25;
	long MessageSubType_MICROAPP_PLAY_CONTINUE = 26;
	long MessageSubType_MICROAPP_COLLECT_DATA = 27;
	long MessageSubType_MICROAPP_MENU = 28;
	long MessageSubType_MICROAPP_RESULT = 29;
	long MessageSubType_TEMPORARY_CONNECT = 30;

	long EventID_CONNECT_FAILURE = 1;
	long EventID_BUSY = 2;
	long EventID_NO_ANSWER = 3;
	long EventID_ANSWER = 4;
	long EventID_ABANDON = 5;
	long EventID_DISCONNECT = 6;
	long EventID_CONNECT_INVALID = 7;

	long MessageType_CloseConf = 8;
	long MessageType_RegisterVariables = 49;

	int MASK_CALL_VARS_Call_Variable_1 = 0x0001;
	int MASK_CALL_VARS_Call_Variable_2 = 0x0002;
	int MASK_CALL_VARS_Call_Variable_3 = 0x0004;
	int MASK_CALL_VARS_Call_Variable_4 = 0x0008;
	int MASK_CALL_VARS_Call_Variable_5 = 0x0010;
	int MASK_CALL_VARS_Call_Variable_6 = 0x0020;
	int MASK_CALL_VARS_Call_Variable_7 = 0x0040;
	int MASK_CALL_VARS_Call_Variable_8 = 0x0080;
	int MASK_CALL_VARS_Call_Variable_9 = 0x0100;
	int MASK_CALL_VARS_Call_Variable_10 = 0x0200;

}
