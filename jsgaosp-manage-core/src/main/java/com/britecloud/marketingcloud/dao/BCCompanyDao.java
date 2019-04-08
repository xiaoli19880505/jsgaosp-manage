/** 
 * 项目名称:91营销云
 * 文件名：BCCompanyDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import java.util.List;

import com.britecloud.marketingcloud.model.BcCompany;



public interface BCCompanyDao {
	/**
	 * 列出所有单位
	 * @return
	 */
	public List<BcCompany> listCompany();
}
