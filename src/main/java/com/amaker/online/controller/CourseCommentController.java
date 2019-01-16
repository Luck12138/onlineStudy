package com.amaker.online.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.model.CourseComment;
import com.amaker.online.service.CourseCommentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
