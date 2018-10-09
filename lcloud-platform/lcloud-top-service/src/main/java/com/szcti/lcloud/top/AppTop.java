package com.szcti.lcloud.top;

import com.szcti.lcloud.common.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@EnableScheduling
public class AppTop extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AppTop.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppTop.class);
	}
}
