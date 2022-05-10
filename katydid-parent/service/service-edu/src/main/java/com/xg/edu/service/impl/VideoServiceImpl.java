package com.xg.edu.service.impl;

import com.xg.edu.client.VodClient;
import com.xg.edu.entity.Video;
import com.xg.edu.mapper.VideoMapper;
import com.xg.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    //远程调用
    @Autowired
    private VodClient vodClient;

    @Override
    public boolean removeVideoById(String videoID) {
        Video v = this.getById(videoID);
        if(v==null){
            return false;
        }
        String videoSourceId = v.getVideoSourceId();
        if(videoSourceId != null){
            vodClient.deleteVideo(videoSourceId);
        }
        this.removeById(videoID);
        return true;
    }
}
