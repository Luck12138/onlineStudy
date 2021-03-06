package com.amaker.online.dao;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.Course;
import com.amaker.online.model.Exam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2019/3/17 0017 12:59
 * @Author: Luck
 */
@Component
public interface ExamDao {

    //查询exam列表
    List<Exam> selectExamListPage(Exam exam, TailPage<Exam> page);

    List<Exam> selectExamList(Exam exam);

    int getTotalItemsCount(Exam exam);

    void addExam(Exam exam);

    Exam getExamById(int id);

    void deleteExamById(int id);

    void updateAllExam(Exam exam);
}
