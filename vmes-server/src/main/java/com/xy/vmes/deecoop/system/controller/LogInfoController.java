package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.LogInfo;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.LogInfoService;
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
import org.apache.commons.lang.StringUtils;

import java.util.*;



/**
* 说明：操作日志Controller
* @author 刘威 自动生成
* @date 2018-08-28
*/
@RestController
@Slf4j
public class LogInfoController {

    private Logger logger = LoggerFactory.getLogger(LogInfoController.class);

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private ColumnService columnService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @GetMapping("/system/logInfo/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################logInfo/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        LogInfo logInfo = logInfoService.selectById(id);
        model.putResult(logInfo);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################logInfo/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        LogInfo logInfo = (LogInfo)HttpUtils.pageData2Entity(pd, new LogInfo());
        logInfo.setCompanyId(pd.getString("currentCompanyId"));
        logInfoService.save(logInfo);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################logInfo/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        LogInfo logInfo = (LogInfo)HttpUtils.pageData2Entity(pd, new LogInfo());
        logInfoService.update(logInfo);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @GetMapping("/system/logInfo/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################logInfo/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        logInfoService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################logInfo/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = new ResultModel();
        String ids = pd.getString("ids");
        if(StringUtils.isEmpty(ids)){
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }
        String id_str = StringUtil.stringTrimSpace(ids);
        String[] id_arry = id_str.split(",");
        if(id_arry.length>0){
            logInfoService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################logInfo/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<LogInfo> logInfoList = logInfoService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",logInfoList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################logInfo/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<LogInfo> logInfoList = logInfoService.dataList(pd);
        model.putResult(logInfoList);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/listPageLogInfos")
    public ResultModel listPageLogInfos()  throws Exception {

        logger.info("################logInfo/listPageLogInfos 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = logInfoService.listPageLogInfos(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/listPageLogInfos 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/exportExcelLogInfos")
    public void exportExcelLogInfos() throws Exception {
        logger.info("################logInfo/exportExcelLogInfos 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //1. 获取Excel导出数据查询条件
        PageData pd = HttpUtils.parsePageData();
        logInfoService.exportExcelLogInfos(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/exportExcelLogInfos 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2018-08-28
    */
    @PostMapping("/system/logInfo/importExcelLogInfos")
    public ResultModel importExcelLogInfos(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################logInfo/importExcelLogInfos 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = logInfoService.importExcelLogInfos(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################logInfo/importExcelLogInfos 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



