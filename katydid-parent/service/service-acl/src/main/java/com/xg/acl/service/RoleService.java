package com.xg.acl.service;

import com.xg.acl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户获取角色数据
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 给用户分配角色
     * @param userId 单个用户 ID
     * @param roleId 多个 角色ID
     */
    void saveUserRoleRelationShip(String userId, String[] roleId);


    List<Role> selectRoleByUserId(String id);

    /**
     * 删除角色和角色对应的属性
     * @param id
     */
    void removeRoleAndPermission(String id);
}
