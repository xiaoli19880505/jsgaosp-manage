/*
 *   David Hu
 */
package com.britecloud.marketingcloud.core.security;

import java.io.Serializable;
import java.util.HashMap;

public class ClientSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	boolean logined = false;

	private HashMap attributes = new HashMap();

	public void addAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public HashMap getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap attributes) {
		this.attributes = attributes;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}
	
}
