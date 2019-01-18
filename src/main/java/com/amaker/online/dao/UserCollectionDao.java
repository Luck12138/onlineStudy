package com.amaker.online.dao;

import com.amaker.online.model.UserCollections;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/17 0017 11:30
 * @Author: GHH
 * @Description:
 */
@Component
public interface UserCollectionDao {

    List<UserCollections> selectAllCollection(UserCollections userCollections);

    void deleteCollection(UserCollections userCollections);

    void addCollection(UserCollections userCollections);
}
