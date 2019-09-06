package com.amaker.online.controller;

import com.amaker.online.model.Question;
import com.amaker.online.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Date: 2019/3/25 0025 9:45
 * @Author: Luck
 */
@RequestMapping("/exam")
@Controller
public class ProblemController {


    @Autowired
    private ProblemService problemService;

    @RequestMapping("/problemList/{id}")
    public String problem(@PathVariable("id") int id,
                          Model model){

        Question question=new Question();
        question.setSubjectId(id);
        List<Question> questionList = problemService.selectQuestionList(question);
        model.addAttribute("questionList",questionList);
        return "exam/problemList";
    }


    @RequestMapping("/problemDetail/{id}")
    public String detail(@PathVariable("id") int id,
                         Model model){
        Question question = problemService.selectQuestionById(id);
        model.addAttribute("question",question);
        return "exam/problemDetail";
    }



}
