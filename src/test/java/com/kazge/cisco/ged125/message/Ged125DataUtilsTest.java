package com.kazge.cisco.ged125.message;

import com.com.kazge.common.midware.common.Log;
import com.com.kazge.common.midware.common.SimpleBaseTest;

public class Ged125DataUtilsTest extends SimpleBaseTest{
	public void testMatch(){
		try {
			
			Object ret = null;
			Object v = null;
			
			v = true;
			ret = Ged125DataUtils.bytes2Boolean(Ged125DataUtils.boolean2Bytes((Boolean)v),0);
			Log.debug("%s <==> %s",v,ret);
			
			v = 6;
			ret = Ged125DataUtils.bytes2Int(Ged125DataUtils.int2Bytes((Integer)v),0);
			Log.debug("%s <==> %s",v,ret);
			
			
			v = 6666;
			ret = Ged125DataUtils.bytes2Uint(Ged125DataUtils.uint2Bytes((Integer)v),0);
			Log.debug("%s <==> %s",v,ret);
		} catch (Exception e) {
			e.printStackTrace();
			
			fail();
		}
	}
}
