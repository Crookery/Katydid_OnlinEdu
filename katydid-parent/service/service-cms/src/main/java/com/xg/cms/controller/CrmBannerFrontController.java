package com.xg.cms.controller;


import com.xg.cms.entity.CrmBanner;
import com.xg.cms.service.CrmBannerService;
import com.xg.commonutils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台系统中的接口
 *
 * TODO 点击图片进入相应的课程(管理员界面进行url设置即可)
 */


@RestController
@RequestMapping("/cms/bannerFront")
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/getAllBanner")
    public Message getAllBanner(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return Message.successful().add("list",list);
    }
}
