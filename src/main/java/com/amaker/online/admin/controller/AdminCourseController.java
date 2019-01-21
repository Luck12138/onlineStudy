package com.amaker.online.admin.controller;

import com.amaker.online.bussiness.ConstsClassifyBussiness;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.ConstsClassifyVO;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseSectionVO;
import com.amaker.online.service.ConstsClassifyService;
import com.amaker.online.service.CourseSectionService;
import com.amaker.online.service.CourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: onlinestudy
 * @Date: 2019/1/20 0020 16:14
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSectionService courseSectionService;

    @Autowired
    private ConstsClassifyBussiness classifyBussiness;

    @Autowired
    private ConstsClassifyService constsClassifyService;

    @RequestMapping("/coursePageList")
    public String course(Course course, TailPage<Course> page, Model model){
        model.addAttribute("curNav","course");
        if(StringUtils.isEmpty(course.getName())){
            course.setName(null);
        }
        page.setPageSize(5);
        TailPage<Course> courses = courseService.selectCoursePage(course, page);
        model.addAttribute("page",courses);
        model.addAttribute("course",course);
        return "admin/courseList";
    }

    @RequestMapping("/courseDoSale")
    @ResponseBody
    public String courseDoSale(Course course){
        courseService.updateCourse(course);
        return JsonView.getJSONString(0);
    }

    @RequestMapping("/courseDelete")
    @ResponseBody
    public String deleteCourse(Course course){
        courseService.deleteCourse(course);
        return JsonView.getJSONString(0);
    }


    @RequestMapping("/courseRead")
    public String courseRead(Long id,Model model){
        //课程详情
        Course course = courseService.getById(id);
        if(course==null){
            return "error/404";
        }else {
            String url = QiniuStorage.getUrl(course.getPicture());
            course.setPicture(url);
        }
        //课程章节
        List<CourseSectionVO> sectionList = courseSectionService.selectSectionList(course.getId());
        //课程分类
        Map<String, ConstsClassifyVO> map = classifyBussiness.selectClassifyMap();
        List<ConstsClassifyVO> classify=new ArrayList<>();
        for(ConstsClassifyVO vo:map.values()){
            classify.add(vo);
        }
        List<ConstsClassify> subClassify=new ArrayList<>();
        for(ConstsClassifyVO vo:map.values()){
            subClassify.addAll(vo.getSubClassifyList());
        }
        model.addAttribute("subClassify",subClassify);
        model.addAttribute("classify",classify);
        model.addAttribute("chapterSection",sectionList);
        model.addAttribute("course",course);
        model.addAttribute("curNav","course");
        return "admin/read";
    }

    @RequestMapping("/courseGetById")
    @ResponseBody
    public String getCourseById(Long id){
        Course course = courseService.getById(id);
        return JsonView.getJSONString(course);
    }

    @RequestMapping("/courseAppend")
    public String addCourseSection(@RequestParam("courseId") Long id, Model model){
        Course course = courseService.getById(id);
        if(course==null){
            return "error/404";
        } else {
            String url = QiniuStorage.getUrl(course.getPicture());
            course.setPicture(url);
        }
        //课程章节
        List<CourseSectionVO> sectionList = courseSectionService.selectSectionList(course.getId());
        model.addAttribute("chapterSection",sectionList);
        model.addAttribute("course",course);
        model.addAttribute("curNav","course");
        return "admin/appendCourse";
    }


    @RequestMapping("/courseDoMerge")
    @ResponseBody
    public String courseDo(Course course,
                           @RequestParam("pictureImg")MultipartFile pictureImg) throws IOException {
        if(pictureImg!=null&&pictureImg.getBytes().length>0){
            String key = QiniuStorage.uploadImage(pictureImg.getBytes());
            course.setPicture(key);
        }

        if(course.getId()!=null){//更新
            courseService.updateAllCourse(course);
        }else {//添加
            if(StringUtils.isNotEmpty(course.getClassify())){
                ConstsClassify constsClassify = constsClassifyService.selectByCode(course.getClassify());
                if(constsClassify!=null){
                    course.setClassifyName(constsClassify.getName());
                }
            }
            if(StringUtils.isNotEmpty(course.getSubClassify())){
                ConstsClassify constsClassify = constsClassifyService.selectByCode(course.getSubClassify());
                if(constsClassify!=null){
                    course.setSubClassifyName(constsClassify.getName());
                }
            }
            courseService.addAllCourse(course);
        }

        return JsonView.getJSONString(0);
    }

    @RequestMapping("/courseAdd")
    public String addCourse(Model model){
        Map<String, ConstsClassifyVO> map = classifyBussiness.selectClassifyMap();
        List<ConstsClassifyVO> classify=new ArrayList<>();
        for(ConstsClassifyVO vo:map.values()){
            classify.add(vo);
        }
        List<ConstsClassify> subClassify=new ArrayList<>();
        for(ConstsClassifyVO vo:map.values()){
            subClassify.addAll(vo.getSubClassifyList());
        }
        model.addAttribute("subClassify",subClassify);
        model.addAttribute("classify",classify);

        model.addAttribute("curNav","course");
        return "admin/add";
    }
}
