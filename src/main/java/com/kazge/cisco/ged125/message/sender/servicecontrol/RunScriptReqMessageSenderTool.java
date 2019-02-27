package com.kazge.cisco.ged125.message.sender.servicecontrol;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.request.servicecontrol.RunScriptReqMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class RunScriptReqMessageSenderTool implements ServiceCtrlMessageSenderTool{

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		RunScriptReqMessage smsg = (RunScriptReqMessage)msg;
		
		sender.addUint(smsg.getInvokeId());
		
		//TODO others
		
		sender.addStringF(MessageEnum.TAG_Call_Variable_1, smsg.getCallVariable1());
		sender.addStringF(MessageEnum.TAG_Call_Variable_2, smsg.getCallVariable2());
		sender.addStringF(MessageEnum.TAG_Call_Variable_3, smsg.getCallVariable3());
		sender.addStringF(MessageEnum.TAG_Call_Variable_4, smsg.getCallVariable4());
		sender.addStringF(MessageEnum.TAG_Call_Variable_5, smsg.getCallVariable5());
		sender.addStringF(MessageEnum.TAG_Call_Variable_6, smsg.getCallVariable6());
		sender.addStringF(MessageEnum.TAG_Call_Variable_7, smsg.getCallVariable7());
		sender.addStringF(MessageEnum.TAG_Call_Variable_8, smsg.getCallVariable8());
		sender.addStringF(MessageEnum.TAG_Call_Variable_9, smsg.getCallVariable9());
		sender.addStringF(MessageEnum.TAG_Call_Variable_10, smsg.getCallVariable10());
	}

}
