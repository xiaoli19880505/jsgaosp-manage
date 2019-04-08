package com.britecloud.marketingcloud.api.request;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TrackRequest {
	String createdAt; //YYYY-MM-DD HH:MM:SS
	Map dataFields;
	String eventName;
	String transcationId;
	String userId;
	String companyId;
	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	@ApiModelProperty(value="创建的时间,格式：YYYY-MM-DD HH:MM:SS")
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
