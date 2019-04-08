/** 
 * 项目名称:91营销云
 * 文件名：SysCompanyJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.BcCompany;

public interface SysCompanyJdbcDao {

	Map loadCompany(Map params);
	
	List<BcCompany> loadCompany();

	BcCompany getCompanyInfo(String companyId);

	void addCompany(BcCompany company);

	void deleteCompany(String companyId);

	void saveCompany(BcCompany company);

	void initPara(String companyId);

}
