/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   StartWebApplication.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.britecloud.marketingcloud.core.ContextHolder;


@SpringBootApplication
@ComponentScan(basePackages = "com.britecloud")
@Configuration
public class ConsoleServer extends WebMvcConfigurerAdapter {

	@Bean
	public HttpPutFormContentFilter loadHttpPutFormContentFilter() {
		return new HttpPutFormContentFilter();
	}

	public static void main(String[] args) throws Exception {  

		
		SpringApplication app = new SpringApplication(ConsoleServer.class);
		StandardEnvironment env = new StandardEnvironment();
		env.setActiveProfiles("web");
		app.setEnvironment(env);
		ApplicationContext ctx = app.run(args);
		ContextHolder.getInstance().setApplicationContext(ctx);

	
	}
}