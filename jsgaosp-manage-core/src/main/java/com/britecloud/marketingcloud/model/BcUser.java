/** 
 * 项目名称:91营销云
 * 文件名：BcUser.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import java.util.Date;

public class BcUser {
	
	public static final String DEFAULT_PASSWORD="123456";//默认密码
	
	private String userId;
	private String companyId;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userOfficePhone;
	private String userMobile;
	private String userQq;
	private String retired;//0：过期	1：未过期
	private String userType;//MAINTAIN  系统运维人员,ADMIN  公司管理员,USER     普通用户
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;
	
	private String name;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserOfficePhone() {
		return userOfficePhone;
	}
	public void setUserOfficePhone(String userOfficePhone) {
		this.userOfficePhone = userOfficePhone;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getRetired() {
		return retired;
	}
	public void setRetired(String retired) {
		this.retired = retired;
	}
	
}
