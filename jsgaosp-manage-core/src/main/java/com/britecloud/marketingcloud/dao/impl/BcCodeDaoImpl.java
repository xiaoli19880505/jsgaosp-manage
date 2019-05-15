package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcCodeDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称:省公安
 * 文件名：BcCodeDaoImpl.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-12
 * Copyright 紫金数云 2019 版权所有
 */
@Repository
public class BcCodeDaoImpl extends BaseJdbcDao implements BcCodeDao {

	@Override
	public PageDataResult<BcCodeSort> listCodeSort(Map params) {
		PageDataResult<BcCodeSort> pageData = new PageDataResult<BcCodeSort>();

		String sql = loadSQL("listCodeSort", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcCodeSort> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcCodeSort.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public PageDataResult<BcCodeSort> listCodeSortDetailBypCodeSortId(Map params) {
		PageDataResult<BcCodeSort> pageData = new PageDataResult<BcCodeSort>();

		String sql = loadSQL("listCodeSortDetailBypCodeSortId", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcCodeSort> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcCodeSort.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public BcCodeSort getCodeSortById(BcCodeSort codeSort) {
		String sql = loadSQL("getCodeSortByPId");
		Map paramMap = new HashMap();
		paramMap.put("codeSortId", codeSort.getCodeSortId());
		List<BcCodeSort> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcCodeSort.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public BcCodeSort getCodeSortBypOrgNo(BcCodeSort codeSort) {
		return null;
	}

	@Override
	public List<BcCodeSort> getCodeSortByKey(Map params) {
		String sql = loadSQL("getCodeSortDetailByKey", params);
		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcCodeSort> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcCodeSort.class));

		return list;
	}

	@Override
	public void saveCodeSort(BcCodeSort codeSort) {
		codeSort.setCodeSortId(UUIDUtils.generateUUID());
		codeSort.setStatus(Constants.STATUS_ENABLE);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		codeSort.setCreateTime(format.format(date));
		String sql = loadSQL("saveCodeSort");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(codeSort);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void updateCodeSort(BcCodeSort codeSort) {
		String sql = loadSQL("updateCodeSort");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		codeSort.setUpdateTime(format.format(date));
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(codeSort);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void deleteCodeSort(BcCodeSort codeSort) {
		String sql = loadSQL("deleteCodeSort");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(codeSort);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public List<BcCodeSort> getCodeList(String sortCode) {
		String sql = loadSQL("getCodeSortDetailByKey");
		Map paramMap = new HashMap();
		paramMap.put("codeSortKey", sortCode);
		List<BcCodeSort> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcCodeSort.class));
		return list;
	}

}
