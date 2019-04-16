package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.model.BcArea;
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
 * 文件名：BcAreaDao.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-9
 * Copyright 紫金数云 2019 版权所有
 */
@Repository
public class BcAreaDaoImpl extends BaseJdbcDao implements BcAreaDao {

	@Override
	public List<BcArea> listArea(String pAreaNo) {
		String sql = loadSQL("listArea");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("pAreaNo",pAreaNo);
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcArea>(BcArea.class));
	}

	@Override
	public void saveArea(BcArea area) {
	    area.setId(UUIDUtils.generateUUID());
	    area.setStatus(Constants.STATUS_ENABLE);
        String sql = loadSQL("saveArea");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);
        getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	/**
	 * 根据areaNo判断是否存在
	 * @param area
	 * @return
	 */
	@Override
	public int existsAreaNo(BcArea area){
		String sql = loadSQL("existsAreaNo");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);
		return getNamedParameterJdbcTemplate().queryForInt(sql,parameters);
	}

    @Override
    public void updateArea(BcArea area) {
        String sql = loadSQL("updateArea");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

	@Override
	public void deleteArea(BcArea area) {
		String sql = loadSQL("deleteArea");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public BcArea getAreaById(String id) {
		String sql = loadSQL("getAreaById");
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		List<BcArea> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcArea.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public Map<String,Object> getAreaByAreaNo(String areaNo) {
		String sql = loadSQL("getAreaByAreaNo");
		Map paramMap = new HashMap();
		paramMap.put("areaNo", areaNo);
		Map<String,Object> objectMap = getNamedParameterJdbcTemplate().queryForMap(sql, paramMap);

		return objectMap;
	}

}
