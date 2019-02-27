package com.kazge.cisco.ged125.message.sender;

import java.util.HashMap;
import java.util.Map;

import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.sender.servicecontrol.ServiceCtrlMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.ConnectMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.EventReportMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.InitServiceCtrlConfMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.InitServiceCtrlDataMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.InitServiceCtrlEndMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.InitServiceCtrlReqMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.NewCallMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.RunScriptReqMessageSenderTool;
import com.kazge.cisco.ged125.message.sender.servicecontrol.RunScriptResultMessageSenderTool;

public class ServiceCtrlMessageSender extends AbstractMessageSender {
	private static Map<Long, ServiceCtrlMessageSenderTool> toolset;
	
	static{
		toolset = new HashMap<Long, ServiceCtrlMessageSenderTool>();
		
		toolset.put(1L,  new InitServiceCtrlReqMessageSenderTool());
		toolset.put(2L,  new InitServiceCtrlConfMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_INIT_SERVICE_CTRL_DATA, new InitServiceCtrlDataMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_INIT_SERVICE_CTRL_END, new InitServiceCtrlEndMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_NEW_CALL,  new NewCallMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_CONNECT, new ConnectMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_RUN_SCRIPT_REQ, new RunScriptReqMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_RUN_SCRIPT_RESULT, new RunScriptResultMessageSenderTool());
		toolset.put(MessageEnum.MessageSubType_EVENT_REPORT, new EventReportMessageSenderTool());
	}
	
	@Override
	protected void prepareData(Ged125Message message) {
		ServiceCtrlMessage msg = (ServiceCtrlMessage) message;

		long subType = msg.getMessageSubType();
		
		addUint(msg.getServiceControlHeader().getMessageSubType());
		addUint(msg.getServiceControlHeader().getDialogueID());
		addUint(msg.getServiceControlHeader().getSendSeqNo());
		
		//some message have not invoke id
//		addUint(msg.getInvokeId());
		
		ServiceCtrlMessageSenderTool tool = toolset.get(subType);
		
		if (null == tool){
			throw new RuntimeException(String.format("the service control sender tool for %s is null.",message.getClassType()));
		}

		tool.sendOtherPart(this,msg);
	}

}
