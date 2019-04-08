/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.SysUserJdbcDao;
import com.britecloud.marketingcloud.model.BcUser;
import com.britecloud.marketingcloud.utils.PageUtils;

@Repository
public class SysUserJdbcDaoImpl extends BaseJdbcDao implements SysUserJdbcDao {

    public void createUser(BcUser user) {
        String sql = loadSQL("createUser");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        getNamedParameterJdbcTemplate().update(sql, parameters);

    }

    public void updateUser(BcUser sysUser) {
        String sql = loadSQL("updateUser");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(sysUser);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    public void deleteUser(String sysUserId) {
        String sql = loadSQL("deleteUser");
        Map paramMap = new HashMap();
        paramMap.put("userId", sysUserId);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    public BcUser getUser(String sysUserId) {
        String sql = loadSQL("getUser");
        Map paramMap = new HashMap();
        paramMap.put("userId", sysUserId);
        Collection list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(BcUser.class));

        if (list == null || list.isEmpty()) {
            return null;
        }
        return (BcUser) list.iterator().next();
    }


    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    public Map listSysUser(Map params) {
        Map pageData = new HashMap();
        String sql = loadSQL("listUser", params);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.put("totalCount", totalCount);
        pageData.put("totalPage", PageUtils.getTotalPage(totalCount));

        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<BcUser> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcUser.class));
        pageData.put("data", list);
        return pageData;
    }

    /**
     * 重置密码：默认为123456
     */
    public void resetPwd(String userId, String pwd) {
        String sql = loadSQL("resetPwd");
        Map paramMap = new HashMap();
        paramMap.put("userId", userId);
        paramMap.put("pwd", pwd);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    @Override
    public BcUser getUserByEmail(String email) {
        String sql = loadSQL("getUserByEmail");
        Map paramMap = new HashMap();
        paramMap.put("email", email);
        List<BcUser> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(BcUser.class));

        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

	@Override
	public int getCountByCompanyId(String companyId) {
		 String sql = loadSQL("getCountByCompanyId");
		 Map paramMap = new HashMap();
	     paramMap.put("companyId", companyId);
	     return getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
	
	@Override
	public int checkEmail(String email) {
		 String sql = loadSQL("checkEmail");
		 Map paramMap = new HashMap();
	     paramMap.put("email", email);
	     return getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
}
