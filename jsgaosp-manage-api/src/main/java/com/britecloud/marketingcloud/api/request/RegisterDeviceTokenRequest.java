package com.britecloud.marketingcloud.api.request;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RegisterDeviceTokenRequest {
	String userId;
	DeviceInfo device;

	@ApiModelProperty(value = "用户的编号", required = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "设备的信息", required = true)
	public DeviceInfo getDevice() {
		return device;
	}

	public void setDevice(DeviceInfo device) {
		this.device = device;
	}

	static class DeviceInfo {
		private String token;
		private String platform;
		private String applicationName;
		private Map dataFields;

		@ApiModelProperty(value = "设备的凭证", required = true)
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		@ApiModelProperty(value = "平台", required = true)
		public String getPlatform() {
			return platform;
		}

		public void setPlatform(String platform) {
			this.platform = platform;
		}

		@ApiModelProperty(value = "程序名称", required = true)
		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		@ApiModelProperty(value = "数据域，格式：Map<String,Object>")
		public Map getDataFields() {
			return dataFields;
		}

		public void setDataFields(Map dataFields) {
			this.dataFields = dataFields;
		}

	}
}
