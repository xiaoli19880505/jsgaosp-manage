/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl.pay;

import com.britecloud.marketingcloud.consants.pay.CommonConstants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.pay.AccessConfDao;
import com.britecloud.marketingcloud.model.pay.AccessConfPO;
import com.britecloud.marketingcloud.model.pay.AccessConfVO;
import com.britecloud.marketingcloud.utils.PageUtils;
import org.apache.commons.lang.ClassUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccessConfDaoImpl extends BaseJdbcDao implements AccessConfDao {

    public void create(AccessConfPO payAppConf) {
        String sql = loadSQL("create", CommonConstants.PACKAGE_NAME);
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(payAppConf);
        getNamedParameterJdbcTemplate().update(sql, parameters);

    }

    public void update(AccessConfPO sys) {
        String sql = loadSQL("update", CommonConstants.PACKAGE_NAME);
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(sys);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    public void delete(String id) {
        String sql = loadSQL("delete", CommonConstants.PACKAGE_NAME);
        Map paramMap = new HashMap();
        paramMap.put("id", id);
        getNamedParameterJdbcTemplate().update(sql, paramMap);
    }

    public AccessConfPO get(String id) {
        String sql = loadSQL("get", CommonConstants.PACKAGE_NAME);
        Map paramMap = new HashMap();
        paramMap.put("id", id);
        Collection list = getNamedParameterJdbcTemplate().query(sql, paramMap,
                new BeanPropertyRowMapper(AccessConfPO.class));

        if (list == null || list.isEmpty()) {
            return null;
        }
        return (AccessConfPO) list.iterator().next();
    }


    /**
     * 分页查询用户列表
     *
     * @param params
     * @return
     */
    public Map list(Map params) {
        Map pageData = new HashMap();
        String sql = loadSQL("list", params, CommonConstants.PACKAGE_NAME);
        Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.put("totalCount", totalCount);
        pageData.put("totalPage", PageUtils.getTotalPage(totalCount));

        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<AccessConfVO> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(AccessConfVO.class));
        pageData.put("data", list);
        return pageData;
    }

}