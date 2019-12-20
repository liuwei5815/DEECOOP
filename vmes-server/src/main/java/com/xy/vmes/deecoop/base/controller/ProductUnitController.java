package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.ProductUnit;
import com.xy.vmes.deecoop.base.service.ProductUnitService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;



/**
* 说明：产品计价单位Controller
* @author 刘威 自动生成
* @date 2018-11-15
*/
@RestController
@Slf4j
public class ProductUnitController {
    private Logger logger = LoggerFactory.getLogger(ProductUnitController.class);

    @Autowired
    private ProductUnitService productUnitService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @GetMapping("/base/productUnit/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################productUnit/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        ProductUnit productUnit = productUnitService.selectById(id);
        model.putResult(productUnit);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################productUnit/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ProductUnit productUnit = (ProductUnit)HttpUtils.pageData2Entity(pd, new ProductUnit());

        if(StringUtils.isEmpty(productUnit.getProductId())) {
            model.putCode("1");
            model.putMsg("产品不能为空！");
            return model;
        }

        if(StringUtils.isEmpty(productUnit.getUnit())) {
            model.putCode("1");
            model.putMsg("单位不能为空！");
            return model;
        }


        pd = new PageData();
        pd.put("productId",productUnit.getProductId());
        pd.put("unit",productUnit.getUnit());
        List<ProductUnit> productUnitList = productUnitService.dataList(pd);
        if(productUnitList!=null&&productUnitList.size()>0){
            model.putCode("1");
            model.putMsg("该单位已被使用，不能重复添加！");
            return model;
        }

        productUnitService.updateToNotDefaultByPorId(productUnit.getProductId());

        productUnit.setIsdefault("1");
        productUnitService.save(productUnit);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################productUnit/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        String id  = pd.getString("id");
        ProductUnit productUnit = (ProductUnit)HttpUtils.pageData2Entity(pd, new ProductUnit());
        if("计量单位".equals(productUnit.getType())){
            productUnit.setType("1");
        }else{
            productUnit.setType("0");
        }
        ProductUnit productUnitOld = productUnitService.selectById(id);
        pd = new PageData();
        pd.put("id",productUnitOld.getId());
        pd.put("isSelfExist","true");
        pd.put("productId",productUnitOld.getProductId());
        pd.put("unit",productUnitOld.getUnit());
        List<ProductUnit> productUnitList = productUnitService.dataList(pd);
        if(productUnitList!=null&&productUnitList.size()>0){
            model.putCode("1");
            model.putMsg("该单位已被使用，不能直接修改！");
            return model;
        }


        productUnitService.update(productUnit);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @GetMapping("/base/productUnit/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################productUnit/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        productUnitService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################productUnit/deleteByIds 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
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
            productUnitService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/deleteByIds 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################productUnit/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<ProductUnit> productUnitList = productUnitService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",productUnitList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################productUnit/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //HttpServletResponse response  = HttpUtils.currentResponse();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<ProductUnit> productUnitList = productUnitService.dataList(pd);
        model.putResult(productUnitList);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/




    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/productUnit/updateIsDefaultProductUnit")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateIsDefaultProductUnit()  throws Exception {

        logger.info("################bom/updateIsDefaultProductUnit 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productUnitService.updateIsDefaultProductUnit(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/updateIsDefaultProductUnit 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，可以修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/listPageProductUnits")
    public ResultModel listPageProductUnits()  throws Exception {
        logger.info("################productUnit/listPageProductUnits 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = productUnitService.listPageProductUnits(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/listPageProductUnits 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-11-15
     */
    @PostMapping("/base/productUnit/updateProductUnitPrice")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateProductUnitPrice()  throws Exception {
        logger.info("################productUnit/updateProductUnitPrice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productUnitService.updateProductUnitPrice(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/updateProductUnitPrice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 根据货品id查询(vmes_product_unit) 获取该货品id全部(计价单位,单位换算公式,货品价格)
     *
     * @author 陈刚 自动创建，可以修改
     * @date 2018-12-06
     */
    @PostMapping("/base/productUnit/findListProductUnitByProduct")
    public ResultModel findListProductUnitByProduct() throws Exception {
        logger.info("################productUnit/findListProductUnitByProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productUnitService.findListProductUnitByProduct(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/findListProductUnitByProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 根据货品id查询(vmes_product_unit) 获取该货品id全部(计价单位,单位换算公式,货品价格)
     * 1. 计价单位Map     <计价单位id, 计价单位名称> Map
     * 2. 计价单位货品价格 <计价单位id, 货品价格> Map
     * 3. 计价单位换算公式 <计价单位id, 计量转换计价单位 数量转换公式> Map
     * 4. 计价单位换算公式 <计价单位id, 计价转换计量单位 数量转换公式> Map
     *
     * @author 陈刚 自动创建，可以修改
     * @date 2018-12-06
     */
    @PostMapping("/base/productUnit/findListProductUnit")
    public ResultModel findListProductUnit() throws Exception {
        logger.info("################productUnit/findListProductUnit 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productUnitService.findListProductUnit(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/findListProductUnit 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 订单管理(计量单位,计价单位)单位换算公式计算(货品数量)
     *
     * 参数说明:
     * formula:           单位换算公式
     * parmKey:           公式中的参数key 本接口中取值为(P:计价单位 N:计量单位)
     * stockCount:        (计量单位)库存数量
     * productStockCount: (计量单位)库存可用数量
     * count:             (计价单位)订购数量
     *
     * @author 陈刚 自动创建，可以修改
     * @date 2018-12-06
     */
    @PostMapping("/base/productUnit/formulaReckonByProductCount")
    public ResultModel formulaReckonByProductCount() throws Exception {
        logger.info("################productUnit/formulaReckonByProductCount 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productUnitService.formulaReckonByProductCount(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/formulaReckonByProductCount 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2018-11-15
    */
    @PostMapping("/base/productUnit/exportExcelProductUnits")
    public void exportExcelProductUnits() throws Exception {
        logger.info("################productUnit/exportExcelProductUnits 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        productUnitService.exportExcelProductUnits(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnit/exportExcelProductUnits 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * 货品单位单价 Excel导入
    *
    * @author 陈刚
    * @date 2019-10-28
    */
    @PostMapping("/base/productUnit/importExcelProductUnitByProductPrice")
    public ResultModel importExcelProductUnitByProductPrice(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/base/productUnit/importExcelProductUnitByProductPrice 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        HttpServletRequest httpRequest = HttpUtils.currentRequest();

        String companyId = httpRequest.getParameter("companyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        model = productUnitService.importExcelProductUnitByProductPrice(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/productUnit/importExcelProductUnitByProductPrice 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



