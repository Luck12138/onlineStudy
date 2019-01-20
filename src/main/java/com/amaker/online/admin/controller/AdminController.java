package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: onlinestudy
 * @Date: 2019/1/19 0019 9:45
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {



    @RequestMapping("/index")
    public String index(){
        AuthUser user =(AuthUser) SecurityUtils.getSubject().getPrincipal();
        if(!user.getUsername().equals("admin")){
            return "error/404";
        }
        return "admin/index";
    }

}
