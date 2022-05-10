package com.xg.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.acl.entity.User;
import com.xg.acl.entity.UserRole;
import com.xg.acl.mapper.UserMapper;
import com.xg.acl.service.UserRoleService;
import com.xg.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    //TODO 测试
    @Override
    public void removeUserAndRole(String id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        userRoleService.remove(wrapper);
        baseMapper.deleteById(id);
    }
}
