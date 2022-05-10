package com.xg.edu.client.impl;

import com.xg.commonutils.Message;
import com.xg.edu.client.VodClient;
import org.springframework.stereotype.Component;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public Message deleteVideo(String videoId) {
        return Message.fail().add("msg","服务暂不可用");
    }
}
