/** 
 * 项目名称:91营销云
 * 文件名：SqlUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ClassUtils;

import com.britecloud.marketingcloud.core.dao.jdbc.sql.dynamic.DynamicSql;
import com.britecloud.marketingcloud.core.dao.jdbc.sql.stat.StaticSql;
import com.britecloud.marketingcloud.core.exception.FrameworkExceptions;
import com.britecloud.marketingcloud.core.exception.ServiceException;
import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerFactory;

public class SqlUtils {
	private static Logger logger = LoggerFactory.getLogger(SqlUtils.class);
	
	private static final String COMMENT_SL_START = "//"; //$NON-NLS-1$

	public static String loadSQL(String fileName, String sqlId) {
		String sql = loadSQL(fileName, sqlId, null);
		return sql;
	}

	public static String loadSQL(String fileName, String sqlId, Map parameters) {
		if (logger.isDebugEnabled()){
			logger.debug("load SQLFile:"+fileName+" SqlId:"+sqlId);
		}

		
		String resourceLocation = fileName + (fileName.endsWith(".sql") ? "" : ".sql");
		BufferedReader in = null;
		try {
			URL url = ClassUtils.getDefaultClassLoader().getResource(resourceLocation);
			if (url == null)
				return null;
			//File file = ResourceUtils.getFile(url);
			Map map = new HashMap();
			in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			String sqlName = null;
			StringBuffer sb = new StringBuffer();
			while (true) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				if (StringUtils.isEmpty(line)) {
					continue;
				}
				int statementStart = StringUtils.trim(line).indexOf("--------------------");
				if (statementStart == 0) {
					line = StringUtils.trim(in.readLine());
					if (line.startsWith("--")) {
						if (StringUtils.isNotEmpty(sqlName)) {
							String sqlText = sb.toString();
							if (sqlText.indexOf("--<") > 0) {
								map.put(sqlName, new DynamicSql(sqlText).getSql(parameters));
							} else {
								map.put(sqlName, new StaticSql(sqlText).getSql(parameters));
							}
						}
						sqlName = line.substring(2);
						sb.delete(0, sb.length());
						continue;
					}
				}

				int cstart = line.indexOf(COMMENT_SL_START);
				if (cstart >= 0) {
					line = line.substring(0, cstart);
				}
				if (!StringUtils.isEmpty(line)) {
					sb.append(line);
					sb.append('\n');
				}
			}
			if (StringUtils.isNotEmpty(sqlName)) {
				String sqlText = sb.toString();
				map.put(sqlName, sqlText);
				if (sqlText.indexOf("--<") > 0) {
					map.put(sqlName, new DynamicSql(sqlText).getSql(parameters));
				}else{
					map.put(sqlName, new StaticSql(sqlText).getSql(parameters));
				}
			}
			String sql = (String) map.get(sqlId);
			if (sql==null){
				return null;
			}
			return sql;
		} catch (Exception e) {
			throw new ServiceException(FrameworkExceptions.DATABASE_ERROR, e);
		} finally{
			if (in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static  void  main(String[] args){
		String resourceLocation = "com/britecloud/marketingcloud/dao/impl/sql/SysUserJdbcDaoImpl.sql";
		URL url = ClassUtils.getDefaultClassLoader().getResource(resourceLocation);
	}

}
