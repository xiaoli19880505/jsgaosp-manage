/** 
 * 项目名称:91营销云
 * 文件名：BcRole.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import lombok.Data;

@Data
public class BcRole {
	private String roleId;
	private String orgId;
	private String companyId;
	private String name;
	private String description;
	private String perm;
	private String roleType;
	private String createDate;
	private String createUserId;
	private String updateDate;
	private String updateUserId;
	private String status;
}
