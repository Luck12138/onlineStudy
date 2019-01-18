package com.amaker.online.controller;

import com.amaker.online.common.MD5Utils;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.UserCollections;
import com.amaker.online.model.UserCourseSection;
import com.amaker.online.model.UserCourseSectionDto;
import com.amaker.online.service.AuthUserService;
import com.amaker.online.service.UserCollectionsService;
import com.amaker.online.service.UserCourseSectionService;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 13:32
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    public static Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserCourseSectionService userCourseSectionService;

    @Autowired
    private UserCollectionsService userCollectionsService;

    @Autowired
    private AuthUserService authUserService;


    @RequestMapping("/home")
    public String home(){
        return "user/home";
    }

    @RequestMapping("/course")
    public String course(Model model, TailPage<UserCourseSectionDto> page){
        model.addAttribute("curNav","course");
        UserCourseSection userCourseSection=new UserCourseSection();
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        userCourseSection.setUserId(user.getId());
        TailPage<UserCourseSectionDto> userCourseSections = userCourseSectionService.selectUserSectionPage(userCourseSection, page);
        model.addAttribute("page",userCourseSections);
        return "user/course";
    }
    @RequestMapping("/collect")
    public String collect(Model model, TailPage<UserCollections> page){
        model.addAttribute("curNav","collect");
        UserCollections userCollections=new UserCollections();
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        userCollections.setUserId(user.getId());
        TailPage<UserCollections> collectionsTailPage = userCollectionsService.selectUserCollectPage(userCollections, page);
        model.addAttribute("page",collectionsTailPage);
        return "user/collect";
    }

    @RequestMapping("/info")
    public String info(Model model){
        model.addAttribute("curNav","info");
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        AuthUser authUser = authUserService.selectUserByUsername(user.getUsername());
        if(authUser!=null&&StringUtils.isNotEmpty(authUser.getHeader())){
            authUser.setHeader(QiniuStorage.getUrl(authUser.getHeader()));
        }
        model.addAttribute("authUser",authUser);
        return "/user/info";
    }



    @RequestMapping("/saveInfo")
    @ResponseBody
    public String saveInfo(AuthUser authUser,
                           @RequestParam("pictureImg")MultipartFile pictureImg) throws IOException {
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        authUser.setId(user.getId());
        if(pictureImg!=null&&pictureImg.getBytes().length>0){
            String key = QiniuStorage.uploadImage(pictureImg.getBytes());
            authUser.setHeader(key);
        }
        authUserService.updateUser(authUser);
        return JsonView.getJSONString(0);
    }


    @RequestMapping("/passwd")
    public String passwd(Model model){
        model.addAttribute("curNav","passwd");
        return "user/passwd";
    }

    @RequestMapping("/savePasswd")
    @ResponseBody
    public String savePasswd(@RequestParam("oldPassword") String oldPassword,
                             @RequestParam("password") String password,
                             @RequestParam("rePassword") String rePassword){
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        AuthUser currentUser= authUserService.selectUserByUsername(user.getUsername());
        if(null == currentUser){
            return JsonView.getJSONString(1,"用户不存在！");
        }
        oldPassword = MD5Utils.MD5(oldPassword);
        if(!oldPassword.equals(currentUser.getPassword())){
            return JsonView.getJSONString(1,"旧密码不正确！");
        }
        if(StringUtils.isEmpty(password.trim())){
            return JsonView.getJSONString(1,"新密码不能为空！");
        }
        if(!password.trim().equals(rePassword.trim())){
            return JsonView.getJSONString(1,"新密码与重复密码不一致！");
        }
        currentUser.setPassword(MD5Utils.MD5(password));
        authUserService.updateUser(currentUser);
        logger.info(currentUser.getPassword());
        return JsonView.getJSONString(0);
    }
}
