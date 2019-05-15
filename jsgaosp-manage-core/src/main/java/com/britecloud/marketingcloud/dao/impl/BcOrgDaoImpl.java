package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.BcOrgDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.utils.PageUtils;
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
		String sql = loadSQL("listOrgWithNoType");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("pOrgNo",pAreaNo);
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcOrg>(BcOrg.class));
	}

	@Override
	public PageDataResult<BcOrg> listDepartmentByOrgId(Map params) {
		PageDataResult<BcOrg> pageData = new PageDataResult<BcOrg>();
		String sql = loadSQL("listDepartByOrgId", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));
		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<BcOrg> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcOrg.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public void saveOrg(BcOrg org) {
		org.setOrgNo(UUIDUtils.generateUUID());
		org.setStatus(Constants.STATUS_ENABLE);
		String sql = loadSQL("saveOrg");
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
		String sql = loadSQL("existsChildrenOrgName");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(org);
		return getNamedParameterJdbcTemplate().queryForInt(sql,parameters);
	}

	@Override
	public List<BcOrg> getOrgAreaNameList() {
		String sql = loadSQL("getOrgAreaNameList");
		Map<String, String> paramMap = new HashMap<String, String>();
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcOrg>(BcOrg.class));
	}

	@Override
	public BcOrg queryOrgById(String orgNo) {
		String sql = loadSQL("queryOrgById");
		Map paramMap = new HashMap();
		paramMap.put("orgNo", orgNo);
		List<BcOrg> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcOrg.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.iterator().next();
	}

	@Override
	public List<BcOrg> queryOrgListByPid(String orgNo) {
		String sql = loadSQL("queryOrgListByPid");
		Map paramMap = new HashMap();
		paramMap.put("orgNo", orgNo);
		List<BcOrg> list = getNamedParameterJdbcTemplate().query(sql, paramMap,
				new BeanPropertyRowMapper(BcOrg.class));
		return list;
	}

}
