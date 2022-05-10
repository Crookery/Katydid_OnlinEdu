package com.xg.statistics.client;

import com.xg.commonutils.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-ucenter")
public interface UCenterClient {
    @GetMapping("/ucenter/member/countRegister/{day}")
    Message countRegister(@PathVariable("day") String day);
}
