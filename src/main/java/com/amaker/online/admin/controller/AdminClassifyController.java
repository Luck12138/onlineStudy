package com.amaker.online.admin.controller;

import com.amaker.online.bussiness.ConstsClassifyBussiness;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.ConstsClassifyVO;
import com.amaker.online.service.ConstsClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: onlinestudy
 * @Date: 2019/1/19 0019 19:17
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminClassifyController {

    @Autowired
    private ConstsClassifyBussiness classifyBussiness;

    @Autowired
    private ConstsClassifyService classifyService;

    @RequestMapping("/classifyIndex")
    public String index(Model model){
        model.addAttribute("curNav","classify");
        List<ConstsClassifyVO> classifyVOList=new ArrayList<>();
        Map<String, ConstsClassifyVO> map = classifyBussiness.selectClassifyMap();
        for(ConstsClassifyVO vo:map.values()){
            classifyVOList.add(vo);
        }
        List<ConstsClassify> subClassify=new ArrayList<>();
        for(ConstsClassifyVO vo:map.values()){
            subClassify.addAll(vo.getSubClassifyList());
        }

        model.addAttribute("classify",classifyVOList);
        model.addAttribute("subClassify",subClassify);
        return "admin/classifyIndex";
    }

    @RequestMapping("/classifyGetById")
    @ResponseBody
    public String getById(int id){
        ConstsClassify classify = classifyService.selectClassify(id);
        return JsonView.getJSONString(classify);
    }

    @RequestMapping("/classifySave")
    @ResponseBody
    public String classifySave(ConstsClassify constsClassify){
        if(constsClassify.getId()==null){
            ConstsClassify classify = classifyService.selectByCode(constsClassify.getCode());
            if(classify!=null){
                return JsonView.getJSONString(1,"编码已存在！");
            }classifyService.addClassify(constsClassify);
        }else {
            classifyService.updateClassify(constsClassify);
        }
      return JsonView.getJSONString(0);
    }

    @RequestMapping("/classifyDelete")
    @ResponseBody
    public String deleteClassify(ConstsClassify constsClassify){
        ConstsClassify classify = classifyService.selectClassify(constsClassify.getId());
        if(classify.getParentCode().equals("0")){
            List<ConstsClassify> classifyList = classifyService.selectByParentCode(classify.getCode());
            for(ConstsClassify c:classifyList){
                classifyService.deleteClassify(c);
            }
            classifyService.deleteClassify(constsClassify);

            return JsonView.getJSONString(0);
        }else {
            classifyService.deleteClassify(constsClassify);
            return JsonView.getJSONString(0);
        }

    }
}
