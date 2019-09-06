package com.amaker.online.service;

import com.amaker.online.dao.GradeDao;
import com.amaker.online.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2019/5/14 0014 9:10
 * @Author: Luck
 */
@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    public void addGrade(Grade grade){
        gradeDao.insertGrade(grade);
    }

    public List<Grade> selectGradeByStuId(Grade grade){
        return  gradeDao.selectGradeByStudentId(grade);
    }
}
