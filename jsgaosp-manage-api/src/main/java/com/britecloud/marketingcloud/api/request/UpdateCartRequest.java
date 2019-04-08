package com.britecloud.marketingcloud.api.request;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateCartRequest {
	private String userId;
	private String transcationId;
	String createdAt;
	String companyId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private JSONObject item;

	private List<JSONObject> items;

	@ApiModelProperty(value = "用户的编号", required = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "购物车中的商品列表", required = true)
	public String getTranscationId() {
		return transcationId;
	}

	public void setTranscationId(String compaignId) {
		this.transcationId = compaignId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public JSONObject getItem() {
		return item;
	}

	public void setItem(JSONObject item) {
		this.item = item;
	}

	public List<JSONObject> getItems() {
		return items;
	}

	public void setItems(List<JSONObject> items) {
		this.items = items;
	}

}
