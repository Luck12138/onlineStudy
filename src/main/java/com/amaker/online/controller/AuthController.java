package com.amaker.online.controller;

import com.amaker.online.common.MD5Utils;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.common.web.SessionContext;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

        return "auth/register";
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
        if(SessionContext.isLogin()){
            return "/";
        }
        return "auth/login";
    }



    @RequestMapping("/doLogin")
    public String doLogin(AuthUser user, Integer rememberMe, Model model){

        if(SessionContext.getAuthUser() != null){
            return "user/home";
        };
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),MD5Utils.MD5(user.getPassword()));
        try {
            if(rememberMe != null && rememberMe == 1){
                token.setRememberMe(true);

            }
            currentUser.login(token);
            //shiro：不抛出异常，登陆成功
            return "user/home";
        }catch(AuthenticationException e){ //登录失败
            model.addAttribute("errcode",2);
            return "auth/login";
        }
    }

    @RequestMapping("/ajaxlogin")
    @ResponseBody
    public String ajaxlogin(AuthUser user, Integer rememberMe){
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),MD5Utils.MD5(user.getPassword()));
        try {
            if(rememberMe != null && rememberMe == 1){
                token.setRememberMe(true);
            }
            currentUser.login(token);//shiro：不抛出异常，登陆成功
            return JsonView.getJSONString(0);
        }catch(AuthenticationException e){ //登录失败
            return JsonView.getJSONString(1, "用户名或密码不正确");
        }
    }

}
