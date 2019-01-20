package com.amaker.online.dao;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.ConstsSiteCarousel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 15:17
 * @Author: GHH
 * @Description:
 */
@Component
public interface ConstsSiteCarouselDao {

    List<ConstsSiteCarousel> selectCarouseList(int count);

    int selectCarouseCount(ConstsSiteCarousel constsSiteCarousel);

    List<ConstsSiteCarousel> selectCarousePage(ConstsSiteCarousel constsSiteCarousel, TailPage<ConstsSiteCarousel> page);

    ConstsSiteCarousel selectCarouseById(ConstsSiteCarousel constsSiteCarousel);

    void createCarousel(ConstsSiteCarousel constsSiteCarousel);

    void updateCarousel(ConstsSiteCarousel constsSiteCarousel);

    void deleteCarousel(ConstsSiteCarousel constsSiteCarousel);
}
