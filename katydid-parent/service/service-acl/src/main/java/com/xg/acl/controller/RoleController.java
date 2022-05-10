package com.xg.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.acl.entity.Role;
import com.xg.acl.service.RoleService;
import com.xg.commonutils.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  权限角色管理
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */

@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "分页-获取角色列表")
    @GetMapping("/{page}/{limit}")
    public Message index(@PathVariable Long page,@PathVariable Long limit,Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return Message.successful().add("items", pageParam.getRecords()).add("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("/get/{id}")
    public Message getRole(@PathVariable String id) {
        Role role = roleService.getById(id);
        return Message.successful().add("item", role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/save")
    public Message saveRole(@RequestBody Role role) {
        roleService.save(role);
        return Message.successful();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Message updateRoleById(@RequestBody Role role) {
        roleService.updateById(role);
        return Message.successful();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Message removeRoleById(@PathVariable String id) {
        roleService.removeRoleAndPermission(id);
        return Message.successful();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("/batchRemove")
    public Message batchRemoveRoleByIds(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return Message.successful();
    }

}

