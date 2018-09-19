package com.buy.controller;

import com.buy.config.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * test Controller
 * @author yrp
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/test")
    public void test(){
        try {
            logger.info("日志开始###############################################################");
            int num = 1/0;
            System.out.println(num);
        }catch (Exception e){
            logger.info("日志结束###############################################################");
            logger.error("系统运行异常：" + e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/testRedis")
    public String testRedis(){
        try{
            logger.info("日志开始###################################################################");
            redisService.addStr("name","张三");
            redisService.addStr("age","25");
            logger.info("日志结束####################################################################");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("日志异常####################################################################");
            return e.getMessage();
        }
        return "测试redis";
    }

    @ResponseBody
    @RequestMapping(value = "/getRedis")
    public String getRedis(){
        String name = null;
        String age = null;
        try{
            logger.info("日志开始###################################################################");
            name = String.valueOf(redisService.get("name"));
            age = String.valueOf(redisService.get("age"));
        }catch (Exception e){
            e.printStackTrace();
            logger.info("日志异常####################################################################");
            logger.info(e.getMessage());
        }
        return "name:"+name+";age:"+age;
    }
}
