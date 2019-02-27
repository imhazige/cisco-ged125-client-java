package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.DialogueFailureEventMessage;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;

public class DialogueFailureEventMessageParserTool implements ServiceCtrlMessageParserTool{

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		DialogueFailureEventMessage rmsg = (DialogueFailureEventMessage)msg;
		
		rmsg.setErrorCode(parser.readUint());
	}

	@Override
	public ServiceCtrlMessage createSubTypeMessage() {
		return new DialogueFailureEventMessage();
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
