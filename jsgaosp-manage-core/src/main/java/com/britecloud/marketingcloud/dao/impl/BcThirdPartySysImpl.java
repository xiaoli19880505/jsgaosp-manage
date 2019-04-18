package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcThirdPartySysDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.expand.BcThirdPartySysEntityExpand;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BcThirdPartySysImpl extends BaseJdbcDao implements BcThirdPartySysDao {


	@Override
	public PageDataResult<BcThirdPartySysEntityExpand> listThirdPartySys(Map params) {
		PageDataResult<BcThirdPartySysEntityExpand> pageData = new PageDataResult<BcThirdPartySysEntityExpand>();

		String sql = loadSQL("listThirdPartySys", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcThirdPartySysEntityExpand> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(BcThirdPartySysEntityExpand.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public void saveSysArgs(BcThirdPartySysEntity args) {
		args.setId(UUIDUtils.generateUUID());
		String sql = loadSQL("saveThirdPartySys");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public int existsArgsKey(BcThirdPartySysEntity args) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsArgsKey");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	@Override
	public void updateThirdPartySys(BcThirdPartySysEntity args) {
		String sql = loadSQL("updateThirdPartySys");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void deleteThirdPartySys(BcThirdPartySysEntity args) {
		String sql = loadSQL("deleteThirdPartySys");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	@Override
	public void approveSysAppliant(BcThirdPartySysEntity args) {
		String sql = loadSQL("approveSysApplicant");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}
}
