package com.xg.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.xg.acl.service.IndexService;
import com.xg.commonutils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("/info")
    public Message info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Message.successful().replace(userInfo);
    }

    /**
     * 动态获取权限获取菜单
     */
    @GetMapping("/menu")
    public Message getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Message.successful().add("permissionList", permissionList);
    }

    @PostMapping("/logout")
    public Message logout(){
        return Message.successful();
    }

}
