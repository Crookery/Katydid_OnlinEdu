package com.xg.acl.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IndexService {
    /**
     * 根据用户名获取用户登录信息
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     */
    List<JSONObject> getMenu(String username);

}
