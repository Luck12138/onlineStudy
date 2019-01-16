package com.amaker.online.config;

import com.amaker.online.common.web.shiro.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @program: onlinestudy
 * @Date: 2019/1/16 0016 15:55
 * @Author: GHH
 * @Description:
 */
@Configuration
public class FreemarkerConfig {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/");
        freemarker.template.Configuration configuration = freeMarkerConfigurer.createConfiguration();
        configuration.setDefaultEncoding("UTF-8");
        //这里可以添加其他共享变量 比如sso登录地址
        configuration.setSharedVariable("shiro", new ShiroTags());
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }

}
