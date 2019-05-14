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
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcSysApplicationEntity;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;

@Repository
public class BcSysApproveDaoImpl extends BaseJdbcDao implements BcSysApproveDao {

	
	
	@Override
	public PageDataResult<ApplicationEntity> listSysApproves(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("listSysApproves", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}

	

	public void updateAudit(ApplicationEntity args) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getInfo_id());
		paramMap.put("approval_status",args.getApproval_status());
		paramMap.put("working_status",args.getWorking_status());
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("approval_opinion",args.getApproval_opinion());
		String sql = loadSQL("updateAudit");
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}


}
