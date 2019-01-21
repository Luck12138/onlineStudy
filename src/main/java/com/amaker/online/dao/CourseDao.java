package com.amaker.online.dao;

import com.amaker.online.common.page.Page;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.Course;
import com.amaker.online.model.CourseQueryDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    public static final String TABLE_NAME="t_course";
    public static final String SELECT_FIELDS="  name, type, classify, classify_name, sub_classify, sub_classify_name, direction, username,\n" +
            "        level, free, price, time, onsale, brief, picture,\n" +
            "        recommend, weight, study_count, create_time, create_user, update_time,\n" +
            "        update_user, del, id ";

    List<Course> queryCourseByTo(CourseQueryDto courseQueryDto);

    List<Course> selectCoursePage(Course course, TailPage<Course> pages);

    int getTotalItemsCount(Course course);

    @Select({"select",SELECT_FIELDS," from ",TABLE_NAME,"where id=#{courseId}"})
    public Course getCourseById(@Param("courseId") long courseId);

    void updateCourse(Course course);

    void deleteCourse(Course course);

    void updateAllCourse(Course course);

    void createCourse(Course course);
}
