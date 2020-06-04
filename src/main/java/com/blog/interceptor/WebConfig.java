package com.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	/**
	 * 配置拦截路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin","/admin/login");
	}

}
