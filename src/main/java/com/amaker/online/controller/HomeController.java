package com.amaker.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 13:43
 * @Author: GHH
 * @Description:
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/",method = {RequestMethod.GET})
    public String home(){

        return "index";
    }



}
