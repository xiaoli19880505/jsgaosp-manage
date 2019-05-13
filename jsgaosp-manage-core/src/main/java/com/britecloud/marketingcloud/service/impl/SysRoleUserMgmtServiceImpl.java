/** 
 * 项目名称:91营销云
 * 文件名：SysRoleUserMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.SysRoleUserJdbcDao;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;
import com.britecloud.marketingcloud.service.SysRoleUserMgmtService;

/**
 * 项目名称:医院罗盘
 * 文件名：SysRoleMgmtService.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
@Service
public class SysRoleUserMgmtServiceImpl implements SysRoleUserMgmtService {

    @Autowired
    public SysRoleUserJdbcDao sysRoleUserJdbcDao;



    @Override
    public void remove(String roleId, String userId) {
        sysRoleUserJdbcDao.remove(roleId, userId);
       // sysUserOrgJdbcDao.remove(userId);//删除用户的关联机构
    }

    @Override
    public PageDataResult<BcUser> getUserListByRoleId(Map params) {
        return sysRoleUserJdbcDao.listUserByRoleId(params);
    }

    @Override
    public PageDataResult<BcUser> getrUserListNotInThisRole(Map params) {
        return sysRoleUserJdbcDao.getrUserListNotInThisRole(params);
    }

    @Override
    public void add(String roleId, String ids) {
        sysRoleUserJdbcDao.add(roleId, ids.split(","));
    }

    @Override
    public List<BcRole> listRoleByUserId(String userId) {
        return sysRoleUserJdbcDao.listRoleByUserId(userId);
    }
    /**
     * 为此用户添加ADMIN角色
     */
	@Override
	public void addAdminRole(String companyId,String userId) {
		sysRoleUserJdbcDao.addAdminRole(companyId,userId);
	}

    @Override
    public int CountUserByRoleId(String roleId) {

	    return sysRoleUserJdbcDao.countUserByRoleId(roleId);
    }

}
