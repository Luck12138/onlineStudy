package com.amaker.online.admin.controller;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.ConstsSiteCarousel;
import com.amaker.online.service.CarouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: onlinestudy
 * @Date: 2019/1/20 0020 10:10
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminCarouselController {

    @Autowired
    private CarouseService carouseService;

    @RequestMapping("/carouselQueryPage")
    public String carouseIndex(Model model,
                               TailPage<ConstsSiteCarousel> page,
                               ConstsSiteCarousel siteCarousel){
        model.addAttribute("curNav","carousel");
        page.setShowPage(5);
        TailPage<ConstsSiteCarousel> carouseByPage = carouseService.selectCarouseByPage(siteCarousel, page);
        model.addAttribute("page",carouseByPage);
        return "admin/pageList";
    }

    @RequestMapping("/carouselToMerge")
    public String carouselTo(ConstsSiteCarousel constsSiteCarousel,Model model){
        if(constsSiteCarousel.getId()!=null){//编辑
            ConstsSiteCarousel siteCarousel = carouseService.selectCarouseById(constsSiteCarousel);
            if(siteCarousel!=null&&StringUtils.isNotEmpty(siteCarousel.getPicture())){
                String url = QiniuStorage.getUrl(siteCarousel.getPicture());
                siteCarousel.setPicture(url);
            }
            model.addAttribute("carousel",siteCarousel);
        }

        return "admin/merge";
    }

    @RequestMapping("/carouselDoMerge")
    public String carouselDo(ConstsSiteCarousel constsSiteCarousel,
                             @RequestParam("pictureImg")MultipartFile pictureImg) throws IOException {
        if(pictureImg!=null&&pictureImg.getBytes().length>0){
            String uploadImage = QiniuStorage.uploadImage(pictureImg.getBytes());
            constsSiteCarousel.setPicture(uploadImage);
        }
        if(constsSiteCarousel.getId()==null){
            carouseService.addCarousel(constsSiteCarousel);
        }else{
            carouseService.updateCarousel(constsSiteCarousel);
        }

        return "redirect:/admin/carouselQueryPage";
    }

    @RequestMapping("/carouselDelete")
    @ResponseBody
    public String delete(ConstsSiteCarousel constsSiteCarousel){
        carouseService.deleteCarousel(constsSiteCarousel);
        return JsonView.getJSONString(0);
    }
}
