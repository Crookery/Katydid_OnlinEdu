package com.xg.ems.service.impl;

import com.xg.ems.service.EmsService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmsServiceImpl implements EmsService {
    private final String EMAIL_MINE = "1024542508@qq.com";
    private final String EMAIL_AUTHORITY = "uinklrxlgetkbdgg";

    @Override
    public boolean send(String email, String code) {
        if(StringUtils.isEmpty(email) || !email.contains("@qq.com")){
            return false;
        }
        HtmlEmail htmlEmail = new HtmlEmail();
        //设置字符集和主机
        htmlEmail.setCharset("utf-8");
        htmlEmail.setHostName("SMTP.qq.com");
        htmlEmail.setSmtpPort(587);

        try {
            //设置授权
            htmlEmail.setAuthentication(EMAIL_MINE,EMAIL_AUTHORITY);
            //设置发送人和收信人
            htmlEmail.setFrom(EMAIL_MINE,"admin-Katydid");
            htmlEmail.addTo(email);
            //设置标题和内容
            htmlEmail.setSubject("Katydid-Online网站验证码");
            htmlEmail.setMsg("感谢您在Katydid-Online网站的注册\n"+"验证码为： "+code+"\n验证码在 3 分钟内有效");
            htmlEmail.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return false;
    }
}
