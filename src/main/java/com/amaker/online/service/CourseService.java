package com.amaker.online.service;

import com.amaker.online.dao.CourseDao;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseQueryDto;
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

    public List<Course> selectCourseByVo(CourseQueryDto courseQueryDto){
        return courseDao.queryCourseByTo(courseQueryDto);
    }
}
