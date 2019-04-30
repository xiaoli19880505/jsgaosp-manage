package com.britecloud.marketingcloud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.britecloud.marketingcloud.utils.MultiJdbcDao;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;

@Repository
public class ApplicatonDaoImpl extends BaseJdbcDao implements ApplicatonDao {

	@Override
	public void saveApplication(ApplicationEntity args) {
		String id=UUIDUtils.generateUUID();
		args.setId(id);
		args.setStatus("1");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		args.setCreate_date(format.format(date));
		String sql = loadSQL("insertApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
		
		String sql2 = loadSQL("insertApplicationInfo");
		args.setApp_id(id);
		args.setVersion_status("1");
		args.setVersion("1.0");
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
	public void updateApplicationInfo(ApplicationEntity args) {
		String sql = loadSQL("updateApplicationInfo");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("app_id",args.getId());
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("version_status",args.getVersion_status());
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	
	@Override
	public void updateStatus(ApplicationEntity args) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("app_id",args.getId());
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("version_status",args.getVersion_status());
		paramMap.put("id",args.getId());
		paramMap.put("status",args.getStatus());
		//更新主表状态
		String sql = loadSQL("updateApplication");
		getNamedParameterJdbcTemplate().update(sql, paramMap);
		//更新详细表状态
		String sqlinfo = loadSQL("updateInfoStatus");
		getNamedParameterJdbcTemplate().update(sqlinfo, paramMap);
	}
	
	
	@Override
	public void updateApplication(ApplicationEntity args) {
		String sql = loadSQL("updateApplication");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getId());
		paramMap.put("status",args.getStatus());
		getNamedParameterJdbcTemplate().update(sql, paramMap);
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
