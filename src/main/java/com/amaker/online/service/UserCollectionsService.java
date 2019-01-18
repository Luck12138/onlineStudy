package com.amaker.online.service;

import com.amaker.online.dao.UserCollectionDao;
import com.amaker.online.model.UserCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/17 0017 11:38
 * @Author: GHH
 * @Description:
 */
@Service
public class UserCollectionsService {

    @Autowired
    private UserCollectionDao userCollectionDao;

    public List<UserCollections> selectCollections(UserCollections userCollections){
        return userCollectionDao.selectAllCollection(userCollections);
    }

    public void deleteCollection(UserCollections userCollections){
        userCollectionDao.deleteCollection(userCollections);
    }

    public void addCollection(UserCollections userCollections){
        userCollectionDao.addCollection(userCollections);
    }
}
