package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.base.service.CustomerService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Customer;
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
import java.text.MessageFormat;
import java.util.*;



/**
 * 说明：vmes_customer:客户供应商表Controller
 * @author 陈刚 自动生成
 * @date 2018-09-18
 */
@RestController
@Slf4j
public class CustomerController {
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    //@Autowired
    //private CustomeAddressService customeAddressService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;
    //@Autowired
    //private ColumnService columnService;


    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @GetMapping("/base/customer/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################customer/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Customer customer = customerService.selectById(id);
        model.putResult(customer);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################customer/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Customer customer = (Customer)HttpUtils.pageData2Entity(pd, new Customer());
        customer.setId(Conv.createUuid());
        customerService.save(customer);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################customer/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Customer customer = (Customer)HttpUtils.pageData2Entity(pd, new Customer());
        customerService.update(customer);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @GetMapping("/base/customer/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################customer/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        customerService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################customer/deleteById 执行开始 ################# ");
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
            customerService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################customer/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Customer> customerList = customerService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",customerList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################customer/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Customer> customerList = customerService.dataList(pd);
        model.putResult(customerList);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


//    @PostMapping("/base/customer/addCustomerBalance")
//    @Transactional(rollbackFor=Exception.class)
//    public ResultModel addCustomerBalance()  throws Exception {
//
//        logger.info("################customer/addCustomerBalance 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        PageData pd = HttpUtils.parsePageData();
//        ResultModel model = customerService.addCustomerBalance(pd);
//        Long endTime = System.currentTimeMillis();
//        logger.info("################customer/addCustomerBalance 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }
//
//
//    @PostMapping("/base/customer/updateCustomerBalance")
//    @Transactional(rollbackFor=Exception.class)
//    public ResultModel updateCustomerBalance()  throws Exception {
//
//        logger.info("################customer/updateCustomerBalance 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        PageData pd = HttpUtils.parsePageData();
//        ResultModel model = customerService.updateCustomerBalancee(pd);
//        Long endTime = System.currentTimeMillis();
//        logger.info("################customer/updateCustomerBalance 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }


    /**
     * @author 陈刚 自动创建，可以修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/listPageCustomers")
    public ResultModel listPageCustomers()  throws Exception {
        logger.info("################customer/listPageCustomers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = customerService.listPageCustomers(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/listPageCustomers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }




    @PostMapping("/base/customer/listPageCustomerReceive")
    public ResultModel listPageCustomerReceive()  throws Exception {
        logger.info("################customer/listPageCustomerReceive 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = customerService.listPageCustomerReceive(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/listPageCustomerReceive 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/base/customer/listPageCustomerAccountDays")
    public ResultModel listPageCustomerAccountDays()  throws Exception {
        logger.info("################customer/listPageCustomerAccountDays 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = customerService.listPageCustomerAccountDays(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/listPageCustomerAccountDays 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 新增客户供应商
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/customer/addCustomer")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addCustomer() throws Exception {
        logger.info("################/customer/addCustomer 执行开始 ################# ");
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
        String companyId = pageData.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        //genre:49c0a7ebcb4c4175bd5195837a6a9a13 供应商:supplierGenre
        //genre:df7cb67fca4148bc9632c908e4a7fdea 客户:customerGenre
        String genre = pageData.getString("genre");

        String genreName = new String();
        if (Common.DICTIONARY_MAP.get("supplierGenre").equals(genre)) {
            genreName = "供应商";
        } else if (Common.DICTIONARY_MAP.get("customerGenre").equals(genre)) {
            genreName = "客户";
        }

        //2. 名称是否存在
        if (customerService.isExistByName(null, genre, companyId, name)) {
            String msgTemp = "{0}名称({1})在系统中已经存在！";
            String msgExist = MessageFormat.format(msgTemp,
                    genreName,
                    name);
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgExist);
            return model;
        }

        //获取客户供应商编码
        String code = coderuleService.createCoder(companyId,"vmes_customer","C");
        if(StringUtils.isEmpty(code)){
            model.putCode(1);
            model.putMsg("客户供应商编码规则创建异常，请重新操作！");
            return model;
        }

        Customer object = (Customer)HttpUtils.pageData2Entity(pageData, new Customer());
        object.setId(Conv.createUuid());
        object.setCuser(pageData.getString("cuser"));
        object.setCompanyId(companyId);
        object.setCode(code);
        //生成客户供应商二维码
        String qrcode = fileService.createQRCode("customer", object.getId());
        if (qrcode != null && qrcode.trim().length() > 0) {
            object.setQrcode(qrcode);
        }
        customerService.save(object);
        model.put("id",object.getId());
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/addCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改客户供应商
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/customer/updateCustomer")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateCustomer() throws Exception {
        logger.info("################/customer/updateCustomer 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerService.updateCustomer(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/updateCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改客户供应商(禁用)状态
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/customer/updateDisableCustomer")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableCustomer() throws Exception {
        logger.info("################/customer/updateDisableCustomer 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerService.updateDisableCustomer(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/updateDisableCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除客户供应商
     * 1. 支持批量删除
     * 2. 如果删除数据被引用，则禁用该数据
     * 3. 如果删除数据未被引用，则直接物理删除
     *
     * @author 陈刚
     * @date 2018-09-19
     */
    @PostMapping("/base/customer/deleteCustomers")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteCustomers() throws Exception {
        logger.info("################/customer/deleteCustomers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = customerService.deleteCustomers(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/deleteCustomers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * Excel导出
     * @author 陈刚 自动创建，可以修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/exportExcelCustomers")
    public void exportExcelCustomers() throws Exception {
        logger.info("################customer/exportExcelCustomers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        customerService.exportExcelCustomers(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/exportExcelCustomers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
     * Excel导入
     *
     * @author 陈刚 自动创建，可以修改
     * @date 2018-09-18
     */
    @PostMapping("/base/customer/importExcelCustomers")
    public ResultModel importExcelCustomers(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################customer/importExcelCustomers 执行开始 ################# ");
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
        logger.info("################customer/importExcelCustomers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威
     * @date 2018-07-31
     */
    @PostMapping("/base/customer/listTreeCustomer")
    public ResultModel listTreeCustomer()  throws Exception {
        logger.info("################customer/listTreeCustomer 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = customerService.listTreeCustomer(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################customer/listTreeCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 获取当前企业客户树形结构
     * 接口参数:
     *   companyId: 企业id(登录用户所属企业id)
     *   customerGenre: 客户属性(字典表 vmes_dictionary)
     *     49c0a7ebcb4c4175bd5195837a6a9a13 供应商
     *     df7cb67fca4148bc9632c908e4a7fdea 客户
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/base/customer/treeCustomer")
    public ResultModel treeCustomer() throws Exception {
        logger.info("################/base/customer/treeCustomer 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();

        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();

        String companyId = pd.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        String customerGenre = new String();
        if (pd.getString("customerGenre") != null) {
            customerGenre = pd.getString("customerGenre");
        }

        //(企业id,客户属性)查询客户表(vmes_customer)
        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        findMap.put("genre", customerGenre);
        findMap.put("orderStr", "cdate asc");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Customer> customerList = customerService.findCustomerList(findMap);

        List<TreeEntity> treeList = new ArrayList<>();
        if (customerList != null && customerList.size() > 0) {
            //for (Customer object : customerList) {
            for (int i = 0; i < customerList.size(); i++) {
                Customer object = customerList.get(i);
                TreeEntity treeNode = new TreeEntity();

                treeNode.setId(object.getId());
                treeNode.setValue(object.getId());

                treeNode.setName(object.getName());
                treeNode.setLabel(object.getName());

                //customerSupplierGenre:b166cc9397744f0cbbea3244647305ee(字典id)
                treeNode.setPid(Common.DICTIONARY_MAP.get("customerSupplierGenre"));
                treeNode.setSerialNumber(Integer.valueOf(0+1));
                treeNode.setIsdisable(object.getIsdisable());

                treeList.add(treeNode);
            }
        }

        //rootNode
        TreeEntity rootNode = new TreeEntity();
        rootNode.setId(Common.DICTIONARY_MAP.get("customerSupplierGenre"));
        rootNode.setValue(Common.DICTIONARY_MAP.get("customerSupplierGenre"));
        rootNode.setName("客户");
        rootNode.setLabel("客户");
        rootNode.setPid("root");
        rootNode.setSerialNumber(Integer.valueOf(0));
        rootNode.setIsdisable("1");
        treeList.add(rootNode);

        TreeEntity treeObj = TreeUtil.switchTree(Common.DICTIONARY_MAP.get("customerSupplierGenre"), treeList);
        //String treeJsonStr = YvanUtil.toJson(treeObj);
        //System.out.println("treeJsonStr: " + treeJsonStr);

        Map result = new HashMap();
        result.put("treeList", treeObj);

        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/customer/treeCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private StringBuffer exportExcelError(String msgStr) {
        StringBuffer msgBuf = new StringBuffer();
        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
        msgBuf.append(msgStr.trim());
        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

        return msgBuf;
    }


    private String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
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

        //获取全部 客户供应商属性
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("customerSupplierGenre"), null);
        Map<String, String> genreNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 客户类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("customerType"), companyId);
        Map<String, String> customerTypeNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 供应商类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("supplierType"), companyId);
        Map<String, String> supplierTypeNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 全国区域名称
        dictionaryService.implementBusinessMapByAreaPath();
        Map<String, String> areaPathNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //name 名称
            String name = mapObject.get("name");
            if (name == null || name.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "名称");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //genreName 属性
            String genreName = mapObject.get("genreName");
            if (genreName == null || genreName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "属性");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (genreName != null && genreName.trim().length() > 0) {
                if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "属性",
                            genreName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) != null) {
                    //genre 属性id
                    mapObject.put("genre", genreNameKeyMap.get(genreName));
                }
            }

            //typeName 类型
            String typeName = mapObject.get("typeName");
            if (typeName != null && typeName.trim().length() > 0) {
                //genre 属性id
                String genre = mapObject.get("genre");

                //客户
                if (Common.DICTIONARY_MAP.get("customerGenre").equals(genre)
                        && customerTypeNameKeyMap != null && customerTypeNameKeyMap.size() > 0 && customerTypeNameKeyMap.get(typeName) == null
                        ) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            genreName + "类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (customerTypeNameKeyMap != null && customerTypeNameKeyMap.size() > 0 && customerTypeNameKeyMap.get(typeName) != null) {
                    //type 类型id
                    mapObject.put("type", customerTypeNameKeyMap.get(typeName));
                }

                //供应商
                if (Common.DICTIONARY_MAP.get("supplierGenre").equals(genre)
                        && supplierTypeNameKeyMap != null && supplierTypeNameKeyMap.size() > 0 && supplierTypeNameKeyMap.get(typeName) == null
                        ) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            genreName + "类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (supplierTypeNameKeyMap != null && supplierTypeNameKeyMap.size() > 0 && supplierTypeNameKeyMap.get(typeName) != null) {
                    //type 类型id
                    mapObject.put("type", supplierTypeNameKeyMap.get(typeName));
                }
            }

            //provinceName 地区
            String provinceName = mapObject.get("provinceName");
            if (provinceName != null && provinceName.trim().length() > 0) {
                if (areaPathNameKeyMap != null && areaPathNameKeyMap.size() > 0 && areaPathNameKeyMap.get(provinceName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "地区",
                            provinceName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (areaPathNameKeyMap != null && areaPathNameKeyMap.size() > 0 && areaPathNameKeyMap.get(provinceName) != null) {
                    //province 区域id
                    mapObject.put("province", areaPathNameKeyMap.get(provinceName));
                }
            }

            //remark 备注
        }

        return strBuf.toString();
    }

    private void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
        if (objectList == null || objectList.size() == 0) {return;}

        for (int i = 0; i < objectList.size(); i++) {
            Customer customer = new Customer();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            String userId = mapObject.get("userId");
            customer.setCuser(userId);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            customer.setCompanyId(companyId);

            //name 名称
            String name = mapObject.get("name");
            customer.setName(name);

            //genreName 属性 genre 属性id
            String genre = mapObject.get("genre");
            customer.setGenre(genre);

            //typeName 类型 type 类型id
            String type = mapObject.get("type");
            customer.setType(type);

            //provinceName 地区  province 区域id
            String province = mapObject.get("province");
            customer.setProvince(province);

            //remark 备注
            String remark = mapObject.get("remark");
            if (remark != null && remark.trim().length() > 0) {
                customer.setRemark(remark.trim());
            }

            try {
                //获取客户供应商编码
                String code = coderuleService.createCoder(companyId,"vmes_customer","C");
                customer.setCode(code);

                //生成客户供应商二维码
                customer.setId(Conv.createUuid());
                String qrcode = fileService.createQRCode("customer", customer.getId());
                if (qrcode != null && qrcode.trim().length() > 0) {
                    customer.setQrcode(qrcode);
                }
                customerService.save(customer);

                //System.out.println("第" + (i+1) + "行：添加成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}