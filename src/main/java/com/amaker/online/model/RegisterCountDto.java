package com.amaker.online.model;

import java.util.Date;

/**
 * @program: onlinestudy
 * @Date: 2019/1/22 0022 10:37
 * @Author: GHH
 * @Description:
 */
public class RegisterCountDto {

    private Integer totalCount;

    private String dateStr;

    private Date startDate;

    private Date endDate;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
