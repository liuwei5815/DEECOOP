package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.base.service.ProductPropertyService;
import com.xy.vmes.deecoop.base.service.ProductService;
import com.xy.vmes.deecoop.base.service.ProductUnitPriceService;
import com.xy.vmes.deecoop.base.service.ProductUnitService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.*;
import com.yvan.*;
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
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;



/**
* 说明：vmes_product:产品表Controller
* @author 陈刚 自动生成
* @date 2018-09-21
*/
@RestController
@Slf4j
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductUnitService productUnitService;
    @Autowired
    private ProductUnitPriceService productUnitPriceService;
    @Autowired
    private ProductPropertyService productPropertyService;
    @Autowired
    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @GetMapping("/base/product/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################product/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Product product = productService.selectById(id);
        model.putResult(product);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################product/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Product product = (Product)HttpUtils.pageData2Entity(pd, new Product());
        productService.save(product);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################product/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Product product = (Product)HttpUtils.pageData2Entity(pd, new Product());
        productService.update(product);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @GetMapping("/base/product/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################product/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        productService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################product/deleteByIds 执行开始 ################# ");
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
            productService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################product/deleteByIds 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################product/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Product> productList = productService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",productList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################product/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Product> productList = productService.dataList(pd);
        model.putResult(productList);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    @PostMapping("/base/product/findProduct")
    public ResultModel findProduct() throws Exception {
        logger.info("################/base/product/findProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();

        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();

        String productId = pd.getString("productId");
        if (productId == null || productId.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("货品id为空或空字符串！");
            return model;
        }

        Map<String, Object> productMap = new HashMap<>();

        PageData findMap = new PageData();
        findMap.put("productId", productId);
        List<Map> mapList = productService.getDataListPage(findMap);
        if (mapList != null && mapList.size() > 0) {
            productMap = mapList.get(0);
        }

        model.put("productMap", productMap);

        Long endTime = System.currentTimeMillis();
        logger.info("################/base/product/findProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/listPageProducts")
    public ResultModel listPageProducts() throws Exception {
        logger.info("################product/listPageProducts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = productService.listPageProducts(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/listPageProducts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/base/product/listPageProductPropertys")
    public ResultModel listPageProductPropertys() throws Exception {
        logger.info("################product/listPageProductPropertys 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = productService.listPageProductPropertys(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/listPageProductPropertys 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 新增产品物料
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/product/addProduct")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addProduct() throws Exception {
        logger.info("################/product/addProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = new ResultModel();
        //1. 非空判断
        String name = pageData.getString("name");
        if (name == null || name.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("名称输入为空或空字符串，名称为必填不可为空！");
            return model;
        }

        //获取产品编码
        String companyId = pageData.getString("currentCompanyId");
        String code = coderuleService.createCoder(companyId,"vmes_product","P");
        if(StringUtils.isEmpty(code)){
            model.putCode(1);
            model.putMsg("产品编码规则创建异常，请重新操作！");
            return model;
        }

        //2. 添加产品表(vmes_product)
        Product product = (Product) HttpUtils.pageData2Entity(pageData, new Product());
        product.setCuser(pageData.getString("cuser"));
        product.setCompanyId(companyId);
        product.setCode(code);

        //生成产品二维码
        product.setId(Conv.createUuid());
        String qrcode = fileService.createQRCode("product", product.getId());
        if (qrcode != null && qrcode.trim().length() > 0) {
            product.setQrcode(qrcode);
        }
        productService.save(product);

        //3. 添加 vmes_product_unit
        ProductUnit productUnit = productService.product2ProductUnit(product,pageData.getString("unit"));
        productUnitService.save(productUnit);

        //4. 添加 vmes_product_unit_price
        if (product.getPrice() != null) {
            ProductUnitPrice productUnitPrice = productService.product2ProductUnitPrice(product,pageData.getString("unit"));
            productUnitPriceService.save(productUnitPrice);
        }

        //5. 添加产品属性表(vmes_product_property)
        String dataListJsonStr = pageData.getString("prodPropertyJsonStr");
        //测试代码-真实环境无此代码
        //dataListJsonStr = "[{\"name\":\"属性名称_1\",\"value\":\"属性值_1\",\"remark\":\"备注_1\"},{\"name\":\"属性名称_2\",\"value\":\"属性值_2\",\"remark\":\"备注_2\"}]";

        if (dataListJsonStr != null && dataListJsonStr.trim().length() > 0) {
            //JsonString 转换成List<Map<String, Object>>
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) YvanUtil.jsonToList(dataListJsonStr);
            if (dataList == null || dataList.size() == 0) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("自定义产品属性Json字符串-转换成List错误！");
                return model;
            }

            List<ProductProperty> propertyList = this.mapList2ProductPropertyList(dataList);
            productPropertyService.addProductProperty(pageData.getString("cuser"),
                    product.getId(),
                    propertyList);

            List<ProductProperty> productPropertyList = productPropertyService.findProductPropertyListByProdId(product.getId());
            String productPropertyStr = productPropertyService.findPropertyValue(productPropertyList);

            Product productEdit = new Product();
            productEdit.setId(product.getId());
            productEdit.setProperty(productPropertyStr);
            productService.update(productEdit);
        }

        Long endTime = System.currentTimeMillis();
        logger.info("################product/addProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改产品物料
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/product/updateProduct")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateProduct() throws Exception {
        logger.info("################/product/updateProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = productService.updateProduct(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/updateProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改产品物料(禁用)状态
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/product/updateDisableProduct")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableProduct() throws Exception {
        logger.info("################/product/updateDisableProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = productService.updateDisableProduct(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/updateDisableProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除产品物料
     * 1. 支持批量删除
     * 2. 删除产品物料属性表(vmes_product_property)
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/product/deleteProduct")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteProduct() throws Exception {
        logger.info("################/product/deleteProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = productService.deleteProduct(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/deleteProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * Excel导出
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/exportExcelProducts")
    public void exportExcelProducts() throws Exception {
        logger.info("################product/exportExcelProducts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        productService.exportExcelProducts(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################product/exportExcelProducts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 陈刚 自动创建，可以修改
    * @date 2018-09-21
    */
    @PostMapping("/base/product/importExcelProduct")
    public ResultModel importExcelProducts(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/base/product/importExcelProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        if (file == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请上传Excel文件！");
            return model;
        }

        // 验证文件是否合法
        // 获取上传的文件名(文件名.后缀)
        String fileName = file.getOriginalFilename();
        if (fileName == null
                || !(fileName.matches("^.+\\.(?i)(xlsx)$")
                || fileName.matches("^.+\\.(?i)(xls)$"))
                ) {
            String failMesg = "不是excel格式文件,请重新选择！";
            model.putCode(Integer.valueOf(1));
            model.putMsg(failMesg);
            return model;
        }

        // 判断文件的类型，是2003还是2007
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        List<List<String>> dataLst = ExcelUtil.readExcel(file.getInputStream(), isExcel2003);
        List<LinkedHashMap<String, String>> dataMapLst = ExcelUtil.reflectMapList(dataLst);

        HttpServletRequest httpRequest = HttpUtils.currentRequest();
        String companyId = (String)httpRequest.getParameter("companyId");
        String userId = (String)httpRequest.getParameter("userId");

        if (dataMapLst == null || dataMapLst.size() == 1) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("导入文件数据为空，请至少填写一行导入数据！");
            return model;
        }
        //去掉列表名称行
        dataMapLst.remove(0);

        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        String msgStr = this.checkColumnImportExcel(dataMapLst,
                companyId,
                userId,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(this.exportExcelError(msgStr).toString());
            return model;
        }

        //2. Excel导入字段-名称唯一性判断-在Excel文件中
        //3. Excel导入字段-名称唯一性判断-在业务表中判断
        //4. Excel数据添加到货品表
        this.addImportExcelByList(dataMapLst);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/product/importExcelProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                         String companyId,
                                         String userId,
                                         Integer index,
                                         Integer maxShowRow) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int maxRow = 0;
        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //获取全部 货品属性
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productGenre"), null);
        Map<String, String> genreNameKeyMap = dictionaryService.getNameKeyMap();
        //获取全部 计量单位
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productUnit"), companyId);
        Map<String, String> unitNameKeyMap = dictionaryService.getNameKeyMap();
        //获取全部 货品类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productType"), companyId);
        Map<String, String> typeNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //name 货品名称
            String name = mapObject.get("name");
            if (name == null || name.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "货品名称");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //spec 规格型号
            String spec = mapObject.get("spec");
            if (spec == null || spec.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "规格型号");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //genreName 货品属性
            String genreName = mapObject.get("genreName");
            if (genreName == null || genreName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "货品属性");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (genreName != null && genreName.trim().length() > 0) {
                if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "货品属性",
                            genreName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) != null) {
                    //genre 货品属性id
                    mapObject.put("genre", genreNameKeyMap.get(genreName));
                }
            }

            //unitName 计量单位
            String unitName = mapObject.get("unitName");
            if (unitName == null || unitName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "计量单位");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (unitName != null && unitName.trim().length() > 0) {
                if (unitNameKeyMap != null && unitNameKeyMap.size() > 0 && unitNameKeyMap.get(unitName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "计量单位",
                            unitName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (unitNameKeyMap != null && unitNameKeyMap.size() > 0 && unitNameKeyMap.get(unitName) != null) {
                    //unit 计量单位id
                    mapObject.put("unit", unitNameKeyMap.get(unitName));
                }
            }

            //typeName 货品类型
            String typeName = mapObject.get("typeName");
            if (typeName != null && typeName.trim().length() > 0) {
                if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "货品类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) != null) {
                    //type 货品类型id
                    mapObject.put("type", typeNameKeyMap.get(typeName));
                }
            }

            //validityDays 保质期(天) 零的正整数
            String validityDays = mapObject.get("validityDays");
            if (validityDays != null && validityDays.trim().length() > 0) {
                try {
                    BigDecimal validityDays_big = new BigDecimal(validityDays);
                    if (validityDays_big.toString().indexOf(".") != -1) {
                        //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
                        String str_error = MessageFormat.format(msg_column_validityDays_error,
                                (i + index_int),
                                "保质期",
                                validityDays);
                        strBuf.append(str_error);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {
                            return strBuf.toString();
                        }
                    } else if (validityDays_big.doubleValue() <= 0D) {
                        //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
                        String str_error = MessageFormat.format(msg_column_validityDays_error,
                                (i + index_int),
                                "保质期",
                                validityDays);
                        strBuf.append(str_error);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {
                            return strBuf.toString();
                        }
                    } else {
                        //validityDays 保质期(天)
                        mapObject.put("validityDays", validityDays_big.toBigInteger().toString());
                    }
                } catch (NumberFormatException e) {
                    //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
                    String str_error = MessageFormat.format(msg_column_validityDays_error,
                            (i + index_int),
                            "保质期",
                            validityDays);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }

            //price 单价
            String price = mapObject.get("price");
            if (price != null && price.trim().length() > 0) {
                try {
                    BigDecimal price_big = new BigDecimal(price);
                    if (price_big.doubleValue() <= 0D) {
                        //String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！"
                        String str_error = MessageFormat.format(msg_column_price_error,
                                (i + index_int),
                                "单价",
                                price);
                        strBuf.append(str_error);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {
                            return strBuf.toString();
                        }
                    } else {
                        //price 单价 四舍五入到2位小数
                        price_big = price_big.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
                        mapObject.put("price", price_big.toString());
                    }
                } catch (NumberFormatException e) {
                    //String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！"
                    String str_error = MessageFormat.format(msg_column_price_error,
                            (i + index_int),
                            "单价",
                            price);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }

        }

        return strBuf.toString();
    }


    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
        if (objectList == null || objectList.size() == 0) {return;}

        for (int i = 0; i < objectList.size(); i++) {
            Product product = new Product();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            String userId = mapObject.get("userId");
            product.setCuser(userId);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            product.setCompanyId(companyId);

            //sourceCode 企业货品编码 (允许为空)
            String sourceCode = mapObject.get("sourceCode");
            if (sourceCode != null && sourceCode.trim().length() > 0) {
                product.setSourceCode(sourceCode);
            }

            //name 货品名称
            String name = mapObject.get("name");
            product.setName(name);

            //spec 规格型号
            String spec = mapObject.get("spec");
            product.setSpec(spec);

            //genreName 货品属性 genre 货品属性id
            String genre = mapObject.get("genre");
            product.setGenre(genre);

            //typeName 货品类型 type 货品类型id
            String type = mapObject.get("type");
            product.setType(type);

            //validityDays 保质期(天)
            String validityDays_str = mapObject.get("validityDays");
            if (validityDays_str != null && validityDays_str.trim().length() > 0) {
                product.setValidityDays(new BigDecimal (validityDays_str));
            }

            //remark 备注
            String remark = mapObject.get("remark");
            if (remark != null && remark.trim().length() > 0) {
                product.setRemark(remark.trim());
            }

            //unitName 计量单位 unit 计量单位id
            String unit = mapObject.get("unit");

            try {
                //获取产品编码
                String code = coderuleService.createCoder(companyId,"vmes_product","P");
                product.setCode(code);

                //生成产品二维码
                product.setId(Conv.createUuid());
                String qrcode = fileService.createQRCode("product", product.getId());
                if (qrcode != null && qrcode.trim().length() > 0) {
                    product.setQrcode(qrcode);
                }

                //添加 货品表
                productService.save(product);

                //添加 vmes_product_unit
                ProductUnit productUnit = productService.product2ProductUnit(product, unit);
                productUnitService.save(productUnit);

                //price 单价
                String price = mapObject.get("price");
                if (price != null && price.trim().length() > 0) {
                    product.setPrice(new BigDecimal(price));
                    ProductUnitPrice productUnitPrice = productService.product2ProductUnitPrice(product,unit);
                    productUnitPriceService.save(productUnitPrice);
                }

                //System.out.println("第" + (i+1) + "行：添加成功！");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private StringBuffer exportExcelError(String msgStr) {
        StringBuffer msgBuf = new StringBuffer();
        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
        msgBuf.append(msgStr.trim());
        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

        return msgBuf;
    }


    public ProductProperty map2ProdProperty(Map<String, Object> mapObj, ProductProperty object) {
        if (object == null) {object = new ProductProperty();}
        if (mapObj == null) {return object;}

        //{name:属性名称,value:属性值，remark:备注}
        if (mapObj.get("name") != null && mapObj.get("name").toString().trim().length() > 0) {
            object.setName(mapObj.get("name").toString().trim());
        }
        if (mapObj.get("value") != null && mapObj.get("value").toString().trim().length() > 0) {
            object.setValue(mapObj.get("value").toString().trim());
        }
        if (mapObj.get("remark") != null && mapObj.get("remark").toString().trim().length() > 0) {
            object.setRemark(mapObj.get("remark").toString().trim());
        }

        return object;
    }


    public List<ProductProperty> mapList2ProductPropertyList(List<Map<String, Object>> mapList) {
        ArrayList<ProductProperty> objectList = new ArrayList<ProductProperty>();
        if (mapList == null || mapList.size() == 0) {return objectList;}

        for (Map<String, Object> mapObject : mapList) {
            ProductProperty object = this.map2ProdProperty(mapObject, null);
            objectList.add(object);
        }

        return objectList;
    }
}



