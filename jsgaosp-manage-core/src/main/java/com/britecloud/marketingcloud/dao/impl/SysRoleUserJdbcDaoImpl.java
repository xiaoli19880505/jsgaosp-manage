/** 
 * 项目名称:91营销云
 * 文件名：SysRoleUserJdbcDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.*;
import com.britecloud.marketingcloud.utils.PageUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.SysRoleUserJdbcDao;

@Repository
public class SysRoleUserJdbcDaoImpl extends BaseJdbcDao implements SysRoleUserJdbcDao {


    @Override
    public PageDataResult<BcUser> listUserByRoleId(Map params) {

        PageDataResult<BcUser> pageData = new PageDataResult<BcUser>();
        String sql = loadSQL("listUserByRoleId", params);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.setTotalCount(totalCount);
        pageData.setTotalPage(PageUtils.getTotalPage(totalCount));
        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<BcUser> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcUser.class));
        pageData.setList(list);
        return pageData;
    }

    @Override
    public PageDataResult<BcUser> getrUserListNotInThisRole(Map params) {
        PageDataResult<BcUser> pageData = new PageDataResult<BcUser>();
        String sql = loadSQL("getrUserListNotInThisRole", params);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.setTotalCount(totalCount);
        pageData.setTotalPage(PageUtils.getTotalPage(totalCount));
        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<BcUser> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcUser.class));
        pageData.setList(list);
        return pageData;
    }

    @Override
    public List<BcUser> get(String roleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("roleId", roleId);
        String sql = loadSQL("listUnDistributedUser", params);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcUser.class));
    }

    @Override
    public int countUserByRoleId(String roleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("roleId", roleId);
        String sql = loadSQL("countUserByRoleId", params);
        return getNamedParameterJdbcTemplate().queryForInt(sql, params);
    }

    @Override
    public void remove(String roleId, String userId) {
        String sql = loadSQL("removeUserRole");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("roleId", roleId);
        paramMap.put("userId", userId);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    @Override
    public void remove(String roleId) {
        String sql = loadSQL("removeUserRoleByRoleId");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("roleId", roleId);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    @Override
    public void add(String roleId, String[] ids) {
        String sql = loadSQL("addUserRole");
        SqlParameterSource[] roleUser = new SqlParameterSource[ids.length];
        BcRoleUser sysRoleUser;
        for (int i = 0; i < ids.length; i++) {
            sysRoleUser = new BcRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(ids[i]);
            roleUser[i] = new BeanPropertySqlParameterSource(sysRoleUser);
        }
        getNamedParameterJdbcTemplate().batchUpdate(sql, roleUser);
    }

    @Override
    public List<BcRole> listRoleByUserId(String userId) {
        String sql = loadSQL("listRoleByUserId");
        Map paramMap = new HashMap();
        paramMap.put("userId", userId);
        return getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(BcRole.class));
    }

	public void addAdminRole(String companyId,String userId) {
		 String sql = loadSQL("addAdminRole");
		 Map paramMap = new HashMap();
		 paramMap.put("companyId",companyId);
		 paramMap.put("userId",userId);
		 getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

}
