package com.kazge.cisco.ged125.message.sender;

import java.util.Map.Entry;
import java.util.Set;

import com.kazge.cisco.ged125.message.EccName;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.response.RegisterVariablesMessage;

public class RegisterVariablesMessageSender extends AbstractMessageSender{

	@Override
	protected void prepareData(Ged125Message message) {
		RegisterVariablesMessage msg = (RegisterVariablesMessage)message;
		
		addUshort(msg.getCallVarsMask());
		
		//float
		Set<Entry<String, EccName>> set = msg.getEccTags().entrySet();
		if (!set.isEmpty()){
			for (Entry<String, EccName> entry : set) {
				addEccNameF(entry.getValue());
			}
		}
	}
}
