/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   AccessKeyDaoImpl.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.dao.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.AccessKeyDao;
import com.britecloud.marketingcloud.model.BcAccessKey;
import com.britecloud.marketingcloud.model.Page;
import com.britecloud.marketingcloud.model.Pageable;

/**
 * The Class AccessKeyDaoImpl.
 */
@Repository
public class AccessKeyDaoImpl extends BaseJdbcDao implements AccessKeyDao{

	/* (non-Javadoc)
	 * @see com.britecloud.accessKey.dao.AccessKeyDao#createAccessKey(com.britecloud.model.BcAccessKey)
	 */
	@Override
	public void createAccessKey(BcAccessKey accessKey) {
		String sql = loadSQL("createAccessKey");
		getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(accessKey));
	}

	/* (non-Javadoc)
	 * @see com.britecloud.accessKey.dao.AccessKeyDao#findByApiKey(java.lang.String)
	 */
	@Override
	public BcAccessKey findByApiKey(String companyId,String apiKey) {
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
	public BcAccessKey findByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		String sql = loadSQL("findByCompanyId");
		Map paraMap = new HashMap();
		paraMap.put("companyId",companyId);
		List<BcAccessKey> list= getNamedParameterJdbcTemplate().query(sql,paraMap, new BeanPropertyRowMapper(BcAccessKey.class));
		if (list.isEmpty()){
			return null;
		}
		return list.iterator().next();
	}
	/* (non-Javadoc)
	 * @see com.britecloud.accessKey.dao.AccessKeyDao#deleteAccessKey(java.lang.String)
	 */
	@Override
	public void deleteAccessKey(String companyId,String apiKeys) {
		String sql = loadSQL("deleteAccessKey");
		Map paramMap = new HashMap();
		paramMap.put("companyId", companyId);
		sql=sql.replaceAll("#apiKeys#", apiKeys);
		getNamedParameterJdbcTemplate().update(sql,paramMap);
		
	}

	/* (non-Javadoc)
	 * @see com.britecloud.accessKey.dao.AccessKeyDao#findAllAccessKey(java.lang.String, java.lang.String, com.britecloud.model.Pageable)
	 */
	@Override
	//
	public Page<BcAccessKey> findAllAccessKey(String companyId,String query,Pageable pageable) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyId", companyId);
		String sql="";
		if(query.equals("")||query==null){
			sql=loadSQL("findAllAccessKey",paramMap);
		}else{
			paramMap.put("query", query);
			sql=loadSQL("findAllAccessKey",paramMap);
			sql=sql.replaceAll("#query#", "'%"+query+"%'");
		}
		Integer totalItems =getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), paramMap);
		Page<BcAccessKey> page = new Page<BcAccessKey>();
		page.setTotalItems(totalItems);
		sql = getPaginationString(sql, pageable.getPage() * pageable.getSize(), pageable.getSize());
		List<BcAccessKey> list = getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(BcAccessKey.class));
		page.setContent(list);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.britecloud.accessKey.dao.AccessKeyDao#updateAccesskey(com.britecloud.model.BcAccessKey)
	 */
	@Override
	public void updateAccesskey(String companyId,BcAccessKey accessKey) {
		String sql = loadSQL("updateAccesskey");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("companyId", companyId);
		paramMap.put("apiKey", accessKey.getApiKey());
		getNamedParameterJdbcTemplate().update(sql,new BeanPropertySqlParameterSource(accessKey));
	}



}
