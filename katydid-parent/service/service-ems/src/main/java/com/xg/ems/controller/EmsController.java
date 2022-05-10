package com.xg.ems.controller;

import com.xg.commonutils.Message;
import com.xg.commonutils.MyStringUtils;
import com.xg.ems.service.EmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/eduEms/verify")
public class EmsController {

    @Autowired
    private EmsService emsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 限制：仅支持qq邮件
     */
    @PostMapping("/send/{email}")
    public Message verify(@PathVariable String email){
        //缓存是否已有该邮箱的验证码
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)){
            return Message.successful();
        }
        //没有，则发送
        code = MyStringUtils.getSixCode();
        boolean isSend = emsService.send(email,code);
        //发送，放入缓存 ，过期时间：3分钟
        if(isSend){
            redisTemplate.opsForValue().set(email,code,3, TimeUnit.MINUTES);
            return Message.successful();
        }else {
            return Message.fail();
        }
    }

}
