package com.amaker.online.dao;

import com.amaker.online.model.Course;
import com.amaker.online.model.CourseQueryDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 19:53
 * @Author: GHH
 * @Description:
 */
@Component
public interface CourseDao {

    List<Course> queryCourseByTo(CourseQueryDto courseQueryDto);
}
