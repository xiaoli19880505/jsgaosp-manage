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

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.SysRoleUserJdbcDao;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.model.BcRoleUser;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;

@Repository
public class SysRoleUserJdbcDaoImpl extends BaseJdbcDao implements SysRoleUserJdbcDao {

    @Override
    public Page<BcUser> get(Map params, String sign, Pageable pageable) {
        Page<BcUser> page = new Page<BcUser>();
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("roleId", roleId);
        String sql = "";
        if ("1".equals(sign)) {
            sql = loadSQL("listDistributedUser", params);
        } else {
//            query = "".equals(query) ? null : query;
//            params.put("query", query);
            sql = loadSQL("listUnDistributedUser", params);
        }
        if("MAINTAIN".equals(params.get("roleType"))){
        	sql += " AND BC_USER.USER_TYPE = 'MAINTAIN' ";
        }else{
        	sql +=" AND (BC_USER.USER_TYPE = 'USER' OR BC_USER.USER_TYPE = 'ADMIN')";
        }
        sql += " ORDER BY CREATE_DATE DESC";
        Integer totalItems = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        page.setTotalItems(totalItems);
        sql = getPaginationString(sql, pageable.getPage() * pageable.getSize(), pageable.getSize());
        List<BcUser> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcUser.class));
        page.setContent(list);
        return page;
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
