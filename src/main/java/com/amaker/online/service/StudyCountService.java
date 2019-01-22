package com.amaker.online.service;

import com.amaker.online.dao.AuthUserDao;
import com.amaker.online.dao.StudyCountDao;
import com.amaker.online.model.CourseStudyStaticsDto;
import com.amaker.online.model.RegisterCountDto;
import com.amaker.online.model.StaticsVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/22 0022 9:58
 * @Author: GHH
 * @Description:
 */
@Service
public class StudyCountService {

    @Autowired
    private StudyCountDao studyCountDao;

    @Autowired
    private AuthUserDao authUserDao;

    public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity) {
        List<CourseStudyStaticsDto> list = studyCountDao.queryCourseStudyStatistics(queryEntity);

        StaticsVO returnVo = new StaticsVO();
        List<String> categories = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();

        if(CollectionUtils.isNotEmpty(list)){
            for(CourseStudyStaticsDto item : list){
                categories.add(item.getDateStr());
                data.add(item.getTotalCount());
            }
            returnVo.setCategories(categories);
            returnVo.setData(data);
        }

        return returnVo;
    }

    public StaticsVO selectRegisterCount(RegisterCountDto registerCountDto) {
        List<RegisterCountDto> list =authUserDao.selectRegisterCount(registerCountDto);

        StaticsVO returnVo = new StaticsVO();
        List<String> categories = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();

        if(CollectionUtils.isNotEmpty(list)){
            for(RegisterCountDto item : list){
                categories.add(item.getDateStr());
                data.add(item.getTotalCount());
            }
            returnVo.setCategories(categories);
            returnVo.setData(data);
        }

        return returnVo;
    }


}
