/** 
 * 项目名称:91营销云
 * 文件名：SysCompanyJdbcDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.SysCompanyJdbcDao;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.utils.PageUtils;
@Repository
public class SysCompanyJdbcDaoImpl extends BaseJdbcDao implements SysCompanyJdbcDao {
	
	@Override
	public List<BcCompany> loadCompany() {
		String sql=loadSQL("getCompanyList");
		return getNamedParameterJdbcTemplate().query(sql, new BeanPropertyRowMapper(BcCompany.class));
	}

	@Override
	public Map loadCompany(Map params) {
		Map pageData = new HashMap();
		String sql=loadSQL("getCompanyList");
		if(params.get("queryName")!=null){
			sql += "AND (NAME LIKE '%"+params.get("queryName")+"%' OR DESCRIPTION LIKE '%"+params.get("queryName")+"%')";
		}
		sql += " ORDER BY CREATE_DATE DESC";
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
        pageData.put("totalCount", totalCount);
        pageData.put("totalPage", PageUtils.getTotalPage(totalCount));
        
        sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
        List<BcCompany> list = getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(BcCompany.class));
        pageData.put("data", list);
		return pageData;
	}

	@Override
	public BcCompany getCompanyInfo(String companyId) {
		String sql=loadSQL("getCompanyInfo");
		Map paramMap=new HashMap();
		paramMap.put("companyId", companyId);
		Collection list = getNamedParameterJdbcTemplate().query(sql, paramMap,new BeanPropertyRowMapper(BcCompany.class));
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (BcCompany) list.iterator().next();
	}

	@Override
	public void addCompany(BcCompany company) {
		String sql=loadSQL("addCompany");
		Map paramMap=new HashMap();
		paramMap.put("companyId",company.getCompanyId() );
		paramMap.put("name",company.getName() );
		paramMap.put("description",company.getDescription() );
		paramMap.put("createDate",company.getCreateDate() );
		paramMap.put("createBy",company.getCreateBy() );
		paramMap.put("updateDate",company.getUpdateDate() );
		paramMap.put("updateBy",company.getUpdateBy() );
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public void deleteCompany(String companyId) {
		String sql=loadSQL("deleteCompany");
		Map paramMap=new HashMap();
		paramMap.put("companyId", companyId);
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public void saveCompany(BcCompany company) {
		String sql=loadSQL("saveCompany");
		Map paramMap=new HashMap();
		if(StringUtils.isNotEmpty(company.getName())){
			paramMap.put("name", company.getName());
			sql +=" NAME = :name,";
		}
		if(StringUtils.isNotEmpty(company.getDescription())){
			paramMap.put("description", company.getDescription());
			sql +=" DESCRIPTION = :description,";
		}
		sql += "UPDATE_DATE = :updateDate , UPDATE_BY = :updateBy ";
		paramMap.put("updateDate", company.getUpdateDate());
		paramMap.put("updateBy", company.getUpdateBy());
		
		paramMap.put("companyId", company.getCompanyId());
		sql+=" WHERE COMPANY_ID = :companyId";
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public void initPara(String companyId) {
		String sql=loadSQL("initPara");
		Map paramMap=new HashMap();
		paramMap.put("companyId", companyId);
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

}
