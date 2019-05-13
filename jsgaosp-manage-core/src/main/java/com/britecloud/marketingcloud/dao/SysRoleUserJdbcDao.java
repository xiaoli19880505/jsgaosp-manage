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

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.*;

public interface SysRoleUserJdbcDao {


    PageDataResult<BcUser> listUserByRoleId(Map params);
    PageDataResult<BcUser> getrUserListNotInThisRole(Map params);

    List<BcUser> get(String roleId);

    void remove(String roleId, String userId);

    void remove(String roleId);

    void add(String roleId, String[] ids);

    List<BcRole> listRoleByUserId(String userId);

	void addAdminRole(String companyId,String userId);

	int countUserByRoleId(String roleId);
}
