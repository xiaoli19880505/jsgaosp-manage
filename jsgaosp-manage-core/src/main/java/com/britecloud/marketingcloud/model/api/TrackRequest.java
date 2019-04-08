package com.britecloud.marketingcloud.model.api;

import java.util.Map;



public class TrackRequest {
	String createdAt; //YYYY-MM-DD HH:MM:SS
	Map dataFields;
	String eventName;
	String transcationId;
	String userId;

	
	public String getCreatedAt() {
		return createdAt;
	}

	
	public Map getDataFields() {
		return dataFields;
	}

	public String getEventName() {
		return eventName;
	}

	public String getTranscationId() {
		return transcationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setDataFields(Map dataFields) {
		this.dataFields = dataFields;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setTranscationId(String compaignId) {
		this.transcationId = compaignId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
