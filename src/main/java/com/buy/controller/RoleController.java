package com.buy.controller;

import com.alibaba.fastjson.JSONObject;
import com.buy.entity.Role;
import com.buy.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @ResponseBody
    @RequestMapping(value = "/selectRoleAll")
    public String selectRoleAll(){
        return JSONObject.toJSONString(roleService.selectAll());
    }
}
