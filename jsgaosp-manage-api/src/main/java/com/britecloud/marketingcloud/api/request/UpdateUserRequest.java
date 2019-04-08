package com.britecloud.marketingcloud.api.request;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateUserRequest {

	String userId;
	JSONObject dataFields;
	String companyId;
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
	@ApiModelProperty(value = "数据域，格式：JSONObject,", required = true)
	public JSONObject getDataFields() {
		return dataFields;
	}

	public void setDataFields(JSONObject dataFields) {
		this.dataFields = dataFields;
	}

}
