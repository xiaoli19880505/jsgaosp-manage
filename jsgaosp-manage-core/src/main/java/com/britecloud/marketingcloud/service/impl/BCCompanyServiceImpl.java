/** 
 * 项目名称:91营销云
 * 文件名：BCCompanyServiceImpl.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BCCompanyDao;
import com.britecloud.marketingcloud.model.BcCompany;
import com.britecloud.marketingcloud.service.BCCompanyService;



@Service
public class BCCompanyServiceImpl implements BCCompanyService{

	@Autowired
	private BCCompanyDao bcCompanyDao;
	
	@Override
	public List<BcCompany> listCompany() {
		return bcCompanyDao.listCompany();
	}
	
}
