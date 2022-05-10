package com.xg.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.acl.entity.User;
import com.xg.acl.service.RoleService;
import com.xg.acl.service.UserService;
import com.xg.commonutils.MD5;
import com.xg.commonutils.Message;
import com.xg.servicebase.exception.KatyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  用户权限管理
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */

@RestController
@RequestMapping("/admin/acl/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Message index(@PathVariable Long page,@PathVariable Long limit,@RequestBody(required = false) User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(userQueryVo!=null && !StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }
        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return Message.successful().add("items", pageModel.getRecords()).add("total", pageModel.getTotal());
    }

    @ApiOperation(value="根据Id获取用户信息")
    @GetMapping("/get/{userId}")
    public Message get(@PathVariable String userId){
        User user = userService.getById(userId);
        return Message.successful().add("item",user);
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("/save")
    public Message save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Message.successful();
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("/update")
    public Message updateById(@RequestBody User user) {
        userService.updateById(user);
        return Message.successful();
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("/remove/{id}")
    public Message remove(@PathVariable String id) {
        userService.removeUserAndRole(id);
        return Message.successful();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @PostMapping("/batchRemove")
    public Message batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Message.successful();
    }

    @ApiOperation(value = "根据用户id获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Message toAssign(@PathVariable("userId") String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Message.successful().replace(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Message doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRelationShip(userId,roleId);
        return Message.successful();
    }

}

