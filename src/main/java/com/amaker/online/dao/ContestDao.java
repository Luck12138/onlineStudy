package com.amaker.online.dao;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.Contest;
import com.amaker.online.model.Exam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2019/5/13 0013 14:41
 * @Author: Luck
 */
@Component
public interface ContestDao {

    //查询exam列表
    List<Contest> selectContestListPage(Contest contest, TailPage<Contest> page);

    List<Contest> selectContestList(Contest contest);

    int getTotalItemsCount(Contest contest);

    void addContest(Contest contest);


    void updateScore(int totalScore,int id);
}
