/** 
 * 项目名称:91营销云
 * 文件名：SysCompanyMgmtService.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.BcCompany;

public interface SysCompanyMgmtService {

	Map loadCompany(Map params);
	
	List<BcCompany> loadCompany();

	BcCompany getCompanyInfo(String orgId);

	void addCompany(BcCompany company);

	void deleteCompany(String companyId);
	/**
	 * 修改组织信息
	 * @param org
	 */
	void saveCompany(BcCompany company);

	void initPara(String companyId);
	
}
