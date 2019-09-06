package com.amaker.online.admin.controller;

import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.Exam;
import com.amaker.online.model.Question;
import com.amaker.online.service.ExamService;
import com.amaker.online.service.ProblemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/3/19 0019 9:33
 * @Author: Luck
 */
@Controller
@RequestMapping("/admin")
public class AdminExamController {

    private Logger logger= LoggerFactory.getLogger(AdminExamController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping("/examIndex")
    public String exam(Model model){
        Exam exam=new Exam();
        model.addAttribute("curNav","exam");
        List<Exam> examList = examService.selectExamList(exam);

        List<Question> questionList= problemService.queryQuestionList();
        model.addAttribute("questionList",questionList);
        model.addAttribute("examList",examList);
        return "admin/examIndex";
    }


    @RequestMapping("/examSave")
    @ResponseBody
    public String saveExam(Exam exam,
                           @RequestParam("pictureImg") MultipartFile pictureImg) throws IOException {

        if(pictureImg!=null&&pictureImg.getBytes().length>0){
            String key = QiniuStorage.uploadImage(pictureImg.getBytes());
            exam.setPicture(key);
        }
        if(exam.getId()!=null){//更新
           examService.updateAllExam(exam);
        }else {
            //添加
            exam.setCreateTime(new Date());
            examService.addExam(exam);
        }
        return JsonView.getJSONString(0);
    }


    @RequestMapping("/examGetById")
    @ResponseBody
    public String getExamById(int id){

        Exam examById = examService.getExamById(id);
        return JsonView.getJSONString(examById);
    }


    @RequestMapping("/examDelete")
    @ResponseBody
    public String deleteExam(int id){
        examService.deleteExamById(id);
        return JsonView.getJSONString(0);
    }
}

