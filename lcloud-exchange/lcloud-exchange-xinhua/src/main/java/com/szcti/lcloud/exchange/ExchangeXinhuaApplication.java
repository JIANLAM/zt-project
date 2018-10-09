package com.szcti.lcloud.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeXinhuaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeXinhuaApplication.class, args);
	}
}
