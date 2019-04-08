/** 
 * 项目名称:91营销云
 * 文件名：ExceptionUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.britecloud.marketingcloud.core.exception.ForeignKeyConstraintViolationException;
import com.britecloud.marketingcloud.core.exception.FrameworkExceptions;
import com.britecloud.marketingcloud.core.exception.ServiceException;

public class ExceptionUtils {

	public static ServiceException translateException(Throwable t) {
		if (t instanceof ServiceException) {
			return (ServiceException) t;
		}

		if (t instanceof ForeignKeyConstraintViolationException) {
			return new ServiceException(FrameworkExceptions.FOREIGNKEY_CONSTRAINT);
		}

		if (t instanceof DataAccessException) {
			if (t.getCause() != null) {
				if (t.getCause() instanceof SQLException) {
					SQLException sqlEx = (SQLException) t.getCause();
					if (sqlEx.getNextException() != null) {
						return new ServiceException(FrameworkExceptions.DATABASE_ERROR,sqlEx.getNextException());
					}
					return new ServiceException(FrameworkExceptions.DATABASE_ERROR,sqlEx);
				}
				return new ServiceException(FrameworkExceptions.DATABASE_ERROR,t.getCause());
			} else {
				return new ServiceException(FrameworkExceptions.DATABASE_ERROR);
			}
		}

		if (t == null) {
			return new ServiceException(FrameworkExceptions.SYSTEM_ERROR);
		}

		return new ServiceException(FrameworkExceptions.SYSTEM_ERROR,t);
	}

}
