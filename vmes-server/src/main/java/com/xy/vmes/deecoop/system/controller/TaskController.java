package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.TaskService;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
* 说明：vmes_task:系统执行表(任务代办)Controller
* @author 陈刚 自动生成
* @date 2019-01-30
*/
@RestController
@Slf4j
public class TaskController {
    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-30
    */
    @PostMapping("/system/task/listPageTasks")
    public ResultModel listPageTasks()  throws Exception {
        logger.info("################/system/task/listPageTasks 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = taskService.listPageTasks(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/task/listPageTasks 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-30
    */
    @PostMapping("/system/task/exportExcelTasks")
    public void exportExcelTasks() throws Exception {
        logger.info("################/system/task/exportExcelTasks 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        taskService.exportExcelTasks(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/task/exportExcelTasks 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-30
    */
    @PostMapping("/system/task/importExcelTasks")
    public ResultModel importExcelTasks(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/system/task/importExcelTasks 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = taskService.importExcelTasks(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/task/importExcelTasks 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



