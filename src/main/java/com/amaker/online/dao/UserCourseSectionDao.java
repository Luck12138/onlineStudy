package com.amaker.online.dao;

import com.amaker.online.model.UserCourseSection;
import org.springframework.stereotype.Component;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 21:34
 * @Author: GHH
 * @Description:
 */
@Component
public interface UserCourseSectionDao {

    UserCourseSection selectSectionById(UserCourseSection userCourseSection);
}
