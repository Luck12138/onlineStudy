package com.amaker.online.service;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.dao.QuestionDao;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2019/3/25 0025 11:17
 * @Author: Luck
 */
@Service
public class ProblemService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> selectQuestionList(Question question){
        List<Question> questions = questionDao.selectQuestionList(question);
        return questions;
    }

    public Question selectQuestionById(int id){
       return questionDao.selectQuestionById(id);
    }

    public List<Question> queryQuestionList(){
        return questionDao.queryQuestionList();
    }


    public void addQuestion(Question question){
        questionDao.addQuestion(question);
    }

    public void updateQuestion(Question question){
        questionDao.updateQuestion(question);
    }

    public void deleteQuestion(int id){
        questionDao.deleteById(id);
    }

    public TailPage<Question> selectExamPage(Question question, TailPage<Question> page){
        List<Question> contests = questionDao.selectContestListPage(question, page);
        int totalItemsCount = questionDao.getTotalQuestionCount(question);

        page.setItemsTotalCount(totalItemsCount);
        page.setItems(contests);
        return page;
    }

    public List<Question> selectQuestionByContestId(Question question){
        List<Question> questions = questionDao.selectQuestionByContestId(question);
        return questions;
    }

}
