package com.amaker.online.service;

import com.amaker.online.common.page.Page;
import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.dao.AuthUserDao;
import com.amaker.online.model.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/8 0008 16:20
 * @Author: GHH
 * @Description:
 */
@Service
public class AuthUserService {

    @Autowired
    private AuthUserDao authUserDao;


    public AuthUser selectUserByUsername(String username){
       return authUserDao.getByUsername(username);
    }


    public void addUser(AuthUser authUser){
        authUserDao.addUser(authUser);
    }

    public AuthUser selectUserById(int id){
       return authUserDao.selectUserById(id);
    }

    public void updateUserById(AuthUser authUser){
        authUserDao.updateUser(authUser);
    }

    public void deleteUserById(int id){
        authUserDao.deleteUser(id);
    }

    public TailPage<AuthUser> queryPage(AuthUser authUser, TailPage<AuthUser> page){
        int countByUser = authUserDao.getCountByUser(authUser);
        List<AuthUser> userList = authUserDao.selectUserByPage(authUser, page);
        page.setItemsTotalCount(countByUser);
        page.setItems(userList);
        return page;
    }

    public List<AuthUser> selectTeacher(){
        List<AuthUser> authUsers = authUserDao.selectTeacher();
        for(AuthUser user:authUsers){
            if(StringUtils.isNotEmpty(user.getHeader())){
                user.setHeader(QiniuStorage.getUrl(user.getHeader()));
            }
        }
        return authUsers;
    }


}
