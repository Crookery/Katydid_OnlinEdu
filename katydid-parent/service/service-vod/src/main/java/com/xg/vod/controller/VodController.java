package com.xg.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xg.commonutils.Message;
import com.xg.servicebase.exception.KatyException;
import com.xg.vod.service.VodService;
import com.xg.vod.utils.AliSDKUtils;
import com.xg.vod.utils.VodConfigUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduVod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public Message uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return Message.successful().add("videoID",videoId);
    }

    @ApiOperation("根据ID删除视频")
    @DeleteMapping("/deleteVideo/{videoId}")
    public Message deleteVideo(@PathVariable("videoId") String videoId){
        return vodService.delVideoById(videoId)?Message.successful(): Message.fail();
    }

    @ApiOperation("根据视频id获取视频凭证")
    @GetMapping("/getPlayAuth/{id}")
    public Message getPlayAuth(@PathVariable("id") String id){
        try{
            DefaultAcsClient client = AliSDKUtils.initVodClient(VodConfigUtils.KEY_ID, VodConfigUtils.KEY_SECRET);
            //获取凭证的request对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request中设置视频id
            request.setVideoId(id);
            //获取凭证的response对象
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            String playAuth = response.getPlayAuth();
            return Message.successful().add("playAuth",playAuth);
        }catch (Exception e){
            throw new KatyException(500,"获取凭证失败");
        }
    }

}
