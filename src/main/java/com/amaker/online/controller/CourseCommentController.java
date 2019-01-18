package com.amaker.online.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.CourseComment;
import com.amaker.online.model.CourseSection;
import com.amaker.online.service.CourseCommentService;
import com.amaker.online.service.CourseSectionService;
import com.qiniu.util.Json;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 15:24
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {

    @Autowired
    private CourseCommentService commentService;

    @Autowired
    private CourseSectionService courseSectionService;

    @RequestMapping("/segment")
    public String comment(Model model,CourseComment courseComment, TailPage<CourseComment> page){

        if(null == courseComment.getCourseId() || courseComment.getType() == null){
            return "error/404";
        }

        TailPage<CourseComment> commentPage = commentService.selectAllComment(courseComment, page);
        //处理用户头像
        for(CourseComment item : commentPage.getItems()){
            if(StringUtils.isNotEmpty(item.getHeader())){
                item.setHeader(QiniuStorage.getUrl(item.getHeader()));
            }
        }
        model.addAttribute("page",commentPage);
        return "comment";
    }

    @RequestMapping("/doComment")
    @ResponseBody
    public String addComment(CourseComment courseComment){
        if(courseComment.getContent().trim().length()>200||courseComment.getContent().trim().length()==0){
            return JsonView.getJSONString(3);
        }

        CourseSection courseSection = courseSectionService.selectSectionById(courseComment.getSectionId());
        if(courseSection!=null){
            AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
            courseComment.setSectionTitle(courseSection.getName());
            courseComment.setToUsername(courseComment.getCreateUser());
            courseComment.setUsername(user.getUsername());
            courseComment.setCreateTime(new Date());
            courseComment.setCreateUser(user.getUsername());
            courseComment.setUpdateTime(new Date());
            courseComment.setUpdateUser(user.getUsername());
            commentService.addComment(courseComment);
            return JsonView.getJSONString(0);
        }

        return JsonView.getJSONString(1);
    }

}
