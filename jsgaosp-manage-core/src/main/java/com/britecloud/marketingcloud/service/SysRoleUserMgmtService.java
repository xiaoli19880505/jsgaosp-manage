/** 
 * 项目名称:91营销云
 * 文件名：SysRoleUserMgmtService.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;

/**
 * 项目名称:医院罗盘
 * 文件名：SysRoleMgmtService.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
public interface SysRoleUserMgmtService {

    void add(String roleId, String ids);

    void remove(String roleId, String userId);

    Page<BcUser> get(Map paramMap, String sign, Pageable pageable);

    List<BcRole> listRoleByUserId(String userId);
    
	void addAdminRole(String companyId,String userId);

}
