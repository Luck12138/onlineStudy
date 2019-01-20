package com.amaker.online.service;

import com.amaker.online.dao.ConstsClassifyDao;
import com.amaker.online.model.ConstsClassify;
import com.amaker.online.model.ConstsSiteCarousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    public ConstsClassify selectByCode(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        ConstsClassify  constsClassify=new ConstsClassify();
        constsClassify.setCode(code);
        List<ConstsClassify> classifyList = constsClassifyDao.selectClassifyByCondition(constsClassify);
        if(CollectionUtils.isEmpty(classifyList)){
            return null;
        }
        return classifyList.get(0);
    }

    public ConstsClassify selectClassify(int id){
        return constsClassifyDao.selectClassifyById(id);
    }

    public void updateClassify(ConstsClassify constsClassify){
        constsClassifyDao.updateClassify(constsClassify);
    }

    public void addClassify(ConstsClassify constsClassify){
        constsClassifyDao.createClassify(constsClassify);
    }

    public void deleteClassify(ConstsClassify constsClassify){
        constsClassifyDao.deleteClassify(constsClassify);
    }

    public ConstsClassify selectClassify(Long id){
        return constsClassifyDao.selectById(id);
    }

    public List<ConstsClassify> selectByParentCode(String parentCode){
        ConstsClassify constsClassify=new ConstsClassify();
        constsClassify.setParentCode(parentCode);
       return constsClassifyDao.selectClassifyByCondition(constsClassify);
    }


}
