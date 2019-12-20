package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.MenuTreeService;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：树形结构 Controller (部门,菜单,字典)树形结构
 * 创建人：陈刚
 * 创建时间：2018-07-20
 */
@RestController
@Slf4j
public class MenuTreeController {
    private Logger logger = LoggerFactory.getLogger(MenuTreeController.class);

    @Autowired
    MenuTreeService menuTreeService;
    /**
     * 获得部门树形结构
     * 当前部门节点下面所有子节点树形结构
     *
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    @GetMapping("/system/treeLoad/menuTreeLoad")
    public ResultModel menuTreeLoad() throws Exception{
        return menuTreeService.menuTreeLoad();
    }
}
