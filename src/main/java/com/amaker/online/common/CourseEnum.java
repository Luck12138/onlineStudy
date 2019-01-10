package com.amaker.online.common;

/**
 * @program: onlinestudy
 * @Date: 2019/1/10 0010 10:24
 * @Author: GHH
 * @Description:
 */
public enum CourseEnum {

    FREE(1), //免费
    FREE_NOT(0), //收费

    ONSALE(1), //上架
    ONSALE_NOT(0), //下架

    COLLECTION_CLASSIFY_COURSE(1);//课程收藏


    private Integer value;
    private CourseEnum(Integer value) {
        this.value = value;
    }

    public Integer value(){
        return value;
    }
}
