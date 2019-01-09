package com.amaker.online.service;

import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.dao.ConstsSiteCarouselDao;
import com.amaker.online.model.ConstsSiteCarousel;
import com.amaker.online.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 15:08
 * @Author: GHH
 * @Description:
 */
@Service
public class CarouseService {

    @Autowired
    private ConstsSiteCarouselDao constsSiteCarouselDao;

    public List<ConstsSiteCarousel> selectCarouseList(int count){
        List<ConstsSiteCarousel> carouselList = constsSiteCarouselDao.selectCarouseList(count);
        for(ConstsSiteCarousel siteCarousel:carouselList){
            siteCarousel.setPicture(QiniuStorage.getUrl(siteCarousel.getPicture()));
        }
        return carouselList;
    }
}
