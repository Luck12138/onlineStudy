package com.amaker.online.controller;

import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.Grade;
import com.amaker.online.model.Question;
import com.amaker.online.service.GradeService;
import com.amaker.online.service.ProblemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/5/14 0014 8:39
 * @Author: Luck
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private GradeService gradeService;


    @RequestMapping("/doAdd")
    @ResponseBody
    public String doAdd(int id,int question,String myAnswer){

        int totalScore=0;

        Question questionById = problemService.selectQuestionById(question);
        if(myAnswer.equals(questionById.getAnswer())){
            totalScore=totalScore+questionById.getScore();
        }
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();

        Grade grade=new Grade();
        grade.setContestId(id);
        grade.setResult(totalScore);
        grade.setStudentId(user.getId());
        grade.setState(1);
        grade.setCreateTime(new Date());

        gradeService.addGrade(grade);


        return JsonView.getJSONString(0);
    }


    @RequestMapping("/check")
    public String check(int id, Model model){

        Grade grade=new Grade();
        grade.setContestId(id);
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        grade.setStudentId(user.getId());

        List<Grade> grades = gradeService.selectGradeByStuId(grade);
        int total=0;
        for(Grade grade1:grades){
            total+=grade1.getResult();
        }

        model.addAttribute("totalScore",total);

        return "contest/check";
    }
}
