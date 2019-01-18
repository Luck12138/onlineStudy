package com.amaker.online.dao;

import com.amaker.online.common.page.Page;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.model.CourseComment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/11 0011 16:23
 * @Author: GHH
 * @Description:
 */
@Component
public interface CourseCommentDao {

    int getCommentCount(CourseComment courseComment);

    List<CourseComment> selectCommentPage(CourseComment courseComment, TailPage<CourseComment> page);

    void addComment(CourseComment courseComment);
}
