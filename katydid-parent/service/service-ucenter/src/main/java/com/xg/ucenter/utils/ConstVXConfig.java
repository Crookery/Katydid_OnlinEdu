package com.xg.ucenter.utils;

import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取vx配置
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "wx.open")
public class ConstVXConfig implements InitializingBean {
    private String app_id;
    private String app_secret;
    private String redirect_url;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = app_id;
        APP_SECRET=app_secret;
        REDIRECT_URL=redirect_url;
    }
}
