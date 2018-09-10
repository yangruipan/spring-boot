package com.buy.controller;

import com.alibaba.fastjson.JSONObject;
import com.buy.entity.User;
import com.buy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ResponseBody
    @RequestMapping(value = "/selectUserAll")
    public String selectUserAll(){
        return JSONObject.toJSONString(userService.selectAll());
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUserId")
    public String selectByUserId(){
        User user = userService.selectByPrimaryKey(1);
        return JSONObject.toJSONString(user);
    }
}
