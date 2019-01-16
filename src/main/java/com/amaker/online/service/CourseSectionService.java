package com.amaker.online.service;

import com.amaker.online.common.CourseEnum;
import com.amaker.online.dao.CourseSectionDao;
import com.amaker.online.model.CourseSection;
import com.amaker.online.model.CourseSectionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 13:55
 * @Author: GHH
 * @Description:
 */
@Service
public class CourseSectionService {

    @Autowired
    private CourseSectionDao sectionDao;

    public List<CourseSectionVO> selectSectionList(long id){
        List<CourseSectionVO> resultSection=new ArrayList<>();
        CourseSection courseSection=new CourseSection();
        courseSection.setCourseId(id);
        courseSection.setOnsale(CourseEnum.ONSALE.value());
        List<CourseSection> sectionList = sectionDao.selectAllSection(courseSection);
        Map<Long,CourseSectionVO> map=new HashMap<>();
        for(CourseSection section:sectionList){
            CourseSectionVO sectionVO=new CourseSectionVO();
            if(section.getParentId()==0){
                BeanUtils.copyProperties(section, sectionVO);
                map.put(sectionVO.getId(),sectionVO);
            }else {
                  map.get(section.getParentId()).getSections().add(section);

            }
        }


       for(CourseSectionVO vo:map.values()){
            resultSection.add(vo);
       }
       return resultSection;
    }


    public CourseSection selectSectionById(Long id){
        return sectionDao.selectSectionById(id);
    }
}
