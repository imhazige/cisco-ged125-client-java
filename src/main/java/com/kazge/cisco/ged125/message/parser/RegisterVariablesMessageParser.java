package com.kazge.cisco.ged125.message.parser;

import com.kazge.cisco.ged125.message.response.RegisterVariablesMessage;

public class RegisterVariablesMessageParser extends AbstractMessageParser<RegisterVariablesMessage>{

	@Override
	protected RegisterVariablesMessage parseFixPart() {
		RegisterVariablesMessage msg = new RegisterVariablesMessage();
		
		msg.setCallVarsMask(readUshort());
		
		return msg;
	}

	@Override
	protected void onFloatField(RegisterVariablesMessage msg, int tag, byte[] value) {
		//TODO read eccvars
	}

}
