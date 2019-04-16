package com.britecloud.marketingcloud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcSysApplicatonDao;
import com.britecloud.marketingcloud.dao.BcSysApproveDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;

@Repository
public class BcSysApproveDaoImpl extends BaseJdbcDao implements BcSysApproveDao {

	@Override
	public PageDataResult<BcSysApplicationEntity> listApproves(Map params) {
		PageDataResult<BcSysApplicationEntity> pageData = new PageDataResult<BcSysApplicationEntity>();

		String sql = loadSQL("listSysApproves", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcSysApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(BcSysApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public void saveSysArgs(BcSysApplicationEntity args) {
		args.setId(UUIDUtils.generateUUID());
		args.setStatus("02");
		Date date = new Date(); 
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		args.setCreateDate(format.format(date));
		String sql = loadSQL("saveSysApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public int existsArgsKey(BcSysApplicationEntity args) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsArgsKey");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	@Override
	public void updateApprove(BcSysApplicationEntity args) {
		String sql = loadSQL("updateApprove");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void deleteSysApplication(BcSysApplicationEntity args) {
		String sql = loadSQL("deleteSysApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}


}
