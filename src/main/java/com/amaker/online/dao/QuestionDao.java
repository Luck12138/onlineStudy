package com.amaker.online.dao;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2019/3/25 0025 11:09
 * @Author: Luck
 */
@Component
public interface QuestionDao {

    List<Question> selectQuestionList(Question question);

    List<Question> selectQuestionByContestId(Question question);

    Question selectQuestionById(int id);

    List<Question>  queryQuestionList();

    void addQuestion(Question question);

    void updateQuestion(Question question);

    void deleteById(int id);


    //查询exam列表
    List<Question> selectContestListPage(Question question, TailPage<Question> page);

    List<Question> selectContestList(Question question);

    int getTotalQuestionCount(Question question);

}
