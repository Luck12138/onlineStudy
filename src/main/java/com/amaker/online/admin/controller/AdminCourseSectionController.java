package com.amaker.online.admin.controller;

import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.CourseSection;
import com.amaker.online.service.CourseSectionService;
import com.qiniu.util.Auth;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @program: onlinestudy
 * @Date: 2019/1/21 0021 9:47
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseSectionController {


    @Autowired
    private CourseSectionService courseSectionService;

    @RequestMapping("/courseSectionGetById")
    @ResponseBody
    public String selectSectionById(long id){
        CourseSection courseSection = courseSectionService.selectSectionById(id);
        return JsonView.getJSONString(courseSection);
    }

    @RequestMapping("/courseSectionDoMerge")
    @ResponseBody
    public String updateSection(CourseSection courseSection){

        courseSectionService.updateCourseSection(courseSection);
        return JsonView.getJSONString(0);
    }

    @RequestMapping("/courseSectionDeleteLogic")
    @ResponseBody
    public String deleteSection(Long id){
        CourseSection section=new CourseSection();
        section.setId(id);
        courseSectionService.deleteCourseSection(section);
        return JsonView.getJSONString(0);
    }


    @RequestMapping("/addCourseSection")
    @ResponseBody
    public String addCourseSection(CourseSection courseSection){
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        courseSection.setOnsale(1);
        courseSection.setParentId(0l);
        courseSection.setSort(1);
        courseSection.setUpdateTime(new Date());
        courseSection.setCreateUser(user.getUsername());
        courseSection.setUpdateUser(user.getUsername());
        courseSection.setCreateTime(new Date());

        courseSectionService.addCourseSection(courseSection);
        return JsonView.getJSONString(0);
    }

    @RequestMapping("/addSection")
    @ResponseBody
    public String addSection(CourseSection courseSection){

        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        courseSection.setOnsale(1);
        courseSection.setSort(1);
        courseSection.setUpdateTime(new Date());
        courseSection.setCreateUser(user.getUsername());
        courseSection.setUpdateUser(user.getUsername());
        courseSection.setCreateTime(new Date());
        courseSectionService.addCourseSection(courseSection);
        return JsonView.getJSONString(0);
    }
}
