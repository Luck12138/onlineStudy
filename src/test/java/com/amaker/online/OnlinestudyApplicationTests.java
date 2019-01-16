package com.amaker.online;

import com.amaker.online.common.MD5Utils;
import com.amaker.online.dao.AuthUserDao;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlinestudyApplicationTests {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthUserDao authUserDao;

    public static Logger logger=LoggerFactory.getLogger(OnlinestudyApplicationTests.class);

    @Test
    public void contextLoads() {

//        AuthUser authUser = authUserService.selectUserByUsername("lisi");
//        logger.info(authUser.getSign());
        AuthUser user=new AuthUser();
        user.setUsername("www");
        user.setPassword(MD5Utils.MD5("123456"));
        AuthUser authUser = authUserDao.getUser(user);
        logger.info(authUser.getPassword());
    }

}

