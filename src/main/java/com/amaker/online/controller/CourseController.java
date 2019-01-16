package com.amaker.online.controller;

import com.alibaba.fastjson.JSONObject;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.common.web.SessionContext;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseSection;
import com.amaker.online.model.UserCourseSection;
import com.amaker.online.service.CourseSectionService;
import com.amaker.online.service.CourseService;
import com.amaker.online.service.UserCourseSectionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
