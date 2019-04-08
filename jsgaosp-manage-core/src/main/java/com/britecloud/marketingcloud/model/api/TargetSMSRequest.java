package com.britecloud.marketingcloud.model.api;

import java.util.Map;




public class TargetSMSRequest {
	private String userId;
	private String compaignId;
	private Map dataFields;
	private String sendAt; // YYYY-MM-DD HH:MM:SS

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getCompaignId() {
		return compaignId;
	}

	public void setCompaignId(String compaignId) {
		this.compaignId = compaignId;
	}
	
	public Map getDataFields() {
		return dataFields;
	}

	public void setDataFields(Map dataFields) {
		this.dataFields = dataFields;
	}
	
	public String getSendAt() {
		return sendAt;
	}

	public void setSendAt(String sendAt) {
		this.sendAt = sendAt;
	}

}
