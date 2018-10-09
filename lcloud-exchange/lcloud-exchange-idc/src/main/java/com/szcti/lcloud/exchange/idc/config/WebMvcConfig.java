package com.szcti.lcloud.exchange.idc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.szcti.lcloud.exchange.idc.security.AuthenticationInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
    public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // RequestInterceptor()为自己定义的拦截器
        registry.addInterceptor(authenticationInterceptor());
    }
}
