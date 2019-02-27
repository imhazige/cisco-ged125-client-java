package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;
import com.kazge.cisco.ged125.message.request.servicecontrol.ReleaseMessage;

public class ReleaseMessageParserTool implements ServiceCtrlMessageParserTool{

	@Override
	public void parseOtherFixPart(ServiceCtrlMessage msg, ServiceCtrlMessageParser parser) {
		ReleaseMessage rmsg = (ReleaseMessage)msg;
		
		rmsg.setCause(parser.readUint());
	}

	@Override
	public ServiceCtrlMessage createSubTypeMessage() {
		return new ReleaseMessage();
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
