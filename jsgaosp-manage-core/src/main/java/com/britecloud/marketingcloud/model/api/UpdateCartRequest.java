package com.britecloud.marketingcloud.model.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;




public class UpdateCartRequest {
	private String userId;
	private String transcationId;
	String createdAt;

	List<JSONObject> items;
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getTranscationId() {
		return transcationId;
	}

	public void setTranscationId(String compaignId) {
		this.transcationId = compaignId;
	}
	
	
	
	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<JSONObject> getItems() {
		return items;
	}

	public void setItems(List<JSONObject> items) {
		this.items = items;
	}

}
