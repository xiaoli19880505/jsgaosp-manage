/** 
 * 项目名称:91营销云
 * 文件名：ForeignKeyConstraintViolationException.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.exception;

import org.springframework.dao.DataAccessException;

public class ForeignKeyConstraintViolationException extends DataAccessException {

	public ForeignKeyConstraintViolationException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public ForeignKeyConstraintViolationException(String msg) {
		super(msg);
	}

}
