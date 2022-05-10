package com.xg.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 上传文件到OSS
     */
    String uploadAvatar(MultipartFile file);

}
