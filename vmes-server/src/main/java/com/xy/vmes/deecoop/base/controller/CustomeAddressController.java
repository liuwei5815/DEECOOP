package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.CustomeAddress;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.CustomeAddressService;
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
* 说明：vmes_customer_addressController
* @author 陈刚 自动生成
* @date 2018-09-20
*/
@RestController
@Slf4j
public class CustomeAddressController {

    private Logger logger = LoggerFactory.getLogger(CustomeAddressController.class);

    @Autowired
    private CustomeAddressService customeAddressService;

    @Autowired
    private ColumnService columnService;

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @GetMapping("/base/customeAddress/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################customeAddress/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        CustomeAddress customeAddress = customeAddressService.selectById(id);
        model.putResult(customeAddress);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################customeAddress/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        CustomeAddress customeAddress = (CustomeAddress)HttpUtils.pageData2Entity(pd, new CustomeAddress());
        customeAddressService.save(customeAddress);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################customeAddress/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        CustomeAddress customeAddress = (CustomeAddress)HttpUtils.pageData2Entity(pd, new CustomeAddress());
        customeAddressService.update(customeAddress);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @GetMapping("/base/customeAddress/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################customeAddress/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        customeAddressService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################customeAddress/deleteById 执行开始 ################# ");
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
            customeAddressService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################customeAddress/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<CustomeAddress> customeAddressList = customeAddressService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",customeAddressList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################customeAddress/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<CustomeAddress> customeAddressList = customeAddressService.dataList(pd);
        model.putResult(customeAddressList);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/listPageCustomeAddress")
    public ResultModel listPageCustomeAddress() throws Exception {
        logger.info("################customeAddress/listPageCustomeAddress 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = customeAddressService.listPageCustomeAddress(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/listPageCustomeAddress 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 新增客户供应商地址
     *
     * @author 陈刚
     * @date 2018-09-20
     */
    @PostMapping("/base/customeAddress/addCustomerAddress")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addCustomerAddress() throws Exception {
        logger.info("################/customeAddress/addCustomerAddress 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customeAddressService.addCustomerAddress(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/addCustomerAddress 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改客户供应商地址
     *
     * @author 陈刚
     * @date 2018-09-20
     */
    @PostMapping("/base/customeAddress/updateCustomerAddress")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateCustomerAddress() throws Exception {
        logger.info("################/customeAddress/updateCustomerAddress 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customeAddressService.updateCustomerAddress(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/updateCustomerAddress 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改默认客户供应商地址
     *
     * @author 陈刚
     * @date 2018-09-20
     */
    @PostMapping("/base/customeAddress/updateDefaultCustomerAddress")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDefaultCustomerAddress() throws Exception {
        logger.info("################/customeAddress/updateDefaultCustomerAddress 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customeAddressService.updateDefaultCustomerAddress(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/updateDefaultCustomerAddress 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除客户供应商地址
     * 1. 支持批量删除
     *
     * @author 陈刚
     * @date 2018-09-20
     */
    @PostMapping("/base/customeAddress/deleteCustomerAddress")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteCustomerAddress() throws Exception {
        logger.info("################/customeAddress/deleteCustomerAddress 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customeAddressService.deleteCustomerAddress(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/deleteCustomerAddress 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * Excel导出
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/exportExcelCustomeAddresss")
    public void exportExcelCustomeAddresss() throws Exception {
        logger.info("################customeAddress/exportExcelCustomeAddresss 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        customeAddressService.exportExcelCustomeAddresss(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/exportExcelCustomeAddresss 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/customeAddress/importExcelCustomeAddresss")
    public ResultModel importExcelCustomeAddresss(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################customeAddress/importExcelCustomeAddresss 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = customeAddressService.importExcelCustomeAddresss(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################customeAddress/importExcelCustomeAddresss 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



