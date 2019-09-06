package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.dao.ContestDao;
import com.amaker.online.dao.QuestionDao;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Exam;
import com.amaker.online.model.Question;
import com.amaker.online.service.ContestService;
import com.amaker.online.service.ExamService;
import com.amaker.online.service.ProblemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/5/13 0013 15:16
 * @Author: Luck
 */

@Controller
@RequestMapping("/admin")
public class AdminContestController {

    @Autowired
    private ContestDao contestDao;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ExamService examService;


    @Autowired
    private ContestService contestService;




    @RequestMapping("/contestIndex")
    public String contest(Contest contest, TailPage<Contest> page,Model model){


            List<Contest> contests = contestDao.selectContestListPage(contest, page);
            int totalItemsCount = contestDao.getTotalItemsCount(contest);

            for(Contest contest1:contests){

                Question question=new Question();
                question.setContestId(contest1.getId());
                List<Question> questionList = problemService.selectQuestionByContestId(question);
                int score=0;
                for(Question question1:questionList){
                    score=question1.getScore()+score;
                }

                contestService.updateScore(score,contest1.getId());
            }

            page.setItemsTotalCount(totalItemsCount);
            page.setItems(contests);
            model.addAttribute("page",page);
        return "admin/contestList";
    }

    @RequestMapping("/contestRead")
    public String contestRead( int id,
                              TailPage<Question> page, Model model){

        Question question=new Question();
        question.setContestId(id);
        problemService.selectExamPage(question,page);
        model.addAttribute("page",page);
        model.addAttribute("id",id);

        Exam exam=new Exam();

        List<Exam> examList = examService.selectExamList(exam);

        model.addAttribute("examList",examList);
        return "admin/contestRead";
    }


    @RequestMapping("/contestAdd")
    public String addContest(Model model){

        Exam exam=new Exam();

        List<Exam> examList = examService.selectExamList(exam);

        model.addAttribute("page",examList);

        return "admin/contestAdd";
    }


    @RequestMapping("/contestDoMerge")
    @ResponseBody
    public String doAddContest(Contest contest){


        if(StringUtils.isBlank(contest.getTitle()))
        {
            return JsonView.getJSONString(1);
        }

        contest.setEndTime(new Date());
        contest.setUpdateTime(new Date());
        contest.setCreateTime(new Date());
        contest.setState(1);
        contestService.addContest(contest);

      return JsonView.getJSONString(0);
    }



}
