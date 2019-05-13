/** 
 * 项目名称:91营销云
 * 文件名：SysRoleMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.SysRoleJdbcDao;
import com.britecloud.marketingcloud.dao.SysRoleUserJdbcDao;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.service.SysRoleMgmtService;
import com.britecloud.marketingcloud.utils.IDUtils;

/**
 * 项目名称:医院罗盘
 * 文件名：SysRoleMgmtServiceImpl.java
 * author:ge
 * 版本信息：
 * 日期：2015年8月4日
 * Copyright 颢云科技 2015
 * 版权所有
 */
@Service
public class SysRoleMgmtServiceImpl implements SysRoleMgmtService {

    @Autowired
    public SysRoleJdbcDao sysRoleJdbcDao;
    @Autowired
    public SysRoleUserJdbcDao sysRoleUserJdbcDao;

    @Override
    public void save(BcRole role) {
        role.setCreateDate(DateUtils.curDateTimeStr());
        role.setRoleId(IDUtils.getId());
        sysRoleJdbcDao.save(role);
    }

    @Override
    public void delete(String roleId) {
        sysRoleJdbcDao.delete(roleId);
        sysRoleUserJdbcDao.remove(roleId);//删除角色下的所有用户
    }

    @Override
    public PageDataResult<BcRole> query(Map paramMap) {
        return sysRoleJdbcDao.query(paramMap);
    }

    @Override
    public void update(BcRole role) {
        role.setUpdateDate(DateUtils.curDateTimeStr());
        sysRoleJdbcDao.update(role);
    }

    @Override
    public BcRole get(String roleId) {
        return sysRoleJdbcDao.get(roleId);
    }

	@Override
	public BcRole getRoleByUserId(String userId) {
		return sysRoleJdbcDao.getRoleByUserId(userId);
	}

	public void initRole(String companyId){
		sysRoleJdbcDao.initRole(companyId);
	}

    @Override
    public BcRole getRoleByRoleId(String roleId) {
        return sysRoleJdbcDao.get(roleId);
    }
}
