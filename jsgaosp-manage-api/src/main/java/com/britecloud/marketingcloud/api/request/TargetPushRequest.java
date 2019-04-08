package com.britecloud.marketingcloud.api.request;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TargetPushRequest {
	private String userId;
	private String campaignId;
	private Map dataFields;
	private String sendAt; // YYYY-MM-DD HH:MM:SS

	@ApiModelProperty(value = "用户编号", required = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "附加数据")
	public Map getDataFields() {
		return dataFields;
	}

	public void setDataFields(Map dataFields) {
		this.dataFields = dataFields;
	}

	@ApiModelProperty(value = "发送时间,格式：YYYY-MM-DD HH:MM:SS", required = true)
	public String getSendAt() {
		return sendAt;
	}

	public void setSendAt(String sendAt) {
		this.sendAt = sendAt;
	}

	@ApiModelProperty(value = "活动编号", required = true)
	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

}
