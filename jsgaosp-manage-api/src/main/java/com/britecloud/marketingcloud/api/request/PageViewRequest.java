package com.britecloud.marketingcloud.api.request;

import java.util.Date;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PageViewRequest {
    String transcationId;
    String userId;
    String url;
    String pageName;
    String device;
    String deviceModel;
    public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	String screen;
    String ip;
    Date createdAt;
    String companyId;
    
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

    @ApiModelProperty(required = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "网络地址", required = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty(value = "页面名称", required = true)
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    @ApiModelProperty(value = "设备", required = true)
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    @ApiModelProperty(value = "设备", required = true)
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
