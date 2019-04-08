/** 
 * 项目名称:91营销云
 * 文件名：Slf4jLoggerAdapter.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.log.slf4j;

import java.io.File;

import com.britecloud.marketingcloud.core.log.Level;
import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerAdapter;

public class Slf4jLoggerAdapter implements LoggerAdapter {
    
	public Logger getLogger(String key) {
		return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
	}

    public Logger getLogger(Class<?> key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    private Level level;
    
    private File file;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
