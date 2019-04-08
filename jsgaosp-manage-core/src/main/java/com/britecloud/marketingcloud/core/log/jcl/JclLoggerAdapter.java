/** 
 * 项目名称:91营销云
 * 文件名：JclLoggerAdapter.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.log.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import com.britecloud.marketingcloud.core.log.Level;
import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerAdapter;

public class JclLoggerAdapter implements LoggerAdapter {

	public Logger getLogger(String key) {
		return new JclLogger(LogFactory.getLog(key));
	}

    public Logger getLogger(Class<?> key) {
        return new JclLogger(LogFactory.getLog(key));
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
