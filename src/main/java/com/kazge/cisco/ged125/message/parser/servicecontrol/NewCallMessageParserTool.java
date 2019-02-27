package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.response.servicecontrol.NewCallMessage;

public class NewCallMessageParserTool implements ServiceCtrlMessageParserTool {

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		NewCallMessage smsg = (NewCallMessage)msg;
		
		smsg.setTrunkGroupID(parser.readUint());
		smsg.setTrunkNumber(parser.readUint());
		smsg.setServiceID(parser.readUint());
	}

	@Override
	public ServiceCtrlMessage createSubTypeMessage() {
		return new NewCallMessage();
	}

	@Override
	public void onFloatField(ServiceCtrlMessage msg, int tag, byte[] value) {
		NewCallMessage smsg = (NewCallMessage)msg;
		if (tag == MessageEnum.TAG_Dialed_Number){
			smsg.setDialedNumber(Ged125DataUtils.bytes2String(value));
		}
	}

	@Override
	public void onEccValue(ServiceCtrlMessage msg, long eccTag, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEccArray(ServiceCtrlMessage msg, long eccTag, int index, String value) {
		// TODO Auto-generated method stub
		
	}
	
}
