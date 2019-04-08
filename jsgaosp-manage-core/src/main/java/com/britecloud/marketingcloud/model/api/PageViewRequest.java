package com.britecloud.marketingcloud.model.api;

import java.util.Date;
import java.util.Map;



public class PageViewRequest {
    String transcationId;
    String userId;
    String url;
    String pageName;
    String device;
    String deviceModel;
    String screen;
    String ip;
    Date createdAt;
    
    
    Map dataFields;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
   
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
   
    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map getDataFields() {
        return dataFields;
    }

    public void setDataFields(Map dataFields) {
        this.dataFields = dataFields;
    }

}
