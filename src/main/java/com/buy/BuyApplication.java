package com.buy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.buy")
@EnableCaching
public class BuyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BuyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BuyApplication.class, args);
    }

    /**
     * 通用的rest接口请求模板类
     * @Date 2018/8/27
     *
     */
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
