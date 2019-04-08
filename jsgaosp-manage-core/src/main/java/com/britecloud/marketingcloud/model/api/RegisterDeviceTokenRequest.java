package com.britecloud.marketingcloud.model.api;

import java.util.Map;




public class RegisterDeviceTokenRequest {
	String userId;
	DeviceInfo device;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
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

		
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		
		public String getPlatform() {
			return platform;
		}

		public void setPlatform(String platform) {
			this.platform = platform;
		}

		
		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		
		public Map getDataFields() {
			return dataFields;
		}

		public void setDataFields(Map dataFields) {
			this.dataFields = dataFields;
		}

	}
}
