package com.amaker.online.controller;

import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.model.*;
import com.amaker.online.service.AuthUserService;
import com.amaker.online.service.CourseSectionService;
import com.amaker.online.service.CourseService;
import com.amaker.online.service.UserCourseSectionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 10:13
 * @Author: GHH
 * @Description:
 */
@RequestMapping("/course")
@Controller
public class CourseDetailsController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private CourseSectionService courseSectionService;

    @Autowired
    private UserCourseSectionService userCourseSectionService;


    @RequestMapping(value = "/learn/{courseId}")
    public String learn(@PathVariable("courseId") Long courseId, Model model){

        //获取课程
        Course course = courseService.getById(courseId);
        if(course==null){
            return "error/404";
        }
        model.addAttribute("course",course);

        //获取讲师信息
        AuthUser courseTeacher = authUserService.selectUserByUsername(course.getUsername());
        if(null != courseTeacher && StringUtils.isNotEmpty(courseTeacher.getHeader())){
            courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
        }
        model.addAttribute("courseTeacher",courseTeacher);
        //获取推荐课程
        CourseQueryDto courseQueryDto=new CourseQueryDto();
        courseQueryDto.setCount(5);
        courseQueryDto.setSubClassify(course.getSubClassify());
        List<Course> recommendCourse = courseService.selectCourseByVo(courseQueryDto);
        model.addAttribute("recommendCourse",recommendCourse);
        //获取章节
        List<CourseSectionVO> sectionVOList = courseSectionService.selectSectionList(courseId);
        model.addAttribute("chapterSection",sectionVOList);


        //获取学习进度
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        UserCourseSection userCourseSection=new UserCourseSection();
        userCourseSection.setUserId(user.getId());
        userCourseSection.setCourseId(courseId);
        UserCourseSection selectLastSection = userCourseSectionService.selectLastSection(userCourseSection);
        if(selectLastSection!=null){
            CourseSection courseSection = courseSectionService.selectSectionById(selectLastSection.getSectionId());
            model.addAttribute("curCourseSection",courseSection);
        }
        return "learn";
    }
}
