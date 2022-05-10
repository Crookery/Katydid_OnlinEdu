package com.xg.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.servicebase.exception.KatyException;
import com.xg.vod.service.VodService;
import com.xg.vod.utils.AliSDKUtils;
import com.xg.vod.utils.VodConfigUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            //1.文件流
            InputStream inputStream = file.getInputStream();
            //获取原文件名
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String title = originalFilename.substring(0,originalFilename.lastIndexOf("."));

            //2.创建请求
            UploadStreamRequest request = new UploadStreamRequest(VodConfigUtils.KEY_ID, VodConfigUtils.KEY_SECRET,title,originalFilename,inputStream);
            //设置节点
            request.setApiRegionId("cn-beijing");

            //3.上传
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码
            //其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误："+response.getCode()+", message："+response.getMessage();
                if(StringUtils.isEmpty(videoId)){
                    throw new KatyException(500, errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new KatyException(500, "vod服务上传失败");
        }
    }

    @Override
    public boolean delVideoById(String videoId) {
        try{
            //获取客户端
            DefaultAcsClient client = AliSDKUtils.initVodClient(VodConfigUtils.KEY_ID,VodConfigUtils.KEY_SECRET);
            //创建删除请求
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            return true;
        }catch (ClientException e){
            throw new KatyException(500, "视频删除失败");
        }
    }
}
