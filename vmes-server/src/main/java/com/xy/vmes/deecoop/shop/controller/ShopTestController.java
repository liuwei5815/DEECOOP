package com.xy.vmes.deecoop.shop.controller;

import com.xy.vmes.deecoop.shop.service.ShopTestService;
import com.xy.vmes.entity.ShopTest;

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
* 说明：vmes_shop_test:销售平台Controller
* @author 刘威 自动生成
* @date 2019-12-23
*/
@RestController
@Slf4j
public class ShopTestController {

    private Logger logger = LoggerFactory.getLogger(ShopTestController.class);

    @Autowired
    private ShopTestService shopTestService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @GetMapping("/shop/shopTest/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/shop/shopTest/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        ShopTest shopTest = shopTestService.selectById(id);
        model.putResult(shopTest);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopTest/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/shop/shopTest/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ShopTest shopTest = (ShopTest)HttpUtils.pageData2Entity(pd, new ShopTest());
        shopTestService.save(shopTest);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopTest/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/shop/shopTest/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ShopTest shopTest = (ShopTest)HttpUtils.pageData2Entity(pd, new ShopTest());
        shopTestService.update(shopTest);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-12-23
    */
    @GetMapping("/shop/shopTest/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/shop/shopTest/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        shopTestService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopTest/listPageShopTests")
    public ResultModel listPageShopTests()  throws Exception {
        logger.info("################/shop/shopTest/listPageShopTests 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = shopTestService.listPageShopTests(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/listPageShopTests 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopTest/exportExcelShopTests")
    public void exportExcelShopTests() throws Exception {
        logger.info("################/shop/shopTest/exportExcelShopTests 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        shopTestService.exportExcelShopTests(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/exportExcelShopTests 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2019-12-23
    */
    @PostMapping("/shop/shopTest/importExcelShopTests")
    public ResultModel importExcelShopTests(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/shop/shopTest/importExcelShopTests 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = shopTestService.importExcelShopTests(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/shop/shopTest/importExcelShopTests 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



