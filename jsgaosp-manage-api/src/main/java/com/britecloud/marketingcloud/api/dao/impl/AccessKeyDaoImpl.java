package com.britecloud.marketingcloud.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.api.dao.AccessKeyDao;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.model.BcAccessKey;

@Repository("apiAccessKeyDao")
public class AccessKeyDaoImpl extends BaseJdbcDao implements AccessKeyDao{

	@Override
	public void createAccessKey(BcAccessKey accessKey) {
		String sql = loadSQL("createAccessKey");
		getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(accessKey));
	}

	@Override
	public BcAccessKey findByApiKey(String apiKey) {
		String sql = loadSQL("findByApiKey");
		Map paraMap = new HashMap();
		paraMap.put("apiKey", apiKey);
		List<BcAccessKey> list= getNamedParameterJdbcTemplate().query(sql,paraMap, new BeanPropertyRowMapper(BcAccessKey.class));
		if (list.isEmpty()){
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public void deleteAccessKey(BcAccessKey accessKey) {
		String sql = loadSQL("deleteAccessKey");
		String apiKey = accessKey.getApiKey();
		Map paramMap = new HashMap();
		paramMap.put("apiKey", apiKey);
		getNamedParameterJdbcTemplate().update(sql, paramMap);
		
	}
	
}
