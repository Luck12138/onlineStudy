package com.amaker.online.controller;

import com.amaker.online.bussiness.ConstsClassifyBussiness;
import com.amaker.online.common.CourseEnum;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.ConstsClassifyVO;
import com.amaker.online.model.Course;
import com.amaker.online.service.ConstsClassifyService;
import com.amaker.online.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: onlinestudy
 * @Date: 2019/1/10 0010 15:26
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping(value = "/course")
public class CourseListController {


    @Autowired
    private ConstsClassifyService constsClassifyService;
    @Autowired
    private ConstsClassifyBussiness classifyBussiness;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/list")
    public String list(String c, String sort, TailPage<Course> page,Model model){

        String curCode="-1";
        String curSubCode="-2";
        Map<String, ConstsClassifyVO> map = classifyBussiness.selectClassifyMap();
        List<ConstsClassifyVO> classifyVOS=new ArrayList<>();
        for(ConstsClassifyVO classifyVO:map.values()){
            classifyVOS.add(classifyVO);
        }
        model.addAttribute("classify",classifyVOS);

        ConstsClassify constsClassify = constsClassifyService.selectByCode(c);
        if(constsClassify==null){
            List<ConstsClassify> subClassifyList=new ArrayList<>();
            for(ConstsClassifyVO vo:map.values()){
                subClassifyList.addAll(vo.getSubClassifyList());
            }
            model.addAttribute("subClassify",subClassifyList);
        }else {
            if(!constsClassify.getParentCode().equals("0")){
                curSubCode=constsClassify.getCode();
                curCode=constsClassify.getParentCode();
                model.addAttribute("subClassify",map.get(curCode).getSubClassifyList());
            }else{
                curCode=constsClassify.getCode();
                model.addAttribute("subClassify",map.get(curCode).getSubClassifyList());
            }
        }

        model.addAttribute("curCode", curCode);
        model.addAttribute("curSubCode", curSubCode);

        Course course=new Course();
        if(!curCode.equals("-1")){
            course.setClassify(curCode);
        }
        if(!curSubCode.equals("-2")){
            course.setSubClassify(curSubCode);
        }

        if("pop".equals(sort)){
           page.descSortField("studyCount");
        }else {
            sort="last";
            page.descSortField("id");
        }
        model.addAttribute("sort",sort);
        course.setOnsale(CourseEnum.ONSALE.value());
        TailPage<Course> courses = courseService.selectCoursePage(course, page);
        model.addAttribute("page",courses);
        return "list";
    }
}
