package com.xg.oss.controller;

import com.xg.commonutils.Message;
import com.xg.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/eduOss/fileOss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation("头像上传")
    @PostMapping("/upload")
    public Message upload(MultipartFile file){
        String path = ossService.uploadAvatar(file);
        return StringUtils.isEmpty(path)?Message.fail():Message.successful().add("path",path);
    }

}
