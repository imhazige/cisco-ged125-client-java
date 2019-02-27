package com.kazge.cisco.ged125.message.parser.servicecontrol;

import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.ServiceCtrlMessageParser;

public interface ServiceCtrlMessageParserTool{
	void parseOtherFixPart(ServiceCtrlMessage msg,ServiceCtrlMessageParser parser);

	ServiceCtrlMessage createSubTypeMessage();

	void onFloatField(ServiceCtrlMessage msg, int tag, byte[] value);

	void onEccValue(ServiceCtrlMessage msg, long eccTag, String value);

	void onEccArray(ServiceCtrlMessage msg, long eccTag, int index, String value);
}
