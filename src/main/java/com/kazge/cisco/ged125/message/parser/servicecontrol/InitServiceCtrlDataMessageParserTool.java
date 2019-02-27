package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlDataMessage;

public class InitServiceCtrlDataMessageParserTool implements ServiceCtrlMessageParserTool {

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		InitServiceCtrlDataMessage smsg = (InitServiceCtrlDataMessage) msg;

		smsg.setInvokeId(parser.readUint());
		smsg.setServiceFeatures(parser.readUint());
	}

	@Override
	public ServiceCtrlMessage createSubTypeMessage() {
		return new InitServiceCtrlDataMessage();
	}

	@Override
	public void onFloatField(ServiceCtrlMessage msg, int tag, byte[] value) {

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
