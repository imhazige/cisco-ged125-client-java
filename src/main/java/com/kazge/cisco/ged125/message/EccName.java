package com.kazge.cisco.ged125.message;

import com.kazge.common.StringUtils;

public class EccName {
	private String name;
	private long tag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTag() {
		return tag;
	}

	public void setTag(long tag) {
		this.tag = tag;
	}

	public boolean isEmpty() {
		return StringUtils.isBlank(getName()) || 0 == getTag();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof EccName)) {
			return false;
		}

		EccName ecn = (EccName) obj;

		return getName().equalsIgnoreCase(ecn.getName());
	}

}
