/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import java.util.Map;

import com.britecloud.marketingcloud.model.BcUser;

public interface SysUserJdbcDao {

	public void createUser(BcUser user);

	public void updateUser(BcUser user);

	public void deleteUser(String userId);

	public BcUser getUser(String userId);

	BcUser getUserByEmail(String email);
	
	public Map listSysUser(Map params);
	
	public void resetPwd(String userId,String pwd);

	public int getCountByCompanyId(String companyId);
	
	public int checkEmail(String email);
}
