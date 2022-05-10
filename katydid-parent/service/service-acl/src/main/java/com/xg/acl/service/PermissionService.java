package com.xg.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.xg.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 获取全部菜单(done)
     */
    List<Permission> queryAllMenu();

    /**
     * 根据角色获取菜单(done)
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * 给角色分配权限(done)
     */
    void saveRolePermissionRelationShip(String roleId, String[] permissionId);

    /**
     * 递归删除菜单(done)
     */
    void removeChildById(String id);

    /**
     * 根据用户id获取用户菜单
     */
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);



}
