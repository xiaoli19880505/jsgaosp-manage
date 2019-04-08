package com.britecloud.marketingcloud.model.api;

import com.alibaba.fastjson.JSONObject;




public class UpdateUserRequest {

	String userId;
	JSONObject dataFields;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public JSONObject getDataFields() {
		return dataFields;
	}

	public void setDataFields(JSONObject dataFields) {
		this.dataFields = dataFields;
	}

}
