package com.britecloud.marketingcloud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.ApplicatonDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;

@Repository
public class ApplicatonDaoImpl extends BaseJdbcDao implements ApplicatonDao {

	@Override
	public void saveApplication(ApplicationEntity args) {
		String id=UUIDUtils.generateUUID();
		args.setId(id);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		args.setCreate_date(format.format(date));
		String sql = loadSQL("insertApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
		
		String sql2 = loadSQL("insertApplicationInfo");
		args.setApp_id(id);
		args.setInfo_id(UUIDUtils.generateUUID());
		SqlParameterSource parameters2 = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql2, parameters);
	}


	@Override
	public int existsArgsKey(ApplicationEntity args) {
		// TODO Auto-generated method stub
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsArgsKey");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	@Override
	public int existsApplicationInfo(ApplicationEntity args) {
		// TODO Auto-generated method stub
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsApplicationInfo");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	@Override
	public void updateApplication(ApplicationEntity args) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		args.setApproval_date(format.format(date));
		String sql = loadSQL("updateApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public PageDataResult<ApplicationEntity> listSysApplications(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("listApplication", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}
}
