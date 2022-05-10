package com.xg.edu.client;

import com.xg.commonutils.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@FeignClient("service-ucenter")
public interface UCenterClient {

    @GetMapping("/ucenter/member/getUserInfo")
    Message getLoginInfo(HttpServletRequest httpServletRequest);

}
