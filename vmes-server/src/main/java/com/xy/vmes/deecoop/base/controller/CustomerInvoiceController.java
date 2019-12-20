package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.CustomerInvoice;
import com.xy.vmes.deecoop.base.service.CustomerInvoiceService;
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

import java.util.List;


/**
* 说明：vmes_customer_invoice:客户开票信息Controller
* @author 陈刚 自动生成
* @date 2019-01-09
*/
@RestController
@Slf4j
public class CustomerInvoiceController {

    private Logger logger = LoggerFactory.getLogger(CustomerInvoiceController.class);

    @Autowired
    private CustomerInvoiceService customerInvoiceService;

    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-09
    */
    @PostMapping("/base/customerInvoice/listPageCustomerInvoices")
    public ResultModel listPageCustomerInvoices() throws Exception {
        logger.info("################/base/customerInvoice/listPageCustomerInvoices 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = customerInvoiceService.listPageCustomerInvoices(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/listPageCustomerInvoices 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 陈刚 自动创建，可以修改
     * @date 2019-01-09
     */
    @PostMapping("/base/customerInvoice/findIsExistCustomerInvoice")
    public ResultModel findIsExistCustomerInvoice() throws Exception {
        logger.info("################/base/customerInvoice/findIsExistCustomerInvoice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();

        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        //客户id
        String customerId = pd.getString("customerId");

        String isExistCustomerInvoice = "false";
        List<CustomerInvoice> objectList = null;
        try {
            PageData findMap = new PageData();
            findMap.put("customerId", customerId);
            //是否启用(0:已禁用 1:启用)
            findMap.put("isdefault", "1");
            objectList = customerInvoiceService.findCustomerInvoiceList(findMap);

            if (objectList != null && objectList.size() > 0) {
                isExistCustomerInvoice = "true";
                model.put("customerInvoice", objectList.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("isExistCustomerInvoice", isExistCustomerInvoice);

        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/findIsExistCustomerInvoice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * 新增客户开票信息
     *
     * @author 陈刚
     * @date 2019-01-09
     */
    @PostMapping("/base/customerInvoice/addCustomerInvoice")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addCustomerInvoice() throws Exception {
        logger.info("################/base/customerInvoice/addCustomerInvoice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerInvoiceService.addCustomerInvoice(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/addCustomerInvoice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改客户开票信息
     *
     * @author 陈刚
     * @date 2019-01-09
     */
    @PostMapping("/base/customerInvoice/updateCustomerInvoice")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateCustomerInvoice() throws Exception {
        logger.info("################/base/customerInvoice/updateCustomerInvoice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerInvoiceService.updateCustomerInvoice(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/updateCustomerInvoice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改默认客户开票信息
     *
     * @author 陈刚
     * @date 2019-01-09
     */
    @PostMapping("/base/customerInvoice/updateDefaultCustomerInvoice")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDefaultCustomerInvoice() throws Exception {
        logger.info("################/base/customerInvoice/updateDefaultCustomerInvoice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerInvoiceService.updateDefaultCustomerInvoice(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/updateDefaultCustomerInvoice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除客户开票信息
     * 1. 支持批量删除
     *
     * @author 陈刚
     * @date 2019-01-09
     */
    @PostMapping("/base/customerInvoice/deleteCustomerInvoice")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteCustomerInvoice() throws Exception {
        logger.info("################/base/customerInvoice/deleteCustomerInvoice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerInvoiceService.deleteCustomerInvoice(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customerInvoice/deleteCustomerInvoice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * Excel导出
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-09
    */
    @PostMapping("/base/customerInvoice/exportExcelCustomerInvoices")
    public void exportExcelCustomerInvoices() throws Exception {
        logger.info("################customerInvoice/exportExcelCustomerInvoices 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        customerInvoiceService.exportExcelCustomerInvoices(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customerInvoice/exportExcelCustomerInvoices 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 陈刚 自动创建，可以修改
    * @date 2019-01-09
    */
    @PostMapping("/base/customerInvoice/importExcelCustomerInvoices")
    public ResultModel importExcelCustomerInvoices(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################customerInvoice/importExcelCustomerInvoices 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = customerInvoiceService.importExcelCustomerInvoices(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################customerInvoice/importExcelCustomerInvoices 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



