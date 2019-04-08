/** 
 * 项目名称:91营销云
 * 文件名：Executor.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

import com.britecloud.marketingcloud.core.ContextHolder;
import com.britecloud.marketingcloud.executor.zmq.MonitorReceiveHandler;

@SpringBootApplication
@ComponentScan(basePackages = "com.britecloud")
@Configuration
public class AlarmStart {
	private static MonitorReceiveHandler handler;
	
	@Bean
	public ContextHolder getContextHolder() {
		return ContextHolder.getInstance();
	}
	
	@Bean
	public MonitorReceiveHandler ｍonitorReceiveHandler() {
		return new MonitorReceiveHandler();
	}
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AlarmStart.class);
		app.setWebEnvironment(false);
		StandardEnvironment env = new StandardEnvironment();
		env.setActiveProfiles("alarm");
		app.setEnvironment(env);
		ApplicationContext ctx = app.run(args);
		ContextHolder.getInstance().setApplicationContext(ctx);
		
		handler = (MonitorReceiveHandler)ctx.getBean("ｍonitorReceiveHandler");
		handler.receive();
		handler.destory();
	}
	
	
}
