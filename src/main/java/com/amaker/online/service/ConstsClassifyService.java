package com.amaker.online.service;

import com.amaker.online.dao.ConstsClassifyDao;
import com.amaker.online.model.ConstsClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/9 0009 18:34
 * @Author: GHH
 * @Description:
 */
@Service
public class ConstsClassifyService {

    @Autowired
    private ConstsClassifyDao constsClassifyDao;

    public List<ConstsClassify> selectClassifys(){
        return constsClassifyDao.selectAllClassify();
    }
}
