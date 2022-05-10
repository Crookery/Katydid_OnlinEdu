package com.xg.acl.helper;

import com.alibaba.fastjson.JSONObject;

import com.xg.acl.entity.Permission;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    /**
     * 构建菜单
     */
    public static List<JSONObject> build(List<Permission> treeNodes) {
        List<JSONObject> menus = new ArrayList<>();
        if(treeNodes.size() == 1) {
            Permission topNode = treeNodes.get(0);
            //左侧一级菜单
            List<Permission> oneMenuList = topNode.getChildren();
            for(Permission one :oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noRedirect");
                oneMenu.put("name", "name_"+one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<Permission> twoMenuList = one.getChildren();
                for(Permission two :twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_"+two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<Permission> threeMenuList = two.getChildren();
                    for(Permission three :threeMenuList) {
                        if(StringUtils.isEmpty(three.getPath())) continue;

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_"+three.getId());
                        threeMenu.put("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }

}
