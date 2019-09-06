package com.amaker.online.service;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.dao.ContestDao;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2019/5/13 0013 14:52
 * @Author: Luck
 */
@Service
public class ContestService {

    @Autowired
    private ContestDao contestDao;

    public TailPage<Contest> selectExamPage(Contest contest, TailPage<Contest> page){
        List<Contest> contests = contestDao.selectContestListPage(contest, page);
        int totalItemsCount = contestDao.getTotalItemsCount(contest);

        page.setItemsTotalCount(totalItemsCount);
        page.setItems(contests);
        return page;
    }


    public void addContest(Contest contest){

        contestDao.addContest(contest);
    }



    public void updateScore(int contest,int id){

        contestDao.updateScore(contest,id);
    }
}
