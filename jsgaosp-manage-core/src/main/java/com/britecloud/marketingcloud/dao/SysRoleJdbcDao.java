/** 
 * 项目名称:91营销云
 * 文件名：SysRoleJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcRole;

public interface SysRoleJdbcDao {

	void save(BcRole role);
	void delete(String roleId);
	PageDataResult<BcRole> query(Map paramMap);
	void update(BcRole role);
	BcRole get(String roleId);
	BcRole getRoleByUserId(String userId);
	void initRole(String companyId);
}
