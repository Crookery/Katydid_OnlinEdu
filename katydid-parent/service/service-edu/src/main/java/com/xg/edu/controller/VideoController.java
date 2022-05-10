package com.xg.edu.controller;


import com.xg.commonutils.Message;
import com.xg.edu.entity.Video;
import com.xg.edu.service.VideoService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */

@RestController
@RequestMapping("/edu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    //增加小节
    @PostMapping("/addVideo")
    public Message addVideo(@RequestBody Video video){
       return  videoService.save(video)?Message.successful():Message.fail();
    }

    //删除小节
    @DeleteMapping("/delVideo/{videoID}")
    public Message delVideo(@PathVariable String videoID){
        return videoService.removeVideoById(videoID)?Message.successful():Message.fail();
    }

    //修改小节
    @PostMapping("/updateVideo")
    public Message updateVideo(@RequestBody Video video){
        videoService.updateById(video);    //需要传id值
        return Message.successful();
    }

    //查询小节
    @GetMapping("/getVideo/{videoID}")
    public Message getVideo(@PathVariable String videoID){
        Video v = videoService.getById(videoID);
        return v!=null? Message.successful().add("videoInfo",v):Message.fail();
    }

}

