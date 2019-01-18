package com.amaker.online.service;

import com.amaker.online.common.page.Page;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.dao.CourseCommentDao;
import com.amaker.online.model.CourseComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 16:32
 * @Author: GHH
 * @Description:
 */
@Service
public class CourseCommentService {

    @Autowired
    private CourseCommentDao commentDao;

    public TailPage<CourseComment> selectAllComment(CourseComment courseComment,TailPage<CourseComment> page){

        int commentCount = commentDao.getCommentCount(courseComment);
        List<CourseComment> commentPage = commentDao.selectCommentPage(courseComment, page);
        page.setItemsTotalCount(commentCount);
        page.setItems(commentPage);
        return page;
    }

    public void addComment(CourseComment courseComment){
        commentDao.addComment(courseComment);
    }
}
