package com.amaker.online.bussiness;

import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.ConstsClassifyVO;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseQueryDto;
import com.amaker.online.service.ConstsClassifyService;
import com.amaker.online.service.CourseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 18:38
 * @Author: GHH
 * @Description:
 */
@Component
public class ConstsClassifyBussiness {

    @Autowired
    private ConstsClassifyService classifyService;

    @Autowired
    private CourseService courseService;


    public List<ConstsClassifyVO> selectClassifyList(){
        List<ConstsClassifyVO> classifyVOS=new ArrayList<>();
        for(ConstsClassifyVO classifyVO:this.selectClassifyMap().values()){
            classifyVOS.add(classifyVO);
        }

        return classifyVOS;
    }


    public Map<String,ConstsClassifyVO> selectClassifyMap(){
        Map<String,ConstsClassifyVO> map=new HashMap<>();
        Iterator<ConstsClassify> iterator = classifyService.selectClassifys().iterator();
        while (iterator.hasNext()){
            ConstsClassify constsClassify = iterator.next();
            if(constsClassify.getParentCode().equals("0")){
                ConstsClassifyVO classifyVO=new ConstsClassifyVO();
                BeanUtils.copyProperties(constsClassify,classifyVO);
                map.put(constsClassify.getCode(),classifyVO);
            }else {
                if(constsClassify.getParentCode()!=null){
                    map.get(constsClassify.getParentCode()).getSubClassifyList().add(constsClassify);
                }
            }
        }

        return map;
    }

    public void prepareCourse(List<ConstsClassifyVO> classifyVOList){
        if(CollectionUtils.isNotEmpty(classifyVOList)){
            for(ConstsClassifyVO classifyVO:classifyVOList){
                CourseQueryDto courseQueryDto=new CourseQueryDto();
                courseQueryDto.setCount(5);
                courseQueryDto.descSortField("weight");
                courseQueryDto.setClassify(classifyVO.getCode());

                List<Course> courseList = courseService.selectCourseByVo(courseQueryDto);
                if(CollectionUtils.isNotEmpty(courseList)){
                    classifyVO.setRecomdCourseList(courseList);
                }
            }
        }
    }
}
