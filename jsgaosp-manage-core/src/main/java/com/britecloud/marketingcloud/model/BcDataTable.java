/** 
 * 项目名称:91营销云
 * 文件名：BcDataTable.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class BcDataTable {
	
	public final static String SYNC_STATUS_WAIT="WAIT";
	public final static String SYNC_STATUS_INPROGRESS="INPROGRESS";
	public final static String SYNC_STATUS_FINISHED="FINISHED";
	public final static String SYNC_STATUS_ERROR="ERROR";
	
	private String tableId;
	private String companyId;
	private String name;
	private String code;
	private String description;
	private char retired;//是否过期
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createDate;
	private String createBy;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateDate;
	private String updateBy;
	private String type;
	
	private String syncStatus;
	private Date  syncDate;
	
	private String preDefined;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	private String relation;
	private String parent;
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getRetired() {
		return retired;
	}
	public void setRetired(char retired) {
		this.retired = retired;
	}
	//@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
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
	//@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Date getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	public String getPreDefined() {
		return preDefined;
	}
	public void setPreDefined(String preDefined) {
		this.preDefined = preDefined;
	}
	
	
	
}
