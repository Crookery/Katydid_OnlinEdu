package com.xg.acl.service;

import com.xg.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */
public interface UserService extends IService<User> {
    /**
     * 从数据库中取出用户信息
     */
    User selectByUsername(String username);

    /**
     * 删除用户的时候，同时删除其对应的角色的关系
     */
    void removeUserAndRole(String id);
}
