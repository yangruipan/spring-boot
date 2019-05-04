package com.buy.demo;

import com.buy.entity.pan.User;
import com.buy.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 测试事务控制
     */
    @Test
    public void contextLoads() {
        try {
            userService.insert(new User());
            System.out.println(true);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(false);
        }

    }

}
