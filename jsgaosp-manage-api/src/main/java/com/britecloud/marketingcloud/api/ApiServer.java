package com.britecloud.marketingcloud.api;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.britecloud.marketingcloud.api.utils.RawEventConsumer;
import com.britecloud.marketingcloud.api.utils.RestSignatureFilter;
import com.britecloud.marketingcloud.core.ContextHolder;
import com.google.common.base.Predicate;

import kafka.server.KafkaConfig;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.britecloud")
@EnableSwagger2
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, VelocityAutoConfiguration.class, KafkaConfig.class})
public class ApiServer {
	@Bean
	public FilterRegistrationBean SignatureFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(restSignatureFilter());
		registration.addUrlPatterns("/api/*");
		registration.setName("restSignatureFilter");
		return registration;
	}

	@Bean
	public FilterRegistrationBean encodingFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		registration.setFilter(filter);
		registration.addUrlPatterns("/*");
		registration.setName("encodingFilter");
		return registration;
	}

	@Bean(name = "restSignatureFilter")
	public Filter restSignatureFilter() {
		return new RestSignatureFilter();
	}

//	@Bean
//	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//		return new RedisCacheManager(redisTemplate);
//	}
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
//		StringRedisTemplate template = new StringRedisTemplate(connectionFactory);
//		template.expire("dataSourceDef", 10, TimeUnit.MINUTES);
//		template.expire("apiKey", 10, TimeUnit.MINUTES);
//		return template;
//	}

	@Bean
	public ContextHolder getContextHolder() {
		return ContextHolder.getInstance();
	}

	@Bean
	public Docket marketingCloudApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(marketingCloudPath()).build();
	}

	@Bean(initMethod = "run")
	@ConditionalOnBean(name = "kafkaConsumer")
	public RawEventConsumer getRawEventConsumer() {
		RawEventConsumer consumer = new RawEventConsumer();
		return consumer;
	}

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(ApiServer.class);
		app.setWebEnvironment(true);
		StandardEnvironment env = new StandardEnvironment();
		env.setActiveProfiles("api");
		app.setEnvironment(env);
		ApplicationContext ctx = app.run(args);
		ContextHolder.getInstance().setApplicationContext(ctx);

	}

	private Predicate<String> marketingCloudPath() {
		return or("/api/events.*", "/api/users.*", "/api/email.*", "/api/sms.*");
	}

	private Predicate<String> or(final String regex, final String regex2, final String regex3, final String regex4) {
		// TODO Auto-generated method stub
		return new Predicate<String>() {

			@Override
			public boolean apply(String input) {

				return input.matches(regex) || input.matches(regex2) || input.matches(regex3) || input.matches(regex4);
			}

		};
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Harbortek BI cloud API").description("Harbortek BI Cloud Api Rest接口说明文档").build();
	}

}
