package com.amaker.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 13:32
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/home")
    public String home(){
        return "user/home";
    }
}
