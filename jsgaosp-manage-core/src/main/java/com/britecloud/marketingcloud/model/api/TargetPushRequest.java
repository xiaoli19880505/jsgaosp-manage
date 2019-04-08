package com.britecloud.marketingcloud.model.api;

import java.util.Map;




public class TargetPushRequest {
	private String userId;
	private String campaignId;
	private Map dataFields;
	private String sendAt; // YYYY-MM-DD HH:MM:SS

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	
	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

}
