package com.szcti.lcloud.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.szcti.lcloud.exchange.utils.SpringUtil;

@SpringBootApplication
@Import(SpringUtil.class)
@EnableScheduling
public class ExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }
}
