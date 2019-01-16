package com.amaker.online.dao;

import com.amaker.online.model.CourseSection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 13:46
 * @Author: GHH
 * @Description:
 */
@Component
public interface CourseSectionDao {

    List<CourseSection> selectAllSection(CourseSection courseSection);

    CourseSection selectSectionById(Long id);
}
