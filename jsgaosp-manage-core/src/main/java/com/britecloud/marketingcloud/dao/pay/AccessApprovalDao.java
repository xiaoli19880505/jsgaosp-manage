/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.pay;

import com.britecloud.marketingcloud.model.pay.AccessApprovalPO;

import java.util.Map;

public interface AccessApprovalDao {

	 void create(AccessApprovalPO accessInfo);

	 void update(AccessApprovalPO accessInfo);

	 void delete(String id);

	AccessApprovalPO get(String id);

	 Map list(Map params);
}
