package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: onlinestudy
 * @Date: 2019/1/19 0019 11:28
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {


    @Autowired
    private AuthUserService authUserService;

    /**
     * 用户管理
     * @param user
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/userPageList")
    public String userInfoList(AuthUser user, TailPage<AuthUser> page, Model model){
        model.addAttribute("curNav","userPageList");

        if(!StringUtils.isNotEmpty(user.getUsername())){
            user.setUsername(null);
        }
        if(Integer.valueOf(-1).equals(user.getStatus())){
            user.setStatus(null);
        }
        TailPage<AuthUser> queryPage = authUserService.queryPage(user, page);
        model.addAttribute("page",queryPage);
        model.addAttribute("user",user);
        return "admin/userPageList";
    }


    @RequestMapping("/userGetById")
    @ResponseBody
    public String userGetById(int id){
        AuthUser authUser = authUserService.selectUserById(id);
        return JsonView.getJSONString(authUser);
    }

    @RequestMapping("/userUpdate")
    @ResponseBody
    public String userUpdate(AuthUser authUser){
        authUser.setUsername(null);
        authUser.setRealname(null);
        authUserService.updateUser(authUser);
        return JsonView.getJSONString(0);
    }
}
