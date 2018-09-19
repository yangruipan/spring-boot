package com.buy.controller;

import com.alibaba.fastjson.JSONObject;
import com.buy.entity.pan.User;
import com.buy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * user Controller
 * @author yrp
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/selectUserAll")
    public String selectUserAll(Model model){
        model.addAttribute("userList",userService.selectAll());
        model.addAttribute("userTestList",userService.selectTestAll());
        return "user";
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUserId")
    public String selectByUserId(){
        User user = userService.selectByPrimaryKey(1);
        return JSONObject.toJSONString(user);
    }
}
