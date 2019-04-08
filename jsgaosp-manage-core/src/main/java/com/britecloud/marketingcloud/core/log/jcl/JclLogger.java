/** 
 * 项目名称:91营销云
 * 文件名：JclLogger.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.log.jcl;

import java.io.Serializable;

import org.apache.commons.logging.Log;

import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.support.AbstractLogger;

/**
 * 适配CommonsLogging，依赖于commons-logging.jar
 */
public class JclLogger extends AbstractLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final Log logger;

	public JclLogger(Log logger) {
		this.logger = logger;
	}

    public void trace(String msg) {
        logger.trace(msg);
    }

    public void trace(Throwable e) {
        logger.trace(e);
    }

    public void trace(String msg, Throwable e) {
        logger.trace(msg, e);
    }

	public void debug(String msg) {
		logger.debug(msg);
	}

    public void debug(Throwable e) {
        logger.debug(e);
    }

	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	public void info(String msg) {
		logger.info(msg);
	}

    public void info(Throwable e) {
        logger.info(e);
    }

	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

    public void warn(Throwable e) {
        logger.warn(e);
    }

	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	public void error(String msg) {
		logger.error(msg);
	}

    public void error(Throwable e) {
        logger.error(e);
    }

	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

}
