/** 
 * 项目名称:91营销云
 * 文件名：SQLServerUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.dao.jdbc;


/**
 * 创建日期:2011-9-21
 * 类说明:适用于SQLServer的DAO基类
 */
public class SQLServerUtils {

	public static String getPaginationString(String sql,Integer start,Integer limit) {
		while(sql.substring(0, 1).equals(" ")){
			sql = sql.substring(1);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (select row_number() over(order by _tc_) _rn_, * from (");
		sb.append(sql.substring(0,6).toUpperCase().replaceFirst("SELECT", "SELECT top " + (start + limit) + " 0 _tc_,"));
		sb.append(sql.substring(7));
		sb.append(") t) tt where _rn_ >");
		sb.append(start);
		
		return sb.toString();			
	}

}
