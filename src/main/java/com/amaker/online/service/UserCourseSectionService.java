package com.amaker.online.service;

import com.amaker.online.dao.UserCourseSectionDao;
import com.amaker.online.model.UserCourseSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
