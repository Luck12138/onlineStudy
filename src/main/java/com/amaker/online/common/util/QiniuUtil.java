package com.amaker.online.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: onlinestudy
 * @Date: 2019/1/7 0007 14:35
 * @Author: GHH
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuUtil {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String path;
}
