/** 
 * 项目名称:91营销云
 * 文件名：BcUser.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import lombok.Data;

import java.util.Date;

@Data
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
	private String avatar;
	private String name;
	private String areaNo;
	private String orgNo;
	private String orgName;
}
