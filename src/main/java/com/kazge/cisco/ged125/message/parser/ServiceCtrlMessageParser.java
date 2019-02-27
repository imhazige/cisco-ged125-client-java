package com.kazge.cisco.ged125.message.parser;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.parser.servicecontrol.ServiceCtrlMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.ConnectMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.DialogueFailureEventMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.InitServiceCtrlConfMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.InitServiceCtrlDataMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.InitServiceCtrlEndMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.InitServiceCtrlReqMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.NewCallMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.ReleaseMessageParserTool;
import com.kazge.cisco.ged125.message.parser.servicecontrol.RunScriptReqMessageParserTool;

public class ServiceCtrlMessageParser extends AbstractMessageParser<ServiceCtrlMessage> {
	private static Map<Long, ServiceCtrlMessageParserTool> toolset;

	static {
		toolset = new HashMap<Long, ServiceCtrlMessageParserTool>();

		toolset.put(1L, new InitServiceCtrlReqMessageParserTool());
		toolset.put(2L, new InitServiceCtrlConfMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_INIT_SERVICE_CTRL_DATA, new InitServiceCtrlDataMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_INIT_SERVICE_CTRL_END, new InitServiceCtrlEndMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_NEW_CALL, new NewCallMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_CONNECT, new ConnectMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_RUN_SCRIPT_REQ, new RunScriptReqMessageParserTool());
		
		toolset.put(MessageEnum.MessageSubType_DIALOGUE_FAILURE_EVENT, new DialogueFailureEventMessageParserTool());
		toolset.put(MessageEnum.MessageSubType_RELEASE, new ReleaseMessageParserTool());
	}

	@Override
	protected ServiceCtrlMessage parseFixPart() {
		long subType = readUint();
		long dialogueID = readUint();
		long sendSeqNo = readUint();

		// some message have not invoke id
		// int invokeId = readUint();

		ServiceCtrlMessageParserTool tool = toolset.get(subType);

		if (null == tool) {
			throw new RuntimeException(String.format("the service control parser tool for %s is null.", subType));
		}

		ServiceCtrlMessage msg = tool.createSubTypeMessage();

		msg.getServiceControlHeader().setDialogueID(dialogueID);
		msg.getServiceControlHeader().setSendSeqNo(sendSeqNo);

		tool.parseOtherFixPart(msg, this);

		return msg;
	}

	@Override
	protected void onFloatField(ServiceCtrlMessage msg, int tag, byte[] value) {
		ServiceCtrlMessageParserTool tool = toolset.get(msg.getMessageSubType());

		tool.onFloatField(msg, tag, value);

	}
	
	@Override
	protected void onEccValue(ServiceCtrlMessage msg, long tag, String value) {
		ServiceCtrlMessageParserTool tool = toolset.get(msg.getMessageSubType());

		tool.onEccValue(msg, tag, value);
	}
	
	@Override
	protected void onEccArray(ServiceCtrlMessage msg, long tag, int index, String value) {
		ServiceCtrlMessageParserTool tool = toolset.get(msg.getMessageSubType());

		tool.onEccArray(msg, tag,index, value);
	}

}
