package com.amaker.online.controller;

import com.amaker.online.bussiness.ConstsClassifyBussiness;
import com.amaker.online.common.CourseEnum;
import com.amaker.online.model.*;
import com.amaker.online.service.AuthUserService;
import com.amaker.online.service.CarouseService;
import com.amaker.online.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 13:43
 * @Author: GHH
 * @Description:
 */
@Controller
public class HomeController {

    @Autowired
    private CarouseService carouseService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private ConstsClassifyBussiness classifyBussiness;

    @Autowired
    private AuthUserService authUserService;

    @RequestMapping(value = "/",method = {RequestMethod.GET})
    public String home(Model model){
        //轮播图
        List<ConstsSiteCarousel> carouselList = carouseService.selectCarouseList(4);
        model.addAttribute("carouseList",carouselList);
        //分类显示
        List<ConstsClassifyVO> classifyVOList = classifyBussiness.selectClassifyList();
        classifyBussiness.prepareCourse(classifyVOList);
        model.addAttribute("classifyVo",classifyVOList);
        //实战好课
        CourseQueryDto courseQueryDto=new CourseQueryDto();
        courseQueryDto.setCount(5);
        courseQueryDto.setFree(CourseEnum.FREE_NOT.value());
        courseQueryDto.descSortField("weight");
        List<Course> actionCourseList = courseService.selectCourseByVo(courseQueryDto);
        model.addAttribute("actionCourseList",actionCourseList);
        //免费好课
        courseQueryDto.setFree(CourseEnum.FREE.value());
        List<Course> freeCourseList=courseService.selectCourseByVo(courseQueryDto);
        model.addAttribute("freeCourseList",freeCourseList);
        //java推荐
        courseQueryDto.setFree(null);
        courseQueryDto.setCount(7);
        courseQueryDto.setSubClassify("java");
        courseQueryDto.descSortField("studyCount");
        List<Course> javaCourseList = courseService.selectCourseByVo(courseQueryDto);
        model.addAttribute("javaCourseList",javaCourseList);
        //名师推荐
        List<AuthUser> authUserList = authUserService.selectTeacher();
        model.addAttribute("teacherList",authUserList);
        return "index";
    }


}
