package com.amaker.online.controller;

import com.alibaba.fastjson.JSONObject;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.common.web.SessionContext;
import com.amaker.online.model.*;
import com.amaker.online.service.CourseSectionService;
import com.amaker.online.service.CourseService;
import com.amaker.online.service.UserCourseSectionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 21:23
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private UserCourseSectionService userCourseSectionService;

    @Autowired
    private CourseSectionService sectionService;

    @Autowired
    private CourseService courseService;



    @RequestMapping("/getCurLeanInfo")
    @ResponseBody
    public String  getInfo(){
        JsonView jsonView=new JsonView();
        if(SessionContext.isLogin()){
            UserCourseSection userCourseSection=new UserCourseSection();
            AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
            userCourseSection.setUserId(user.getId());
            UserCourseSection userCourseSectionServiceSectionById = userCourseSectionService.getSectionById(userCourseSection);
            if(userCourseSectionServiceSectionById!=null){
                JSONObject jsonObject=new JSONObject();
                CourseSection courseSection = sectionService.selectSectionById(userCourseSectionServiceSectionById.getSectionId());
                jsonObject.put("curCourseSection",courseSection);
                Course course = courseService.getById(userCourseSectionServiceSectionById.getCourseId());
                jsonObject.put("curCourse",course);
                jsonView.setData(jsonObject);
            }

        }
        return jsonView.toString();
    }


    @RequestMapping("/video/{sectionId}")
    public String video(@PathVariable("sectionId") Long sectionId, Model model){
        if(sectionId==null){
            return "error/404";
        }
        CourseSection courseSection = sectionService.selectSectionById(sectionId);
        if(courseSection==null){
            return "error/404";
        }
        List<CourseSectionVO> courseSectionVOS = sectionService.selectSectionList(courseSection.getCourseId());
        model.addAttribute("courseSection",courseSection);
        model.addAttribute("chaptSections",courseSectionVOS);

        UserCourseSection userCourseSection=new UserCourseSection();
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        userCourseSection.setUserId(user.getId());
        userCourseSection.setCourseId(courseSection.getCourseId());
        userCourseSection.setSectionId(sectionId);
        UserCourseSection selectLastSection = userCourseSectionService.selectLastSection(userCourseSection);
        if(selectLastSection==null){
            userCourseSection.setCreateTime(new Date());
            userCourseSection.setCreateUser(user.getUsername());
            userCourseSection.setUpdateTime(new Date());
            userCourseSection.setUpdateUser(user.getUsername());
            userCourseSectionService.addUserSection(userCourseSection);
        }else {
            userCourseSection.setUpdateTime(new Date());
            userCourseSectionService.update(userCourseSection);
        }
        return "video";
    }
}
