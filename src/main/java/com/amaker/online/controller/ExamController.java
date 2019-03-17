package com.amaker.online.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.Exam;
import com.amaker.online.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date: 2019/3/17 0017 10:23
 * @Author: Luck
 */
@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @RequestMapping("/list")
    public String list(TailPage<Exam> page, Model model){


        String curCode="0";
        String curSubCode="0";
        Exam exam=new Exam();
        page = examService.selectExamPage(exam, page);
        model.addAttribute("curCode",curCode);
        model.addAttribute("curSubCode",curSubCode);
        model.addAttribute("page",page);
        return "exam/examlist";
    }

}
