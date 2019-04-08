package com.britecloud.marketingcloud.api.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TrackPurchaseRequest {
	String userId;
	String transcationId;
	String createdAt;
	
	List<JSONObject> items;
	
	BigDecimal total;
	Map dataFields;
	
	String companyId;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(String campaignId) {
		this.transcationId = campaignId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<JSONObject> getItems() {
		return items;
	}
	public void setItems(List<JSONObject> items) {
		this.items = items;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Map getDataFields() {
		return dataFields;
	}
	public void setDataFields(Map dataFields) {
		this.dataFields = dataFields;
	}
	
	
	
	
}
