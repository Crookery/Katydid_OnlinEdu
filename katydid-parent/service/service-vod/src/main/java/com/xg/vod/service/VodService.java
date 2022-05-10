package com.xg.vod.service;


import org.springframework.web.multipart.MultipartFile;

public interface VodService {

    /**
     * 上传成功后，返回视频ID
     */
    String uploadVideo(MultipartFile file);

    boolean delVideoById(String videoId);
}
