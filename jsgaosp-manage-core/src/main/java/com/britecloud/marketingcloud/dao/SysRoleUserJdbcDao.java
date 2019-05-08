/** 
 * 项目名称:91营销云
 * 文件名：SysRoleUserJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;

public interface SysRoleUserJdbcDao {

    Page<BcUser> get(Map paramMap, String sign, Pageable pageable);

    List<BcUser> get(String roleId);

    void remove(String roleId, String userId);

    void remove(String roleId);

    void add(String roleId, String[] ids);

    List<BcRole> listRoleByUserId(String userId);

	void addAdminRole(String companyId,String userId);

	int countUserByRoleId(String roleId);
}
