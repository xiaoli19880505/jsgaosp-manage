/** 
 * 项目名称:91营销云
 * 文件名：IDUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import org.bson.types.ObjectId;

public class IDUtils {

	private static IdWorker mySQLId = new IdWorker(1);

	public static String getId() {
		try {
			return String.valueOf(mySQLId.nextId());
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String getObjectId(){
		return ObjectId.get().toString();
	}
	
}
