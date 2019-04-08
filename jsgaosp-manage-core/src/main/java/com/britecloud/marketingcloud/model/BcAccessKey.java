/** 
 * 项目名称:91营销云
 * 文件名：BcAccessKey.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BcAccessKey {
	private String companyId;
	private String apiKey;
	private String name;
	private String description;
	private String publicKey;
	private String privateKey;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createDate;
	private String createBy;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String id) {
		this.apiKey = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String accessKey) {
		this.publicKey = accessKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String accessSecret) {
		this.privateKey = accessSecret;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
