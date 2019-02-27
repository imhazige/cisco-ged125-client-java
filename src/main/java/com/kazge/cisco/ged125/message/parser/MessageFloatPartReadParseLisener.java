package com.kazge.cisco.ged125.message.parser;

public interface MessageFloatPartReadParseLisener {
	void onFloatField(long tag, byte[] value);
}
