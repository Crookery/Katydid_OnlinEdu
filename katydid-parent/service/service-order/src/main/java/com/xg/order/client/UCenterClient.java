package com.xg.order.client;

import com.xg.commonutils.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-ucenter")
public interface UCenterClient {

    @PostMapping("/ucenter/member/getUsrInfoToOrder/{id}")
    public Message getUserInfoToOrder(@PathVariable("id") String id);

}
