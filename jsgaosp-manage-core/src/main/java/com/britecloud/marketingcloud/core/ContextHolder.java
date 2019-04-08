/** 
 * 项目名称:91营销云
 * 文件名：ContextHolder.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextHolder implements ApplicationContextAware{
	private  ApplicationContext context;

	private static ContextHolder instance;
	
	public static ContextHolder getInstance(){
		if (instance==null){
			instance = new ContextHolder();
		}
		return instance;
	}
	
	public static ApplicationContext getContext() {
		return getInstance().getApplicationContext();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
	public ApplicationContext getApplicationContext(){
		return context;
	}
	
	
}
