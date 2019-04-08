/** 
 * 项目名称:91营销云
 * 文件名：BCCompanyDaoImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BCCompanyDao;
import com.britecloud.marketingcloud.model.BcCompany;



@Repository
public class BCCompanyDaoImpl extends BaseJdbcDao implements BCCompanyDao {

	@Override
	public List<BcCompany> listCompany() {
		String sql = loadSQL("listCompany");
		Map<String, String> paramMap = new HashMap<String, String>();
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<BcCompany>(BcCompany.class));
	}

}
