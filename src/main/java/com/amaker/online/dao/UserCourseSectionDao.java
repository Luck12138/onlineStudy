package com.amaker.online.dao;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.UserCourseSection;
import com.amaker.online.model.UserCourseSectionDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 21:34
 * @Author: GHH
 * @Description:
 */
@Component
public interface UserCourseSectionDao {

    UserCourseSection selectSectionById(UserCourseSection userCourseSection);

    UserCourseSection selectLastSection(UserCourseSection userCourseSection);

    void createSelectivity(UserCourseSection userCourseSection);

    void update(UserCourseSection userCourseSection);

    int getUserSectionCount(UserCourseSection userCourseSection);

    List<UserCourseSectionDto> selectUserSectionPage(UserCourseSection userCourseSection, TailPage<UserCourseSectionDto> page);
}
