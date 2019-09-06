package com.amaker.online.admin.controller;

import com.amaker.online.common.web.JsonView;
import com.amaker.online.dao.QuestionDao;
import com.amaker.online.model.Question;
import com.amaker.online.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Date: 2019/3/27 0027 13:49
 * @Author: Luck
 */
@RequestMapping("/admin")
@Controller
public class AdminProblemController {

    @Autowired
    private ProblemService problemService;


    @RequestMapping("/questionSave")
    @ResponseBody
    public String save(Question question){
        if(question.getId()!=null){
          question.setUpdateTime(new Date());
            problemService.updateQuestion(question);
        }else {
            question.setCreateTime(new Date());
            problemService.addQuestion(question);
        }
        return JsonView.getJSONString(0);
    }


    @RequestMapping("/questionSave1")
    @ResponseBody
    public String save1(Question question,int contestId){
        if(question.getId()!=null){
            question.setUpdateTime(new Date());
            problemService.updateQuestion(question);
        }else {
            question.setCreateTime(new Date());
            question.setContestId(contestId);
            problemService.addQuestion(question);
        }
        return JsonView.getJSONString(0);
    }



    @RequestMapping("/questionGetById")
    @ResponseBody
    public String getById(int id){
     return   JsonView.getJSONString(problemService.selectQuestionById(id));
    }


    @RequestMapping("/questionDelete")
    @ResponseBody
    public String deleteById(int id){
        problemService.deleteQuestion(id);
        return   JsonView.getJSONString(0);
    }

}
