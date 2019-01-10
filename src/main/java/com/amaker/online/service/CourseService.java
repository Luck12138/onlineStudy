package com.amaker.online.service;

import com.amaker.online.common.CourseEnum;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.dao.CourseDao;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseQueryDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 20:00
 * @Author: GHH
 * @Description:
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    private void prepareCoursePicture(Course course){
        if(null != course && StringUtils.isNotEmpty(course.getPicture())){
            course.setPicture(QiniuStorage.getUrl(course.getPicture()));
        }
    }

    public List<Course> selectCourseByVo(CourseQueryDto courseQueryDto){
        if(courseQueryDto.getOnsale()==null){
            courseQueryDto.setOnsale(CourseEnum.ONSALE.value());
        }
        List<Course> courseList = courseDao.queryCourseByTo(courseQueryDto);
        for(Course course:courseList){
            course.setPicture(QiniuStorage.getUrl(course.getPicture()));
        }
        return courseList;
    }

    public TailPage<Course> selectCoursePage(Course course,TailPage<Course> page){
        List<Course> courses = courseDao.selectCoursePage(course, page);
        int totalItemsCount = courseDao.getTotalItemsCount(course);
        for(Course course1:courses){
            prepareCoursePicture(course1);
        }
        page.setItemsTotalCount(totalItemsCount);
        page.setItems(courses);
        return page;
    }
}
