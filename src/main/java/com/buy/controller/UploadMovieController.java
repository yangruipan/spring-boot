package com.buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/uploadMovie")
public class UploadMovieController {


    @ResponseBody
    @RequestMapping(value = "/uploadMovie")
    public String uploadMovie(){
        return "uploadMovie";
    }
}
