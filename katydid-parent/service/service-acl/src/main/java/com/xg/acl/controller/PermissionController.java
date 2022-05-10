package com.xg.acl.controller;


import com.xg.acl.entity.Permission;
import com.xg.acl.service.PermissionService;
import com.xg.commonutils.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限菜单分配
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */

@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Message indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return Message.successful().add("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Message remove(@PathVariable("id") String id) {
        permissionService.removeChildById(id);
        return Message.successful();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Message doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId,permissionId);
        return Message.successful();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Message toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Message.successful().add("children", list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Message save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Message.successful();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Message updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Message.successful();
    }

}

