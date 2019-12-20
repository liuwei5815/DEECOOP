package com.xy.vmes;

import com.xy.vmes.entity.TreeEntity;
import com.yvan.YvanUtil;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {
    public static void main(String[] args) {
        //System.out.println("***************");
        List<TreeEntity> children = new ArrayList<TreeEntity>();
        TreeEntity tree_1 = new TreeEntity();
        tree_1.setName("用户管理");
        tree_1.setUrl("M00003");
        tree_1.setId("id");
        tree_1.setPid("pid");
        //tree_1.setIcon("icon");
        tree_1.setLayer(Integer.valueOf(1));
        tree_1.setChildren(new ArrayList<TreeEntity>());
        children.add(tree_1);

        TreeEntity tree_2 = new TreeEntity();
        tree_2.setName("角色管理");
        tree_2.setUrl("M00004");
        tree_2.setId("id");
        tree_2.setPid("pid");
        //tree_2.setIcon("icon");
        tree_2.setLayer(Integer.valueOf(1));
        tree_2.setChildren(new ArrayList<TreeEntity>());
        children.add(tree_2);


        TreeEntity tree = new TreeEntity();
        tree.setName("系统");
        tree.setUrl("M00001");
        tree.setId("id");
        tree.setPid("pid");
        tree.setIcon("icon");
        tree.setLayer(Integer.valueOf(1));
        tree.setChildren(children);

        List<TreeEntity> treeList = new ArrayList<TreeEntity>();
        treeList.add(tree);

        String treeJsonStr = YvanUtil.toJson(treeList);
        System.out.println("treeJsonStr: " + treeJsonStr);
    }
}
