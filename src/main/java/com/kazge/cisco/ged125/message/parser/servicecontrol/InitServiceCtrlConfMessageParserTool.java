package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.response.servicecontrol.InitServiceCtrlConfMessage;

public class InitServiceCtrlConfMessageParserTool implements ServiceCtrlMessageParserTool{

	public ServiceCtrlMessage createSubTypeMessage() {
		return new InitServiceCtrlConfMessage();
	}

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		msg.setInvokeId(parser.readUint());
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
