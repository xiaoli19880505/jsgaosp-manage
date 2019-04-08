/** 
 * 项目名称:91营销云
 * 文件名：BcCompany.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import java.util.Date;
import java.util.List;

public class BcCompany {
	private String companyId;
	private String name;
	private String description;
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;
	
	private String label;
	//private List<BcCompany> children;
	
	
	
	public String getLabel() {
		return name;
	}
	public void setLabel(String label) {
		this.label = label;
	}
//	public List<BcCompany> getChildren() {
//		return children;
//	}
//	public void setChildren(List<BcCompany> children) {
//		this.children = children;
//	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}
