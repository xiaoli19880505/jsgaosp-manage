/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.PayAppConfDao;
import com.britecloud.marketingcloud.model.PayAppConf;
import com.britecloud.marketingcloud.utils.PageUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PayAppConfDaoImpl extends BaseJdbcDao implements PayAppConfDao {

    public void createPayAppConf(PayAppConf payAppConf) {
        String sql = loadSQL("createPayConf");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(payAppConf);
        getNamedParameterJdbcTemplate().update(sql, parameters);

    }

    public void updatePayAppConf(PayAppConf sysPayAppConf) {
        String sql = loadSQL("updatePayAppConf");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(sysPayAppConf);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    public void deletePayAppConf(String id) {
        String sql = loadSQL("deletePayAppConf");
        Map paramMap = new HashMap();
        paramMap.put("id", id);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    public PayAppConf getPayAppConf(String id) {
        String sql = loadSQL("getPayAppConf");
        Map paramMap = new HashMap();
        paramMap.put("id", id);
        Collection list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(PayAppConf.class));

        if (list == null || list.isEmpty()) {
            return null;
        }
        return (PayAppConf) list.iterator().next();
    }


    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    public Map listPayConf(Map params) {
        Map pageData = new HashMap();
        String sql = loadSQL("listPayConf", params);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.put("totalCount", totalCount);
        pageData.put("totalPage", PageUtils.getTotalPage(totalCount));

        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<PayAppConf> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(PayAppConf.class));
        pageData.put("data", list);
        return pageData;
    }

}
