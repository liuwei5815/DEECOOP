package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.TemplateService;
import com.xy.vmes.entity.Template;

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
* 说明：vmes_template:模板Controller
* @author 刘威 自动生成
* @date 2019-01-25
*/
@RestController
@Slf4j
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private TemplateService templateService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-01-25
    */
    @GetMapping("/system/template/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/system/template/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Template template = templateService.selectById(id);
        model.putResult(template);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-01-25
    */
    @PostMapping("/system/template/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/system/template/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Template template = (Template)HttpUtils.pageData2Entity(pd, new Template());
        templateService.save(template);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-01-25
    */
    @PostMapping("/system/template/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/system/template/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Template template = (Template)HttpUtils.pageData2Entity(pd, new Template());
        templateService.update(template);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-01-25
    */
    @GetMapping("/system/template/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/system/template/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        templateService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2019-01-25
    */
    @PostMapping("/system/template/listPageTemplates")
    public ResultModel listPageTemplates()  throws Exception {
        logger.info("################/system/template/listPageTemplates 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = templateService.listPageTemplates(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/listPageTemplates 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2019-01-25
    */
    @PostMapping("/system/template/exportExcelTemplates")
    public void exportExcelTemplates() throws Exception {
        logger.info("################/system/template/exportExcelTemplates 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        templateService.exportExcelTemplates(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/exportExcelTemplates 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2019-01-25
    */
    @PostMapping("/system/template/importExcelTemplates")
    public ResultModel importExcelTemplates(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/system/template/importExcelTemplates 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = templateService.importExcelTemplates(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/template/importExcelTemplates 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



