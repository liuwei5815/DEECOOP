package com.xy.vmes.deecoop.shop.controller;

import com.xy.vmes.deecoop.shop.service.ShopUserService;
import com.xy.vmes.entity.ShopUser;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
* 说明：vmes_shop_user:商城平台用户管理Controller
* @author 刘威 自动生成
* @date 2019-12-23
*/
@RestController
@Slf4j
public class ShopUserController {

    private Logger logger = LoggerFactory.getLogger(ShopUserController.class);

    @Autowired
    private ShopUserService shopUserService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @GetMapping("/shop/shopUser/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/shop/shopUser/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        ShopUser shopUser = shopUserService.selectById(id);
        model.putResult(shopUser);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopUser/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/shop/shopUser/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ShopUser shopUser = (ShopUser)HttpUtils.pageData2Entity(pd, new ShopUser());
        shopUserService.save(shopUser);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopUser/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/shop/shopUser/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ShopUser shopUser = (ShopUser)HttpUtils.pageData2Entity(pd, new ShopUser());
        shopUserService.update(shopUser);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @GetMapping("/shop/shopUser/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/shop/shopUser/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        shopUserService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopUser/listPageShopUsers")
    public ResultModel listPageShopUsers()  throws Exception {
        logger.info("################/shop/shopUser/listPageShopUsers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopUserService.listPageShopUsers(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/listPageShopUsers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopUser/exportExcelShopUsers")
    public void exportExcelShopUsers() throws Exception {
        logger.info("################/shop/shopUser/exportExcelShopUsers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        shopUserService.exportExcelShopUsers(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/exportExcelShopUsers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2019-12-23
     */
    @PostMapping("/shop/shopUser/login")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel login()  throws Exception {

        logger.info("################/shop/shopUser/login 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopUserService.login(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/login 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2019-12-23
     */
    @PostMapping("/shop/shopUser/logout")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel logout()  throws Exception {

        logger.info("################/shop/shopUser/logout 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopUserService.logout(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/logout 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2019-12-23
     */
    @PostMapping("/shop/shopUser/changePassword")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel changePassword()  throws Exception {

        logger.info("################/shop/shopUser/changePassword 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopUserService.changePassword(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/changePassword 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2019-12-23
     */
    @PostMapping("/shop/shopUser/loginCode")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel loginCode()  throws Exception {

        logger.info("################/shop/shopUser/loginCode 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopUserService.loginCode(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopUser/loginCode 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

//    /**
//     * @author 刘威 自动创建，禁止修改
//     * @date 2019-12-23
//     */
//    @PostMapping("/shop/shopUser/createLoginCode")
//    @Transactional(rollbackFor=Exception.class)
//    public ResultModel loginSms()  throws Exception {
//
//        logger.info("################/shop/shopUser/createLoginCode 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        PageData pd = HttpUtils.parsePageData();
//        ResultModel model = shopUserService.createLoginCode(pd);
//        Long endTime = System.currentTimeMillis();
//        logger.info("################/shop/shopUser/createLoginCode 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }



}



