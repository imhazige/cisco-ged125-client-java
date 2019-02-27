package com.kazge.cisco.ged125.message.sender.servicecontrol;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.ServiceCtrlMessage;
import com.kazge.cisco.ged125.message.response.servicecontrol.RunScriptResultMessage;
import com.kazge.cisco.ged125.message.sender.ServiceCtrlMessageSender;

public class RunScriptResultMessageSenderTool implements ServiceCtrlMessageSenderTool {

	@Override
	public void sendOtherPart(ServiceCtrlMessageSender sender, ServiceCtrlMessage msg) {
		RunScriptResultMessage smsg = (RunScriptResultMessage) msg;

		sender.addUint(smsg.getInvokeId());
		sender.addBoolean(smsg.isResultCode());

		// float
		sender.addStringF(MessageEnum.TAG_CED, smsg.getCed());
		sender.addBooleanF(MessageEnum.TAG_NewTransactionTag, smsg.getNewTransaction());

		// TODO others
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

		Set<Entry<Long, String>> set = smsg.getEccValueVariables().entrySet();

		if (!set.isEmpty()) {
			for (Entry<Long, String> entry : set) {
				sender.addEccValueF(entry.getKey(), entry.getValue());
			}
		}

		Set<Entry<Long, Map<Integer, String>>> aset = smsg.getEccArrayVariables().entrySet();

		if (!aset.isEmpty()) {
			for (Entry<Long, Map<Integer, String>> entry : aset) {
				Long tag = entry.getKey();
				Set<Entry<Integer, String>> ientry = entry.getValue().entrySet();
				for (Entry<Integer, String> entry2 : ientry) {
					sender.addEccArrayF(tag, entry2.getKey(), entry2.getValue());
				}
			}
		}
	}

}
