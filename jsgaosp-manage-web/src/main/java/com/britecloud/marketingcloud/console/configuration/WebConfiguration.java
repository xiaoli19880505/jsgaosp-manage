package com.britecloud.marketingcloud.console.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.britecloud.marketingcloud.core.SecurityInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public SecurityInterceptor securityInterceptor() {
		SecurityInterceptor interceptor = new SecurityInterceptor();
		interceptor.setUnfilteredURIs("(/.*/login$)|(/global/.*)|(/auth/.*)|(/.*\\.html$)|(/.*\\.jar$)|(/.*\\.jnlp$)|(/.*\\.png$)|(/.*\\.jpg$)|(/.*\\.gif$)|(/.*\\.ico$)|(/.*\\.css$)|(/.*\\.swf$)|(/.*\\.js$)|(/tools/.*)|(/.*\\.woff$)|(/.*\\.eot$)|(/.*\\.map$)|(/.*\\.ck$)");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor());
	}

}
