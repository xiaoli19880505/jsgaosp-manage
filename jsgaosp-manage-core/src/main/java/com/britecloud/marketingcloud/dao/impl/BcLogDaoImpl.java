/**
 * 项目名称:省公安
 * 文件名：BcSysArgsDao.java
 * author:hudasen
 * 版本信息：
 * 日期：2019-4-16
 * Copyright 紫金数云 2019 版权所有
 */

package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcLogDao;
import com.britecloud.marketingcloud.model.BcLoginLog;
import com.britecloud.marketingcloud.model.OperationLog;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 日志管理
 */
@Repository
public class BcLogDaoImpl extends BaseJdbcDao implements BcLogDao {


	/**
	 * 保存登录日志
	 * @param loginLog
	 */
	@Override
	public void saveLoginLog(BcLoginLog loginLog) {
		loginLog.setLoginId(UUIDUtils.generateUUID());
        String sql = loadSQL("saveLoginLog");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(loginLog);
        getNamedParameterJdbcTemplate().update(sql, parameters);
	}

	/**
	 * 保存操作日志
	 * @param operationLog
	 */
	@Override
	public void saveOperationLog(OperationLog operationLog) {
		operationLog.setId(UUIDUtils.generateUUID());
		String sql = loadSQL("saveOperationLog");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(operationLog);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}
}
