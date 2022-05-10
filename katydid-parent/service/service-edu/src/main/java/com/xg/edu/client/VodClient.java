package com.xg.edu.client;

import com.xg.commonutils.Message;
import com.xg.edu.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduVod/video/deleteVideo/{videoId}")
    Message deleteVideo(@PathVariable("videoId") String videoId);

}
