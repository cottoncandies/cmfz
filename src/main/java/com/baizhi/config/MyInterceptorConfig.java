package com.baizhi.config;
import com.baizhi.interceptors.MyInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class MyInterceptorConfig extends WebMvcConfigurerAdapter {
	@Resource
	private MyInterceptors myInterceptors;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptors)
				.addPathPatterns("/**")
				.excludePathPatterns("/admin/login","/validate/code");
	}
}
