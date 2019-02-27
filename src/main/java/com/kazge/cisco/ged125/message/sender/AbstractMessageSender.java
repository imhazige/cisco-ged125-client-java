package com.kazge.cisco.ged125.message.sender;

import java.util.ArrayList;
import java.util.List;

import com.kazge.cisco.ged125.message.EccName;
import com.kazge.cisco.ged125.message.Ged125DataUtils;
import com.kazge.cisco.ged125.message.Ged125Message;
import com.kazge.cisco.ged125.message.MessageEnum;
import com.kazge.cisco.ged125.message.socket.Ged125MessageChannel;
import org.apache.commons.lang.ArrayUtils;

import com.kazge.common.StringUtils;

public abstract class AbstractMessageSender {
	protected abstract void prepareData(Ged125Message message);

	private List<byte[]> bytes = null;

	public void sendMessage(Ged125MessageChannel client, Ged125Message message) {
		List<byte[]> data = new ArrayList<byte[]>();

		bytes = new ArrayList<byte[]>();
		prepareData(message);
		int dataLength = 0;
		for (int i = 0; i < bytes.size(); i++) {
			byte[] ibs = bytes.get(i);
			dataLength += ibs.length;
		}
		data.add(Ged125DataUtils.uint2Bytes(dataLength));
		data.add(Ged125DataUtils.uint2Bytes(message.getMessageType()));
		data.addAll(bytes);

		@SuppressWarnings("unused")
		int len = client.sendMessage(data);
		// Log.debug("sendMessage:%s,%s write total length %s.",dataLength +
		// 8,message.getClassType(),len);
	}

	public void addUint(long n) {
		bytes.add(Ged125DataUtils.uint2Bytes(n));
	}

	public void addBooleanF(int tag, Boolean v) {
		if (null == v) {
			return;
		}
		addF(tag, Ged125DataUtils.boolean2Bytes(v));
	}

	public void addEccNameF(EccName eccname) {
		if (null == eccname || eccname.isEmpty()) {
			return;
		}
		byte[] bs = ArrayUtils.addAll(Ged125DataUtils.uint2Bytes(eccname.getTag()),
				Ged125DataUtils.string2Bytes(eccname.getName()));

		addF(MessageEnum.TAG_ExpCallVarName, bs);
	}

	public void addEccValueF(Long tag, String value) {
		if (null == tag || StringUtils.isBlank(value)) {
			return;
		}

		byte[] bs = ArrayUtils.addAll(Ged125DataUtils.uint2Bytes(tag), Ged125DataUtils.string2Bytes(value));

		addF(MessageEnum.TAG_ExpCallVarValue, bs);
	}

	public void addEccArrayF(Long tag, Integer index, String value) {
		if (null == tag || null == index || !StringUtils.isBlank(value)) {
			return;
		}
		byte[] bs = ArrayUtils.addAll(Ged125DataUtils.uint2Bytes(tag), Ged125DataUtils.uchar2Bytes(index));

		bs = ArrayUtils.addAll(bs, Ged125DataUtils.string2Bytes(value));

		addF(MessageEnum.TAG_ExpCallVarArray, bs);
	}

	public void addBoolean(boolean v) {
		bytes.add(Ged125DataUtils.boolean2Bytes(v));
	}

	public void addInt(int n) {
		bytes.add(Ged125DataUtils.int2Bytes(n));
	}

	public void addString(String s) {
		bytes.add(Ged125DataUtils.string2Bytes(s));
	}

	public void addUchar(int n) {
		bytes.add(Ged125DataUtils.uchar2Bytes(n));
	}

	public void addUshort(int n) {
		bytes.add(Ged125DataUtils.ushort2Bytes(n));
	}

	public void add(byte[] bs) {
		bytes.add(bs);
	}

	public void addF(int tag, byte[] bs) {
		if (null == bs || bs.length == 0) {
			return;
		}
		addUchar(tag);
		addUchar(bs.length);
		add(bs);
	}

	public void addStringF(int tag, String s) {
		if (null == s) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.string2Bytes(s);
		addUchar(bs.length);
		add(bs);
	}

	public void addSpaceF(int tag, String s) {
		if (null == s) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.space2Bytes(s);
		addUchar(bs.length);
		add(bs);
	}

	public void addUintF(int tag, Long n) {
		if (null == n) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.uint2Bytes(n);
		addUchar(bs.length);
		add(bs);
	}

	public void addUcharF(int tag, Integer n) {
		if (null == n) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.uchar2Bytes(n);
		addUchar(bs.length);
		add(bs);
	}

	public void addUshortF(int tag, Integer n) {
		if (null == n) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.ushort2Bytes(n);
		addUchar(bs.length);
		add(bs);
	}

	public void addIntF(int tag, Integer n) {
		if (null == n) {
			return;
		}
		addUchar(tag);
		byte[] bs = Ged125DataUtils.int2Bytes(n);
		addUchar(bs.length);
		add(bs);
	}

}
