package com.amaker.online.service;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.dao.ConstsSiteCarouselDao;
import com.amaker.online.model.ConstsSiteCarousel;
import com.amaker.online.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public TailPage<ConstsSiteCarousel> selectCarouseByPage(ConstsSiteCarousel constsSiteCarousel,TailPage<ConstsSiteCarousel> page){
        int carouseCount = constsSiteCarouselDao.selectCarouseCount(constsSiteCarousel);
        List<ConstsSiteCarousel> carouselList = constsSiteCarouselDao.selectCarousePage(constsSiteCarousel, page);
        if(!CollectionUtils.isEmpty(carouselList)){
            for(ConstsSiteCarousel c:carouselList){
                if(c.getPicture()!=null&&!c.getPicture().equals("")){
                    String url = QiniuStorage.getUrl(c.getPicture());
                    c.setPicture(url);
                }

            }
        }
        page.setItemsTotalCount(carouseCount);
        page.setItems(carouselList);
        return page;
    }

    public ConstsSiteCarousel selectCarouseById(ConstsSiteCarousel constsSiteCarousel){
       return constsSiteCarouselDao.selectCarouseById(constsSiteCarousel);
    }


    public void updateCarousel(ConstsSiteCarousel constsSiteCarousel){
        constsSiteCarouselDao.updateCarousel(constsSiteCarousel);
    }

    public void  addCarousel(ConstsSiteCarousel constsSiteCarousel){
        constsSiteCarouselDao.createCarousel(constsSiteCarousel);
    }

    public void  deleteCarousel(ConstsSiteCarousel constsSiteCarousel){
        constsSiteCarouselDao.deleteCarousel(constsSiteCarousel);
    }
}
