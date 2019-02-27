package com.kazge.cisco.ged125.client;

import com.kazge.cisco.ged125.message.Ged125Message;

public class Ged125ClientError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6497732876460764233L;
	private Ged125Message ged125msg;

	public Ged125Message getGed125msg() {
		return ged125msg;
	}

	public Ged125ClientError(String msg, Ged125Message aged188msg) {
		super(msg);
		ged125msg = aged188msg;
	}
}
