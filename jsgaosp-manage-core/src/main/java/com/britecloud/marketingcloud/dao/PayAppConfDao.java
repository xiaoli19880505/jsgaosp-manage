/** 
 * 项目名称:91营销云
 * 文件名：SysUserJdbcDao.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.model.PayAppConf;

import java.util.Map;

public interface PayAppConfDao {

	 void createPayAppConf(PayAppConf payAppConf);

	 void updatePayAppConf(PayAppConf payAppConf);

	 void deletePayAppConf(String id);

	 PayAppConf getPayAppConf(String id);

	 Map listPayConf(Map params);
}
