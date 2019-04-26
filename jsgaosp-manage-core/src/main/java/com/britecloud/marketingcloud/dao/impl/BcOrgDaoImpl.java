package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.BcOrgDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BcOrgDaoImpl extends BaseJdbcDao implements BcOrgDao {

	@Override
	public List<BcOrg> listOrg(String pAreaNo) {
		String sql = loadSQL("listOrg");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("pOrgNo",pAreaNo);
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcOrg>(BcOrg.class));
	}

	@Override
	public void saveOrg(BcOrg org) {
		org.setOrgNo(UUIDUtils.generateUUID());
		org.setStatus(Constants.STATUS_ENABLE);
		String sql = loadSQL("saveArea");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(org);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void updateOrg(BcOrg org) {
		String sql = loadSQL("updateOrg");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(org);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void deleteOrg(BcOrg org) {
		String sql = loadSQL("deleteOrg");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(org);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public BcOrg getOrgById(String id) {
		String sql = loadSQL("getOrgById");
		Map paramMap = new HashMap();
		paramMap.put("orgNo", id);
		List<BcOrg> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcOrg.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public Map<String, Object> getOrgByOrgNo(String orgNo) {
		String sql = loadSQL("getOrgByOrgNo");
		Map paramMap = new HashMap();
		paramMap.put("orgNo", orgNo);
		Map<String,Object> objectMap = getNamedParameterJdbcTemplate().queryForMap(sql, paramMap);

		return objectMap;
	}

	@Override
	public int existsOrgName(BcOrg org) {
		String sql = loadSQL("existsOrGName");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(org);
		return getNamedParameterJdbcTemplate().queryForInt(sql,parameters);
	}



}
