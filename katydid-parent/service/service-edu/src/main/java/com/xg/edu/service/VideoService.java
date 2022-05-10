package com.xg.edu.service;

import com.xg.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
public interface VideoService extends IService<Video> {

    boolean removeVideoById(String videoID);

}
