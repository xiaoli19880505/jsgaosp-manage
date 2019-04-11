package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcSysArgsDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysArgs;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称:省公安
 * 文件名：BcSysArgsDaoImpl.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-9
 * Copyright 紫金数云 2019 版权所有
 */
@Repository
public class BcSysArgsDaoImpl extends BaseJdbcDao implements BcSysArgsDao {

	@Override
	public PageDataResult<BcSysArgs> listSysArgs(Map params) {
//		Map pageData = new HashMap();
		PageDataResult<BcSysArgs> pageData = new PageDataResult<BcSysArgs>();

		String sql = loadSQL("listSysArgs", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcSysArgs> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcSysArgs.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public void saveSysArgs(BcSysArgs args) {
		args.setId(UUIDUtils.generateUUID());
		args.setStatus(Constants.STATUS_ENABLE);
        String sql = loadSQL("saveSysArgs");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
        getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	/**
	 * 根据argsKey判断是否存在
	 * @param args
	 * @return
	 */
	@Override
	public int existsArgsKey(BcSysArgs args){
		String sql = loadSQL("existsArgsKey");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		return getNamedParameterJdbcTemplate().queryForInt(sql,parameters);
	}

    @Override
    public void updateSysArgs(BcSysArgs args) {
        String sql = loadSQL("updateSysArgs");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

	@Override
	public void deleteSysArgs(BcSysArgs args) {
		String sql = loadSQL("deleteSysArgs");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public BcSysArgs getSysArgsById(BcSysArgs args) {
		String sql = loadSQL("getSysArgsById");
		Map paramMap = new HashMap();
		paramMap.put("id", args.getId());
		List<BcSysArgs> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcSysArgs.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.iterator().next();
	}
}
