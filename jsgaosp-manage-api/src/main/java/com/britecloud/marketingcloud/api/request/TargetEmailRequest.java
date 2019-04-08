package com.britecloud.marketingcloud.api.request;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TargetEmailRequest {
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
	@ApiModelProperty(value = "附加数据,格式:Map<String,Object>")
	public Map getDataFields() {
		return dataFields;
	}

	public void setDataFields(Map dataFields) {
		this.dataFields = dataFields;
	}
	@ApiModelProperty(value = "发送时间，格式：YYYY-MM-DD HH:MM:SS", required = true)
	public String getSendAt() {
		return sendAt;
	}

	public void setSendAt(String sendAt) {
		this.sendAt = sendAt;
	}

}
