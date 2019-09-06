package com.amaker.online.dao;

import com.amaker.online.model.Grade;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2019/5/14 0014 9:02
 * @Author: Luck
 */
@Component
public interface GradeDao {

    void insertGrade(Grade grade);

    List<Grade> selectGradeByStudentId(Grade grade);
}
