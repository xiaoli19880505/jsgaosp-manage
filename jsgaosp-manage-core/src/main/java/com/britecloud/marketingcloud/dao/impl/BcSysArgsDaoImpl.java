package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcSysArgsDao;
import com.britecloud.marketingcloud.model.BcSysArgs;
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
	public List<BcSysArgs> listSysArgs(BcSysArgs args) {
		String sql = loadSQL("listSysArgs");
		Map<String, String> paramMap = new HashMap<String, String>();
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcSysArgs>(BcSysArgs.class));
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
