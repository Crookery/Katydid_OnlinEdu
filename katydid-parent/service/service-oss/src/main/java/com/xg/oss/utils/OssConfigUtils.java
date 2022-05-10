package com.xg.oss.utils;

import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取文件中的配置属性
 * spring加载后，会执行接口 InitializingBean 中的方法
 */
@Setter
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssConfigUtils implements InitializingBean{
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

    //公共静态变量
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
