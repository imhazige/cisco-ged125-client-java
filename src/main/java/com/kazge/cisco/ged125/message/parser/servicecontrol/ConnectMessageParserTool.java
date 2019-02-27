package com.kazge.cisco.ged125.message.parser.servicecontrol;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.request.servicecontrol.ConnectMessage;

public class ConnectMessageParserTool implements ServiceCtrlMessageParserTool {

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		ConnectMessage rmsg = (ConnectMessage) msg;

		rmsg.setLabelType(parser.readUint());
	}

	@Override
	public ServiceCtrlMessage createSubTypeMessage() {
		return new ConnectMessage();
	}

	@Override
	public void onFloatField(ServiceCtrlMessage msg, int tag, byte[] value) {
		ConnectMessage rmsg = (ConnectMessage) msg;
		if (MessageEnum.TAG_Label == tag) {
			rmsg.setLabel(Ged125DataUtils.bytes2String(value));
		}else if(MessageEnum.TAG_RouterCallKey == tag){
			rmsg.setRouterCallKey(Ged125DataUtils.bytes2Uint(value));
		} else if(MessageEnum.TAG_RouterCallKeyDay == tag){
			rmsg.setRouterCallKeyDay(Ged125DataUtils.bytes2Uint(value));
		}else if(MessageEnum.TAG_RouterCallKeySequenceNumber == tag){
			rmsg.setRouterCallKeySequenceNumber(Ged125DataUtils.bytes2Uint(value));
		}
		
		if (MessageEnum.TAG_Call_Variable_1 == tag) {
			rmsg.setCallVariable1(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_2 == tag) {
			rmsg.setCallVariable2(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_3 == tag) {
			rmsg.setCallVariable3(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_4 == tag) {
			rmsg.setCallVariable4(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_5 == tag) {
			rmsg.setCallVariable5(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_6 == tag) {
			rmsg.setCallVariable6(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_7 == tag) {
			rmsg.setCallVariable7(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_8 == tag) {
			rmsg.setCallVariable8(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_9 == tag) {
			rmsg.setCallVariable9(Ged125DataUtils.bytes2String(value));
		} else if (MessageEnum.TAG_Call_Variable_10 == tag) {
			rmsg.setCallVariable10(Ged125DataUtils.bytes2String(value));
		}
	}

	@Override
	public void onEccValue(ServiceCtrlMessage msg, long eccTag, String value) {
		ConnectMessage rmsg = (ConnectMessage) msg;
		
		rmsg.getEccValueVariables().put(eccTag, value);
	}

	@Override
	public void onEccArray(ServiceCtrlMessage msg, long eccTag, int index, String value) {
		ConnectMessage rmsg = (ConnectMessage) msg;
		
		Map<Integer, String> arr = rmsg.getEccArrayVariables().get(eccTag);
		
		if (null == arr){
			arr = new HashMap<Integer, String>();
			rmsg.getEccArrayVariables().put(eccTag,arr);
		}
		
		arr.put(index, value);
	}

}
