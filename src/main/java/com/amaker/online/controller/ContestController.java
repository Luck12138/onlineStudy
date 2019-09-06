package com.amaker.online.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Exam;
import com.amaker.online.model.Question;
import com.amaker.online.service.ContestService;
import com.amaker.online.service.GradeService;
import com.amaker.online.service.ProblemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Date: 2019/5/13 0013 13:53
 * @Author: Luck
 */
@Controller
@RequestMapping("/contest")
public class ContestController {


    @Autowired
    private ContestService contestService;

    @Autowired
    private ProblemService problemService;


    /**
     * 在线考试列表页
     */
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String contestIndex(TailPage<Contest> page,
                               Model model) {
        String curCode="0";
        String curSubCode="0";
      Contest contest =new Contest();
        page = contestService.selectExamPage(contest, page);

        model.addAttribute("curCode",curCode);
        model.addAttribute("curSubCode",curSubCode);
        model.addAttribute("page",page);
        return "contest/index";
    }

    @RequestMapping(value="/detail/{id}", method= RequestMethod.GET)
    public String contestDetail(@PathVariable("id") int id, TailPage<Question> page,
                               Model model) {
        String curCode="0";
        String curSubCode="0";
        Question question =new Question();
        question.setContestId(id);
        page.setPageSize(1);
        page = problemService.selectExamPage(question, page);


        model.addAttribute("id",id);
        model.addAttribute("curCode",curCode);
        model.addAttribute("curSubCode",curSubCode);
        model.addAttribute("page",page);
        return "contest/detail";
    }
}
