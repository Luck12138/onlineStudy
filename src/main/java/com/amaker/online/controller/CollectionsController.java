package com.amaker.online.controller;

import com.amaker.online.common.CourseEnum;
import com.amaker.online.common.web.JsonView;
import com.amaker.online.model.AuthUser;
import com.amaker.online.model.UserCollections;
import com.amaker.online.service.UserCollectionsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 17:50
 * @Author: GHH
 * @Description:
 */
@Controller
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private UserCollectionsService userCollectionsService;

    @RequestMapping("/isCollection")
    @ResponseBody
    public String isCollection(@RequestParam("courseId") Long courseId){

        AuthUser user =(AuthUser) SecurityUtils.getSubject().getPrincipal();
        UserCollections userCollections=new UserCollections();
        userCollections.setUserId(user.getId());
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setObjectId(courseId);
        List<UserCollections> collectionsList = userCollectionsService.selectCollections(userCollections);
        if(!CollectionUtils.isEmpty(collectionsList)){
            return JsonView.getJSONString(1);
        }else {
            return JsonView.getJSONString(0);
        }

    }


    @RequestMapping("/doCollection")
    @ResponseBody
    public String doCollection(Long courseId){
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        UserCollections userCollections=new UserCollections();
        userCollections.setUserId(user.getId());
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setObjectId(courseId);
        List<UserCollections> collections = userCollectionsService.selectCollections(userCollections);
        if(!CollectionUtils.isEmpty(collections)){
            userCollectionsService.deleteCollection(collections.get(0));
            return JsonView.getJSONString(0);
        }else {
            userCollections.setCreateTime(new Date());
            userCollectionsService.addCollection(userCollections);
            return JsonView.getJSONString(1);
        }
    }
}
