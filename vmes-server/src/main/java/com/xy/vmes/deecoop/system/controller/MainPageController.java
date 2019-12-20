package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.MainPageService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：主页 Controller
 * @author 刘威
 * @date 2018-07-27
 */
@RestController
@Slf4j
public class MainPageController {

    @Autowired
    private MainPageService mainPageService;


    private Logger logger = LoggerFactory.getLogger(MainPageController.class);


    /**
     * @author 刘威 修改用户密码
     * @date 2018-07-27
     */
    @PostMapping("/system/mainPage/changePassWord")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel changePassWord()  throws Exception {

        logger.info("################mainPage/changePassWord 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = mainPageService.changePassWord(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################mainPage/changePassWord 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 修改界面样式
     * @date 2018-07-27
     */
    @PostMapping("/system/mainPage/changePageStyle")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel changePageStyle()  throws Exception {

        logger.info("################mainPage/changePageStyle 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = mainPageService.changePageStyle(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################mainPage/changePageStyle 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 保存用户自定义菜单
     * @date 2018-07-27
     */
    @PostMapping("/system/mainPage/saveUserDefinedMenu")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel saveUserDefinedMenu()  throws Exception {

        logger.info("################mainPage/saveUserDefinedMenu 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = mainPageService.saveUserDefinedMenu(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################mainPage/saveUserDefinedMenu 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     * @author 刘威  查询用户自定义菜单
     * @date 2018-07-27
     */
    @PostMapping("/system/mainPage/listUserDefinedMenu")
    public ResultModel listUserDefinedMenu()  throws Exception {

        logger.info("################mainPage/listUserDefinedMenu 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = mainPageService.listUserDefinedMenu(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################mainPage/listUserDefinedMenu 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 查询当前登录用户角色已经绑定的菜单
     * 1. 超级管理员: 全部菜单表数据
     * 2. 非超级管理员(企业管理员,普通用户,外部用户)-当前登录用户角色已经绑定的菜单
     *
     * userType: 当前用户类型-字典表id
     *     userType_admin:超级管理员
     *     userType_company:企业管理员
     *     userType_employee:普通用户
     *     userType_outer:外部用户
     * roleIds:  当前用户角色id
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/mainPage/listRoleMeunAll")
    public ResultModel listRoleMeunAll() throws Exception {
        logger.info("################mainPage/listRoleMeunAll 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = mainPageService.listRoleMeunAll(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################mainPage/listRoleMeunAll 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }




}
