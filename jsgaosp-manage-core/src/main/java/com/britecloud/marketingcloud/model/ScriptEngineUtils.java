/** 
 * 项目名称:91营销云
 * 文件名：ScriptEngineUtils.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.model;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;




import com.britecloud.marketingcloud.core.log.Logger;
import com.britecloud.marketingcloud.core.log.LoggerFactory;

public class ScriptEngineUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ScriptEngineUtils.class);
	
	private static ScriptEngineManager manager = new ScriptEngineManager();

	/***
	 * 
	 * @script 函数内容
	 * @param funcName
	 *            执行的JS函数
	 * @param obj
	 *            参数对象
	 * @return
	 */
	public static Object invokeFunc(String script, String funcName, Object obj) {
		ScriptEngine engine = manager.getEngineByName("javascript");
		Object result = null;
		try {
			// 塞入函数内容
			engine.eval(script);
			logger.info(script);
			Invocable inv = (Invocable) engine;
			result = inv.invokeFunction(funcName, obj);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
}
