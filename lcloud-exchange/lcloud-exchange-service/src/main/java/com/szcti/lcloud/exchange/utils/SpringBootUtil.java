package com.szcti.lcloud.exchange.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SpringBootUtil implements ApplicationContextAware {
	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Environment getEnvironment() {
		return context.getEnvironment();
	}
}