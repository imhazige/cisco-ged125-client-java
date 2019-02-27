package com.kazge.cisco.ged125.message.parser;

import java.util.Arrays;

import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;

public abstract class AbstractMessageParser<T extends Ged125Message> {
	protected int offset;
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	abstract protected T parseFixPart();

	abstract protected void onFloatField(T msg, int tag, byte[] value);

	protected void onEccValue(T msg, long tag, String value) {

	}

	protected void onEccArray(T msg, long tag, int index, String value) {

	}

	public T parse(byte[] adata) {
		data = adata;

		T msg = parseFixPart();
		parseFloatPart(msg);

		return msg;
	}

	private void parseFloatPart(T msg) {
		while (!checkReadEnd(false)) {
			int tag = readUchar();
			int len = readUchar();

			byte[] value = Arrays.copyOfRange(data, offset, offset + len);
			offset += len;
			if (0 < len) {
				if (MessageEnum.TAG_ExpCallVarValue == tag) {
					if (len > 4){
						long ecctag = Ged125DataUtils.bytes2Uint(Arrays.copyOfRange(value, 0, 4));
						int nextlen = len - 4;
						if (nextlen > 0){
							String eccvalue = Ged125DataUtils.bytes2String(Arrays.copyOfRange(value, 4, len));
							onEccValue(msg,ecctag, eccvalue);
						}
					}
				} else if (MessageEnum.TAG_ExpCallVarArray == tag) {
					if (len > 6){
						long ecctag = Ged125DataUtils.bytes2Uint(Arrays.copyOfRange(value, 0, 4));
						int eccindex = Ged125DataUtils.bytes2Ushort(Arrays.copyOfRange(value, 4, 2));
						int nextlen = len - 6;
						if (nextlen > 0){
							String eccvalue = Ged125DataUtils.bytes2String(Arrays.copyOfRange(value, 6, len));
							onEccArray(msg,ecctag, eccindex, eccvalue);
						}	
					}
				} else {
					onFloatField(msg, tag, value);
				}
			}
		}
	}

	public int readInt() {
		int i = Ged125DataUtils.bytes2Int(data, offset);
		offset += 4;

		return i;
	}

	public int readUint() {
		int i = Ged125DataUtils.bytes2Int(data, offset);
		offset += 4;

		return i;
	}

	public long readTime() {
		int i = Ged125DataUtils.bytes2Int(data, offset);
		offset += 4;

		return i;
	}

	public int readUshort() {
		int i = Ged125DataUtils.bytes2Ushort(data, offset);
		offset += 2;

		return i;
	}

	public int readUchar() {
		int i = Ged125DataUtils.bytes2Uchar(data, offset);
		offset += 1;

		return i;
	}

	public boolean readBoolean() {
		boolean b = Ged125DataUtils.bytes2Boolean(data, offset);
		offset += 4;

		return b;
	}

	public String readString(int len) {
		byte[] bs = Arrays.copyOfRange(data, offset, offset + len);
		offset += len;
		String b = Ged125DataUtils.bytes2String(bs);

		return b;
	}

	public boolean checkReadEnd(boolean throwerror) {
		boolean match = offset == data.length;
		if (match) {
			return true;
		}
		if (throwerror) {
			throw new RuntimeException(
					String.format("Read Error, data[%s] not end, only %s readed.", data.length, offset));
		}

		return false;
	}
}
