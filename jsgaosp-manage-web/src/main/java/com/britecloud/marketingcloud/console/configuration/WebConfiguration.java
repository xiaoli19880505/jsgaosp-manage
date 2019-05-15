package com.britecloud.marketingcloud.console.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.britecloud.marketingcloud.core.SecurityInterceptor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
/*@EnableSwagger2
@EnableWebMvc*/
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public SecurityInterceptor securityInterceptor() {
	    SecurityInterceptor interceptor = new SecurityInterceptor();
		interceptor.setUnfilteredURIs("(/.*/login$)|(/global/.*)|(/auth/.*)|(/.*\\.html$)|(/.*\\.jar$)|(/.*\\.jnlp$)|(/.*\\.png$)|(/.*\\.jpg$)|(/.*\\.gif$)|(/.*\\.ico$)|(/.*\\.css$)|(/.*\\.swf$)|(/.*\\.js$)|(/tools/.*)|(/.*\\.woff$)|(/.*\\.eot$)|(/.*\\.map$)|(/.*\\.ck$)");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor())
				.excludePathPatterns("/api/**");
				//.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry .addResourceHandler("/upload/imgs/**")
                .addResourceLocations("file:/home/file");
		/*registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");*/

	}


}
