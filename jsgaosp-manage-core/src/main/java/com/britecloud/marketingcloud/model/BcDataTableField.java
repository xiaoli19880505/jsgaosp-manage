/** 
 * 项目名称:91营销云
 * 文件名：BcDataTableField.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BcDataTableField implements Serializable{
	private String fieldId;
	private String tableId;
	private String companyId;
	private String name;
	private String code;
	private String description;
	private String fieldType;
	private String type;
	private int length;
	private String isUserid;
	private String defaultValue;
	private String enumValue;
	private String funcDefine;
	private int sortOrder;
	private String cycle;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateDate;
	private String retired;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createDate;
	private String updateBy;
	private String createBy;
	private String query;
	
	private String preDefined;
	
	
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
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
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getEnumValue() {
		return enumValue;
	}
	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
	public String getFuncDefine() {
		return funcDefine;
	}
	public void setFuncDefine(String funcDefine) {
		this.funcDefine = funcDefine;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	//@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRetired() {
		return retired;
	}
	public void setRetired(String retired) {
		this.retired = retired;
	}
	public Date getCreateDate() {
		return createDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getIsUserid() {
		return isUserid;
	}
	public void setIsUserid(String isUserid) {
		this.isUserid = isUserid;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPreDefined() {
		return preDefined;
	}
	public void setPreDefined(String preDefined) {
		this.preDefined = preDefined;
	}
	
	
	
}
