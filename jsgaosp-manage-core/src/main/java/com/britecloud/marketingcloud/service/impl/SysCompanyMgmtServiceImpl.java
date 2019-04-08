/** 
 * 项目名称:91营销云
 * 文件名：SysCompanyMgmtServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.SysCompanyJdbcDao;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.service.SysCompanyMgmtService;
@Service
public class SysCompanyMgmtServiceImpl implements SysCompanyMgmtService {
		@Autowired
		private SysCompanyJdbcDao sysComapanyJdbcDao;

		@Override
		public Map loadCompany(Map params) {
			return sysComapanyJdbcDao.loadCompany(params);
		}
		
		@Override
		public List<BcCompany> loadCompany() {
			return sysComapanyJdbcDao.loadCompany();
		}

		@Override
		public BcCompany getCompanyInfo(String companyId) {
			return sysComapanyJdbcDao.getCompanyInfo(companyId);
		}

		@Override
		public void addCompany(BcCompany company) {
			sysComapanyJdbcDao.addCompany(company);
		}

		@Override
		public void deleteCompany(String companyId) {
			sysComapanyJdbcDao.deleteCompany(companyId);
		}

		@Override
		public void saveCompany(BcCompany company) {
			sysComapanyJdbcDao.saveCompany(company);
		}

		@Override
		public void initPara(String companyId) {
			sysComapanyJdbcDao.initPara(companyId);
		}
}
