package com.britecloud.marketingcloud.model.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TrackViewItemRequest {
    String userId;

    String transcationId;
    String createdAt;
    List<JSONObject> items;

    BigDecimal total;
    Map dataFields;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
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

    // JSONObject dataFields;

}
