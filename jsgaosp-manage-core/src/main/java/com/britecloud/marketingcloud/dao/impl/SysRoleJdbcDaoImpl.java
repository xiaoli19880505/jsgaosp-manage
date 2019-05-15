/**
 * 项目名称:91营销云
 * 文件名：SysRoleJdbcDaoImpl.java 
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
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.utils.PageUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.SysRoleJdbcDao;
import com.britecloud.marketingcloud.model.BcRole;
import com.britecloud.marketingcloud.utils.IDUtils;

@Repository
public class SysRoleJdbcDaoImpl extends BaseJdbcDao implements SysRoleJdbcDao {

    @Override
    public void save(BcRole role) {
        String sql = loadSQL("createRole");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(role);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    @Override
    public void delete(String roleId) {
        String sql = loadSQL("deleteRole");
        Map paramMap = new HashMap();
        paramMap.put("roleId", roleId);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    @Override
    public PageDataResult<BcRole> query(Map paramMap) {
//        String sql = loadSQL("queryRole");
//        return getNamedParameterJdbcTemplate().query(sql, paramMap,new BeanPropertyRowMapper(BcRole.class));

        PageDataResult<BcRole> pageData = new PageDataResult<BcRole>();

        String sql = loadSQL("queryRole", paramMap);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), paramMap);
        pageData.setTotalCount(totalCount);
        pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) paramMap.get("page")), PageUtils.pageSize);
        List<BcRole> list = getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(BcRole.class));
        pageData.setList(list);
        return pageData;

    }

    @Override
    public void update(BcRole role) {
        String sql = loadSQL("updateRole");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(role);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    @Override
    public BcRole get(String roleId) {
        String sql = loadSQL("getRole");
        Map paramMap = new HashMap();
        paramMap.put("roleId", roleId);
        List<BcRole> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(BcRole.class));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    @Override
    public BcRole getRoleByUserId(String userId) {
        String sql = loadSQL("getRoleByUserId");
        Map paramMap = new HashMap();
        paramMap.put("userId", userId);
        List<BcRole> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(BcRole.class));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    public void initRole(String companyId) {
        String sql = loadSQL("initRole");
        Map paramMap = new HashMap();
        paramMap.put("companyId", companyId);
        paramMap.put("roleId", IDUtils.getId());
        paramMap.put("roleId1", IDUtils.getId());
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }
}
