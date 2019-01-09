package com.amaker.online.controller;

import com.amaker.online.common.MD5Utils;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.dao.AuthUserDao;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 15:09
 * @Author: GHH
 * @Description:登录&&注册
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private AuthUserService authUserService;

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST,RequestMethod.GET})
    public String register(){

        return "register";
    }

    /**
     * 实现注册功能
     * @return
     */
    @RequestMapping(value = "/doRegister",method = {RequestMethod.POST})
    @ResponseBody
    public String doRegister(AuthUser authUser){

        AuthUser user = authUserService.selectUserByUsername(authUser.getUsername());
        if(user!=null){
            return JsonView.getJSONString(1);
        }else {
            authUser.setPassword(MD5Utils.MD5(authUser.getPassword()));
            authUserService.addUser(authUser);
            return JsonView.getJSONString(0);
        }

    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }



}
