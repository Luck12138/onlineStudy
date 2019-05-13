package com.amaker.online.service;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.dao.ExamDao;
import com.amaker.online.model.Course;
import com.amaker.online.model.Exam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2019/3/17 0017 13:13
 * @Author: Luck
 */
@Service
public class ExamService {


    @Autowired
    private ExamDao examDao;


    private void prepareCoursePicture(Exam exam){
        if(null != exam && StringUtils.isNotEmpty(exam.getPicture())){
            exam.setPicture(QiniuStorage.getUrl(exam.getPicture()));
        }
    }



    public TailPage<Exam> selectExamPage(Exam exam, TailPage<Exam> page){
        List<Exam> exams = examDao.selectExamListPage(exam, page);
        int totalItemsCount = examDao.getTotalItemsCount(exam);
        for(Exam exam1:exams){
            prepareCoursePicture(exam1);
        }
        page.setItemsTotalCount(totalItemsCount);
        page.setItems(exams);
        return page;
    }

    public List<Exam> selectExamList(Exam exam){
        return examDao.selectExamList(exam);
    }

    public void addExam(Exam exam){
        examDao.addExam(exam);
    }

    public Exam getExamById(int id){
        Exam examById = examDao.getExamById(id);
        prepareCoursePicture(examById);
        return examById;
    }

    public void deleteExamById(int id){
        examDao.deleteExamById(id);
    }


    public void updateAllExam(Exam exam){
        examDao.updateAllExam(exam);
    }
}


