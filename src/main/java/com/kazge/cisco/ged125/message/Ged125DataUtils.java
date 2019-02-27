package com.kazge.cisco.ged125.message;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Ged125DataUtils {
	public static byte[] boolean2Bytes(boolean v) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		if (v) {
			bb.putInt(1);
		} else {
			bb.putInt(0);
		}
		return bb.array();
	}
	
	public static boolean bytes2Boolean(byte[] bs, int from) {
		int bi = bytes2Int(Arrays.copyOfRange(bs, from, from + 4));
		
		return bi > 0;
	}
	
	public static byte uchar2Byte(int c) {
		return (byte) c;
	}

	public static byte[] uchar2Bytes(int c) {
		return new byte[] { uchar2Byte(c) };
	}

	public static int bytes2Uchar(byte[] b, int from) {
		return bytes2Uchar(Arrays.copyOfRange(b, from, from + 1));
	}

	public static int bytes2Uchar(byte[] b) {
		return b[0];
	}

	public static byte[] string2Bytes(String str) {
		try {
			byte[] bs = str.getBytes("US-ASCII");
			//this issue only exists in ged-188?
//			bs = ArrayUtils.add(bs, (byte) '\0');

			return bs;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String bytes2String(byte[] bs, int offset, int len) {
		return bytes2StringJava(Arrays.copyOfRange(bs, offset, offset + len));
	}

	public static String bytes2String(byte[] bs) {
		return bytes2String(bs,0,bs.length);
	}
	
	private static String bytes2StringJava(byte[] bs) {
		try {
			return new String(bs, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] space2Bytes(String str) {
		try {
			return str.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] uint2Bytes(long n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);

		return b;
	}

	public static long bytes2Uint(byte[] bs, int offset, int len) {
		return bytes2Uint(Arrays.copyOfRange(bs, offset, offset + len));
	}

	public static long bytes2Uint(byte[] array) {
		return ((long) (array[3] & 0xff)) | ((long) (array[2] & 0xff)) << 8
				| ((long) (array[1] & 0xff)) << 16
				| ((long) (array[0] & 0xff)) << 24;
	}

	public static long bytes2Uint(byte[] res, int from) {
		return bytes2Uint(Arrays.copyOfRange(res, from, from + 4));
	}

	public static byte[] int2Bytes(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	public static int bytes2Int(byte[] res, int from) {
		return bytes2Int(Arrays.copyOfRange(res, from, from + 4));
	}

	public static int bytes2Int(byte[] bs, int offset, int len) {
		return bytes2Int(Arrays.copyOfRange(bs, offset, offset + len));
	}

	public static int bytes2Int(byte[] b) {
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
				| (b[0] & 0xff) << 24;
	}

	public static byte[] ushort2Bytes(int n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) ((n >> 8) & 0xff);

		return b;
	}

	public static int bytes2Ushort(byte[] bs, int offset, int len) {
		return bytes2Ushort(Arrays.copyOfRange(bs, offset, offset + len));
	}

	public static int bytes2Ushort(byte[] b, int from) {
		return bytes2Ushort(Arrays.copyOfRange(b, from, from + 2));
	}

	public static int bytes2Ushort(byte[] b) {
		return b[1] & 0xff | (b[0] & 0xff) << 8;
	}
}
