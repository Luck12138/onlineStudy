package com.amaker.online;

import com.amaker.online.common.page.TailPage;
import com.amaker.online.common.storage.QiniuStorage;
import com.amaker.online.common.storage.ThumbModel;
import com.amaker.online.common.util.CommonUtil;
import com.amaker.online.model.AuthUser;
import com.amaker.online.service.AuthUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

    @Autowired
    private AuthUserService authUserService;

    public static Logger logger=LoggerFactory.getLogger(CrudTest.class);

    @Test
    public void contextLoads() {

    }

    /**
     * 测试创建用户
     */
    @Test
    public void createUser(){
        logger.info("------------------ create  start ");
        AuthUser authUser=new AuthUser();
        authUser.setUsername("tset");
        authUser.setPassword("test123");
        authUser.setRealname("test张三");
        authUserService.addUser(authUser);
        System.out.println("id = "+authUser.getId());
        logger.info("------------------ create  end ");
    }

    /**
     * 测试通过id查询用户
     */
    @Test
    public void selectUserById(){
        logger.info("------------------ select start ");
        AuthUser user = authUserService.selectUserById(32);
        if(user!=null){
            System.out.println("username:"+user.getUsername());
        }
        logger.info("------------------ select  end ");
    }

    /**
     * 测试更新用户
     */
    @Test
    public void update(){
        logger.info("------------------ create  start ");
        AuthUser authUser = authUserService.selectUserById(32);
        authUser.setRealname("testzhangsan");
        authUserService.updateUserById(authUser);
        System.out.println("realname:"+authUser.getRealname());
        logger.info("------------------ create  end ");
    }

    /**
     * 测试删除用户
     */
    @Test
    public void delete(){
        logger.info("------------------ create  start ");
        authUserService.deleteUserById(32);
        AuthUser authUser = authUserService.selectUserById(32);
        if(authUser==null){
            System.out.println("delete success");
        }
        logger.info("------------------ create  end ");
    }



    @Test
    public void page(){
        logger.info("------------------ create  start ");
        TailPage<AuthUser> queryPage=new TailPage<>();
        TailPage<AuthUser>  page= authUserService.queryPage(new AuthUser(), queryPage);
        System.out.println("page size"+page.getItems().size());
        for(AuthUser authUser:page.getItems()){
            System.out.println("username:"+authUser.getUsername());
        }

        logger.info("------------------ create  end ");
    }

    @Test
    public void qiniu(){
        //测试上传图片
        byte[] buff = CommonUtil.getFileBytes(new File("F://360downloads/banner.jpg"));
        String key = QiniuStorage.uploadImage(buff);
        System.out.println("key = " + key);

        //String key = "/default/all/0/0755ffd19e3e416db0a69ca3b23d744a.jpeg";
        //测试下载图片
        String url = QiniuStorage.getUrl(key);
        System.out.println("url = " + url);

        //测试下载不同大小的图片
        url = QiniuStorage.getUrl(key,ThumbModel.THUMB_256);
        System.out.println("url = " + url);
    }
}

