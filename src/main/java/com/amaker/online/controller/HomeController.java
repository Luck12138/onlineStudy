package com.amaker.online.controller;

import com.amaker.online.bussiness.ConstsClassifyBussiness;
import com.amaker.online.model.ConstsClassifyVO;
import com.amaker.online.model.ConstsSiteCarousel;
import com.amaker.online.service.CarouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 13:43
 * @Author: GHH
 * @Description:
 */
@Controller
public class HomeController {

    @Autowired
    private CarouseService carouseService;

    @Autowired
    private ConstsClassifyBussiness classifyBussiness;

    @RequestMapping(value = "/",method = {RequestMethod.GET})
    public String home(Model model){

        List<ConstsSiteCarousel> carouselList = carouseService.selectCarouseList(4);
        model.addAttribute("carouseList",carouselList);

        List<ConstsClassifyVO> classifyVOList = classifyBussiness.selectClassifyList();
        classifyBussiness.prepareCourse(classifyVOList);

        model.addAttribute("classifyVo",classifyVOList);
        return "index";
    }


}
