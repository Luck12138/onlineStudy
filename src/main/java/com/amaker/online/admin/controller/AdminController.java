package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.util.CalendarUtil;
import com.amaker.online.common.util.JsonUtil;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.CourseStudyStaticsDto;
import com.amaker.online.model.RegisterCountDto;
import com.amaker.online.model.StaticsVO;
import com.amaker.online.service.AuthUserService;
import com.amaker.online.service.StudyCountService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;

/**
 * @program: onlinestudy
 * @Date: 2019/1/19 0019 9:45
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private StudyCountService studyCountService;

    @RequestMapping("/index")
    public String index(Model model) throws IOException {
        AuthUser user =(AuthUser) SecurityUtils.getSubject().getPrincipal();
        if(!user.getUsername().equals("admin")){
            return "error/404";
        }

        //获取报表统计信息
        RegisterCountDto staticsDto = new RegisterCountDto();
        staticsDto.setEndDate(new Date());
        staticsDto.setStartDate(CalendarUtil.getPreNDay(new Date(), 7));

        StaticsVO staticsVo = studyCountService.selectRegisterCount(staticsDto);
        if(null != staticsVo){
            model.addAttribute("staticsVo",JsonUtil.toJson(staticsVo));
        }
        return "admin/index";
    }

}
