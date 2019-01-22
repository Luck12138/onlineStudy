package com.amaker.online.dao;

import com.amaker.online.model.CourseStudyStaticsDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/22 0022 9:55
 * @Author: GHH
 * @Description:
 */
@Component
public interface StudyCountDao {

    List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto courseStudyStaticsDto);
}
