package com.britecloud.marketingcloud.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcAdviceDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.model.BcAdviceEntity;
import com.britecloud.marketingcloud.utils.PageUtils;

@Repository
public class BcAdviceDaoImpl extends BaseJdbcDao implements BcAdviceDao {
	
	@Override
	public PageDataResult<BcAdviceEntity> listAdvice(Map params) {
		PageDataResult<BcAdviceEntity> pageData = new PageDataResult<BcAdviceEntity>();
		String sql = loadSQL("listAdvice", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcAdviceEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(BcAdviceEntity.class));
		pageData.setList(list);
		return pageData;
	}
}
