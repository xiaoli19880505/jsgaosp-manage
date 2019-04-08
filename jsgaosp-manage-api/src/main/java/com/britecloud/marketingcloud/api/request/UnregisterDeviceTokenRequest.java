package com.britecloud.marketingcloud.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UnregisterDeviceTokenRequest {
	private String userId;
	private String token;

	@ApiModelProperty(value = "用户的编号", required = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "设备的token", required = true)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
