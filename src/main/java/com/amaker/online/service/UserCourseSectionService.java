package com.amaker.online.service;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.dao.UserCourseSectionDao;
import com.amaker.online.model.UserCourseSection;
import com.amaker.online.model.UserCourseSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 21:42
 * @Author: GHH
 * @Description:
 */
@Service
public class UserCourseSectionService {

    @Autowired
    private UserCourseSectionDao userCourseSectionDao;

    public UserCourseSection getSectionById(UserCourseSection userCourseSection){
        return userCourseSectionDao.selectSectionById(userCourseSection);
    }

    public UserCourseSection selectLastSection(UserCourseSection userCourseSection){
        return userCourseSectionDao.selectLastSection(userCourseSection);
    }

    public void update(UserCourseSection userCourseSection){
        userCourseSectionDao.update(userCourseSection);
    }

    public void addUserSection(UserCourseSection userCourseSection){
        userCourseSectionDao.createSelectivity(userCourseSection);
    }

    public TailPage<UserCourseSectionDto> selectUserSectionPage(UserCourseSection userCourseSection,TailPage<UserCourseSectionDto> page){
        List<UserCourseSectionDto> userCourseSectionDtos = userCourseSectionDao.selectUserSectionPage(userCourseSection, page);
        int userSectionCount = userCourseSectionDao.getUserSectionCount(userCourseSection);
        page.setItems(userCourseSectionDtos);
        page.setItemsTotalCount(userSectionCount);
        return page;

    }
}
