package com.britecloud.marketingcloud.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateSubscriptionsRequest {
	private String userId;
	private String unsubscribedChannelIds;
	private String unsubscribedMessageTypeIds;
	private String companyId;
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@ApiModelProperty(value = "用户的编号", required = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "", required = true)
	public String getUnsubscribedChannelIds() {
		return unsubscribedChannelIds;
	}

	public void setUnsubscribedChannelIds(String unsubscribedChannelIds) {
		this.unsubscribedChannelIds = unsubscribedChannelIds;
	}

	@ApiModelProperty(value = "设备", required = true)
	public String getUnsubscribedMessageTypeIds() {
		return unsubscribedMessageTypeIds;
	}

	public void setUnsubscribedMessageTypeIds(String unsubscribedMessageTypeIds) {
		this.unsubscribedMessageTypeIds = unsubscribedMessageTypeIds;
	}

}
