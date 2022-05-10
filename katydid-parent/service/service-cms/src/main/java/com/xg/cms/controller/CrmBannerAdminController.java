package com.xg.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cms.entity.CrmBanner;
import com.xg.cms.service.CrmBannerService;
import com.xg.commonutils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 后台页面的前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-03-27
 *
 * TODO 管理员页面的Banner
 */

@RestController
@RequestMapping("/cms/bannerAdmin")
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/page/{page}/{limit}")
    public Message pageBanner(@PathVariable("page") long path,
                              @PathVariable("limit") long limit){
        Page<CrmBanner> page = new Page<>(path,limit);
        crmBannerService.page(page,null);
        return Message.successful().add("page",page);
    }

    @PostMapping("/save")
    public Message addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return Message.successful();
    }



}

