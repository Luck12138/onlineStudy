package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.CourseComment;
import com.amaker.online.service.CourseCommentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: onlinestudy
 * @Date: 2019/1/21 0021 21:12
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    private CourseCommentService commentService;

    @RequestMapping("/courseCommentList")
    public String commentList(CourseComment courseComment, TailPage<CourseComment> page,Model model){
        model.addAttribute("curNav","course");
        TailPage<CourseComment> courseComments = commentService.selectAllComment(courseComment, page);
        for(CourseComment item : courseComments.getItems()){
            if(StringUtils.isNotEmpty(item.getHeader())){
                item.setHeader(QiniuStorage.getUrl(item.getHeader()));
            }
        }
        model.addAttribute("courseComment",courseComment);
        model.addAttribute("page",courseComments);
        return "admin/readComment";
    }

    @RequestMapping("/courseCommentDelete")
    @ResponseBody
    public String deleteComment(CourseComment courseComment){
        commentService.deleteComment(courseComment);
        return JsonView.getJSONString(0);
    }
}
