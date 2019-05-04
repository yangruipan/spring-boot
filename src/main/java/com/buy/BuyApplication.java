package com.buy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableAsync(proxyTargetClass = true)
@EnableScheduling //spring boot自带定时器
@SpringBootApplication
@EnableTransactionManagement //事务控制
@EnableCaching //缓存
public class BuyApplication {

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
