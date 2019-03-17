package com.amaker.online.model;

/**
 * @Date: 2019/3/17 0017 13:00
 * @Author: Luck
 */
public class Exam extends BaseEntity {

    private String name;
    private String picture;
    private String brief;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
