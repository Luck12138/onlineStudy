package com.amaker.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 15:09
 * @Author: GHH
 * @Description:登录&&注册
 */
@Controller
public class AuthController {

    @RequestMapping(value = "/auth/register",method = {RequestMethod.POST,RequestMethod.GET})
    public String register(){

        return "register";
    }
}
