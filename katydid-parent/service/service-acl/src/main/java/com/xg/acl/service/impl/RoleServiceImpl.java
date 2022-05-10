package com.xg.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.acl.entity.Role;
import com.xg.acl.entity.RolePermission;
import com.xg.acl.entity.UserRole;
import com.xg.acl.mapper.RoleMapper;
import com.xg.acl.service.RolePermissionService;
import com.xg.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.acl.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    //根据用户获取角色数据
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<Role> allRolesList =baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<UserRole> existUserRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        //对角色进行分类
        List<Role> assignRoles = new ArrayList<Role>();
        for (Role role : allRolesList) {
            //已分配
            if(existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    //根据用户分配角色
    @Override
    public void saveUserRoleRelationShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", userId));

        List<UserRole> userRoleList = new ArrayList<>();
        for(String roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<Role> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }

    @Override
    public void removeRoleAndPermission(String id) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",id);
        rolePermissionService.remove(wrapper);
        baseMapper.deleteById(id);
    }
}
