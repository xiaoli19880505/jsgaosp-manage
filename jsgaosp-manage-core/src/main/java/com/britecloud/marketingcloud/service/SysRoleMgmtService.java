/**
 * 项目名称:91营销云
 * 文件名：SysRoleMgmtService.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcRole;

/**
 * 项目名称:医院罗盘
 * 文件名：SysRoleMgmtService.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
public interface SysRoleMgmtService {

    void save(BcRole role);
    void delete(String roleId);
    PageDataResult<BcRole>  query(Map paramMap);
    void update(BcRole role);
    BcRole get(String roleId);

    public BcRole getRoleByUserId(String userId);

    public void initRole(String companyId);

    BcRole getRoleByRoleId(String roleId);

}
