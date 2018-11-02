package com.buy.service.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName Task
 * @Author yrp
 * @Date 2018/11/2 14:14
 */
@Component
public class Task {

    @Scheduled(fixedRate = 5000)
    public void text1(){
        System.out.println("*********************************测试定时器1*********************************");
    }

    @Scheduled(fixedRate = 5000)
    public void text2(){
        System.out.println("*********************************测试定时器2*********************************");
    }

}
