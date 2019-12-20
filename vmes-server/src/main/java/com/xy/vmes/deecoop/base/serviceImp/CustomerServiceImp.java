package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.deecoop.base.service.CustomeAddressService;
import com.xy.vmes.deecoop.base.service.CustomerExcelService;
import com.xy.vmes.deecoop.base.service.CustomerService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.entity.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.base.dao.CustomerMapper;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 说明：vmes_customer:客户供应商表 实现类
 * 创建人：陈刚 自动创建
 * 创建时间：2018-09-18
 */
@Service
@Transactional(readOnly = false)
public class CustomerServiceImp implements CustomerService {


    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomeAddressService customeAddressService;

//    @Autowired
//    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private ColumnService columnService;

//    @Autowired
//    private SaleReceiveRecordService saleReceiveRecordService;
    @Autowired
    private CustomerExcelService customerExcelService;


//    @Autowired
//    private WarehouseOutService warehouseOutService;
//    @Autowired
//    private WarehouseInService warehouseInService;
//    @Autowired
//    private SaleOrderService saleOrderService;
//    @Autowired
//    private PurchaseOrderService purchaseOrderService;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void save(Customer customer) throws Exception{
        customer.setCdate(new Date());
        customer.setUdate(new Date());
        customerMapper.insert(customer);
    }


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void update(Customer customer) throws Exception{
        customer.setUdate(new Date());
        customerMapper.updateById(customer);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void updateAll(Customer customer) throws Exception{
        customer.setUdate(new Date());
        customerMapper.updateAllColumnById(customer);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    //@Cacheable(cacheNames = "customer", key = "''+#id")
    public Customer selectById(String id) throws Exception{
        return customerMapper.selectById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void deleteById(String id) throws Exception{
        customerMapper.deleteById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        customerMapper.deleteByIds(ids);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<Customer> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customerMapper.dataListPage(pd,pg);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<Customer> dataList(PageData pd) throws Exception{
        return customerMapper.dataList(pd);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return customerMapper.findColumnList();
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return customerMapper.findDataList(pd);
    }


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        customerMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<Customer> selectByColumnMap(Map columnMap) throws Exception{
        List<Customer> customerList =  customerMapper.selectByMap(columnMap);
        return customerList;
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customerMapper.getDataListPage(pd,pg);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        customerMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


    @Override
    public List<Map> getReceiveDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customerMapper.getReceiveDataListPage(pd,pg);
    }


    @Override
    public List<TreeEntity> getTreeList(PageData pd) throws Exception{
        return  customerMapper.getTreeList(pd);
    }

    public Customer findCustomer(PageData object) {
        if (object == null) {return null;}

        List<Customer> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    public Customer findCustomerById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Customer> objectList = this.findCustomerList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }

    public List<Customer> findCustomerList(PageData object) {
        if (object == null) {return null;}

        List<Customer> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    /**
     * check客户列表List<Customer>是否允许删除
     *
     * 创建人：陈刚
     * 创建时间：2018-09-18
     * @param ids
     * @return
     */
    public boolean checkDeleteCustomerByIds(String ids) {
        return true;
    }

    /**
     * 获取(删除,修改)客户表主键id(vmes_customer)-通过','分隔的字符串
     *     <"DeleteIds", 客户表主键id>
     *     <"UpdateIds", 客户表主键id>
     * @param ids
     * @return
     */
    public Map<String, String> findDeleteCustIdsByIds(String ids) {
        Map<String, String> mapObject = new HashMap<String, String>();
        if (ids == null || ids.trim().length() == 0) {return mapObject;}

        String deleteIds = "";
        String updateIds = "";
        String[] id_arry = ids.split(",");
        for (int i = 0; i < id_arry.length; i++) {
            String id = id_arry[i];
            if (this.checkDeleteCustomerByIds(id)) {
                updateIds = updateIds + id + ",";
            } else {
                deleteIds = deleteIds + id + ",";
            }
        }

        if (deleteIds.trim().length() > 0) {
            deleteIds = deleteIds.substring(0, deleteIds.lastIndexOf(","));
        }
        if (updateIds.trim().length() > 0) {
            updateIds = updateIds.substring(0, updateIds.lastIndexOf(","));
        }

        mapObject.put("DeleteIds", deleteIds);
        mapObject.put("UpdateIds", updateIds);

        return mapObject;
    }


//    @Override
//    public void updateCustomerBalance(
//            Customer oldCustomer,
//            BigDecimal balance,
//            String uuser,
//            String type,
//            String remark) throws Exception {
//        PageData pd = new PageData();
//        pd.put("id",oldCustomer.getId());
//        pd.put("version",oldCustomer.getVersion());
//        pd.put("uuser",uuser);
//        pd.put("balance",balance);
//        customerMapper.updateCustomerBalance(pd);
//
//        SaleReceiveRecord saleReceiveRecord = new SaleReceiveRecord();
//        saleReceiveRecord.setBeforeAmount(oldCustomer.getBalance());
//        saleReceiveRecord.setAfterAmount(balance);
//        saleReceiveRecord.setAmount(balance.subtract(oldCustomer.getBalance()));
//        saleReceiveRecord.setCustomerId(oldCustomer.getId());
//        //操作类型 (0:变更 1:录入收款 2:预付款 -1:费用分摊)
//        saleReceiveRecord.setType(type);
//        saleReceiveRecord.setRemark(remark);
//        saleReceiveRecord.setUuser(uuser);
//        saleReceiveRecord.setCuser(uuser);
//        saleReceiveRecordService.save(saleReceiveRecord);
//    }

//    public void updateCustomerBalance(
//            Customer customer,
//            BigDecimal balance,
//            String uuser) throws Exception {
//        PageData pd = new PageData();
//        pd.put("id",customer.getId());
//        pd.put("version",customer.getVersion());
//        pd.put("uuser",uuser);
//        pd.put("balance",balance);
//        customerMapper.updateCustomerBalance(pd);
//    }


    public List<Map> getPreReceiveAmount(PageData pd) throws Exception{
        return  customerMapper.getPreReceiveAmount(pd);
    }


    public List<Map> getNowReceiveAmount(PageData pd) throws Exception{
        return  customerMapper.getNowReceiveAmount(pd);
    }


    public Map<String, Object> getReceiveAmount(PageData pd) throws Exception {
        return  customerMapper.getReceiveAmount(pd);
    }

    /**
     * (客户,供应商)名称在表中是否存在
     *
     * @param id        主键id
     * @param genre     属性id
     * @param companyId 企业id
     * @param name      (客户,供应商)名称
     * @return
     */
    public boolean isExistByName(String id, String genre, String companyId, String name) {
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("companyId", companyId);
        findMap.put("genre", genre);
        findMap.put("name", name);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        Customer object = findCustomer(findMap);
        if (object != null) {return true;}

        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public ResultModel addCustomerBalance(PageData pd) throws Exception {
//        ResultModel model = new ResultModel();
//        BigDecimal addBalance = BigDecimal.valueOf(Double.parseDouble(pd.getString("addBalance")));
//        Customer Customer = customerService.selectById(pd.getString("id"));
//
//        String remark = "录入收款："+ Customer.getBalance().add(addBalance).subtract(Customer.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP);
//        customerService.updateCustomerBalance(
//                Customer,
//                Customer.getBalance().add(addBalance),
//                pd.getString("uuser"),
//                //操作类型 (0:变更 1:录入收款 2:预付款 -1:费用分摊)
//                "1",
//                remark);
//        return model;
//    }
//
//    @Override
//    public ResultModel updateCustomerBalancee(PageData pd) throws Exception {
//        ResultModel model = new ResultModel();
//        Customer newCustomer = (Customer) HttpUtils.pageData2Entity(pd, new Customer());
//        Customer oldCustomer = customerService.selectById(newCustomer.getId());
//
//        String remarkTemp = "变更前：{0} 变更后：{1}";
//        String remark = MessageFormat.format(remarkTemp,
//                oldCustomer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP),
//                newCustomer.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
//
//        customerService.updateCustomerBalance(
//                oldCustomer,
//                newCustomer.getBalance(),
//                pd.getString("uuser"),
//                //操作类型 (0:变更 1:录入收款 2:预付款 -1:费用分摊)
//                "0",
//                remark
//        );
//        return model;
//    }

    @Override
    public ResultModel listPageCustomers(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("customer");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();
        if(columnList!=null&&columnList.size()>0){
            for (Column column : columnList) {
                if(column!=null){
                    if("0".equals(column.getIshide())){
                        titlesHideList.add(column.getTitleKey());
                    }
                    LinkedHashMap titlesLinkedMap = new LinkedHashMap();
                    titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
                    varModelMap.put(column.getTitleKey(),"");
                    titlesList.add(titlesLinkedMap);
                }
            }
        }

        Map result = new HashMap();
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);


        pd.put("orderStr", "cust.cdate desc");

        String genreId = pd.getString("pid");
        if (genreId != null && genreId.trim().length() > 0
                && !Common.DICTIONARY_MAP.get("customerSupplierGenre").equals(genreId)
                ) {
            pd.put("genre", genreId);
        }

        List<Map> varMapList = new ArrayList();

        List<Map> varList = customerService.getDataListPage(pd, pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel listPageCustomerReceive(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("customerReceive");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        //获取指定栏位字符串-重新调整List<Column>

        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }


        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();
        if(columnList!=null&&columnList.size()>0){
            for (Column column : columnList) {
                if(column!=null){
                    if("0".equals(column.getIshide())){
                        titlesHideList.add(column.getTitleKey());
                    }
                    LinkedHashMap titlesLinkedMap = new LinkedHashMap();
                    titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
                    varModelMap.put(column.getTitleKey(),"");
                    titlesList.add(titlesLinkedMap);
                }
            }
        }

        Map result = new HashMap();
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);


        List<Map> varMapList = new ArrayList();

        List<Map> varList = customerService.getReceiveDataListPage(pd, pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);

        Map<String,Object> receiveMap = this.getReceiveAmount(pd);
        if(receiveMap!=null){
            result.put("preReceiveAmount", receiveMap.get("preReceiveAmount"));
            result.put("nowReceiveAmount", receiveMap.get("nowReceiveAmount"));
        }

        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel listPageCustomerAccountDays(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("customerAccountDays");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();
        if(columnList!=null&&columnList.size()>0){
            for (Column column : columnList) {
                if(column!=null){
                    if("0".equals(column.getIshide())){
                        titlesHideList.add(column.getTitleKey());
                    }
                    LinkedHashMap titlesLinkedMap = new LinkedHashMap();
                    titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
                    varModelMap.put(column.getTitleKey(),"");
                    titlesList.add(titlesLinkedMap);
                }
            }
        }

        Map result = new HashMap();
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);


        pd.put("orderStr", "cust.cdate desc");

        String genreId = pd.getString("pid");
        if (genreId != null && genreId.trim().length() > 0
                && !Common.DICTIONARY_MAP.get("customerSupplierGenre").equals(genreId)
                ) {
            pd.put("genre", genreId);
        }

        List<Map> varMapList = new ArrayList();

        List<Map> varList = customerService.getDataListPage(pd, pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);

        model.putResult(result);
        return model;
    }

//    @Override
//    public ResultModel addCustomer(PageData pageData) throws Exception {
//        ResultModel model = new ResultModel();
//        //1. 非空判断
//        String name = pageData.getString("name");
//        if (name == null || name.trim().length() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("名称输入为空或空字符串，名称为必填不可为空！");
//            return model;
//        }
//
//        //获取客户供应商编码
//        String companyId = pageData.getString("currentCompanyId");
//        String code = coderuleService.createCoder(companyId,"vmes_customer","C");
//        if(StringUtils.isEmpty(code)){
//            model.putCode(1);
//            model.putMsg("客户供应商编码规则创建异常，请重新操作！");
//            return model;
//        }
//
//        Customer object = (Customer)HttpUtils.pageData2Entity(pageData, new Customer());
//        object.setId(Conv.createUuid());
//        object.setCuser(pageData.getString("cuser"));
//        object.setCompanyId(companyId);
//        object.setCode(code);
//        //生成客户供应商二维码
//        String qrcode = fileService.createQRCode("customer", object.getId());
//        if (qrcode != null && qrcode.trim().length() > 0) {
//            object.setQrcode(qrcode);
//        }
//        customerService.save(object);
//        return model;
//    }

    @Override
    public ResultModel updateCustomer(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String name = pageData.getString("name");
        if (name == null || name.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("名称输入为空或空字符串，名称为必填不可为空！");
            return model;
        }

        String id = pageData.getString("id");
        if (id == null || id.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("客户供应商主键id为空或空字符串！");
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
        if (customerService.isExistByName(id, genre, companyId, name)) {
            String msgTemp = "{0}名称({1})在系统中已经存在！";
            String msgExist = MessageFormat.format(msgTemp,
                    genreName,
                    name);
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgExist);
            return model;
        }

        Customer objectDB = (Customer)HttpUtils.pageData2Entity(pageData, new Customer());
        String remark = pageData.getString("remark");
        if (remark == null) {remark = "";}
        objectDB.setRemark(remark);
        objectDB.setUuser(pageData.getString("cuser"));
        customerService.update(objectDB);
        return model;
    }

    @Override
    public ResultModel updateDisableCustomer(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String id = pageData.getString("id");
        String isdisable = pageData.getString("isdisable");

        //1. 非空判断
        String msgStr = new String();
        if (id == null || id.trim().length() == 0) {
            msgStr = msgStr + "id为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (isdisable == null || isdisable.trim().length() == 0) {
            msgStr = msgStr + "isdisable为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

//        if (!customerService.checkDeleteCustomerByIds(id)) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("当前禁用的数据系统正在使用中，不可禁用操作！");
//            return model;
//        }

        //2. 修改客户供应商(禁用)状态
        Customer objectDB = (Customer)HttpUtils.pageData2Entity(pageData, new Customer());
        customerService.update(objectDB);
        return model;
    }

    @Override
    public ResultModel deleteCustomers(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String ids = pageData.getString("ids");
        if (ids == null || ids.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！");
            return model;
        }

        StringBuffer msgBuf = new StringBuffer();
        String[] id_arry = ids.split(",");
//        for (int i = 0; i < id_arry.length; i++) {
//            String id = id_arry[i];
//            String msgTemp = "第 {0} 行 该数据在系统中已经使用不可删除禁用！";
//
//            //入库单
//            PageData findMap = new PageData();
//            findMap.put("deptId", id);
//            findMap.put("mapSize", Integer.valueOf(findMap.size()));
//            List objectList = warehouseInService.findWarehouseInList(findMap);
//            if (objectList != null && objectList.size() > 0) {
//                String msgStr = MessageFormat.format(msgTemp, (i+1));
//                msgBuf.append(msgStr);
//                continue;
//            }
//
//            //出库单
//            findMap.clear();
//            findMap.put("deptId", id);
//            findMap.put("mapSize", Integer.valueOf(findMap.size()));
//            objectList = warehouseOutService.findWarehouseOutList(findMap);
//            if (objectList != null && objectList.size() > 0) {
//                String msgStr = MessageFormat.format(msgTemp, (i+1));
//                msgBuf.append(msgStr);
//                continue;
//            }
//
//            //订单表
//            findMap.clear();
//            findMap.put("customerId", id);
//            objectList = saleOrderService.findSaleOrderList(findMap);
//            if (objectList != null && objectList.size() > 0) {
//                String msgStr = MessageFormat.format(msgTemp, (i+1));
//                msgBuf.append(msgStr);
//                continue;
//            }
//
//            //采购表
//            findMap.clear();
//            findMap.put("supplierId", id);
//            objectList = purchaseOrderService.findPurchaseOrderList(findMap);
//            if (objectList != null && objectList.size() > 0) {
//                String msgStr = MessageFormat.format(msgTemp, (i+1));
//                msgBuf.append(msgStr);
//                continue;
//            }
//        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString().trim());
            return model;
        }

        //2. 删除客户供应商
        ids = StringUtil.stringTrimSpace(ids);
        customerService.deleteByIds(ids.split(","));

        //3. 删除客户供应商地址
        //String[] id_arry = ids.split(",");
        for (int i = 0; i < id_arry.length; i++) {
            String id = id_arry[i];
            customeAddressService.deleteCustAddrByCustId(id);
        }
        return model;
    }

    @Override
    public void exportExcelCustomers(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("Customer");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

        String ids = (String)pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "cust.id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);


        pg.setSize(100000);
        List<Map> dataList = customerService.getDataListPage(pd, pg);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> mapObject : dataList) {

                //是否启用(0:已禁用 1:启用)
                String isdisable = (String)mapObject.get("isdisable");
                String isdisableName = "否";
                if ("1".equals(isdisable)) {
                    isdisableName = "是";
                }
                mapObject.put("isdisable", isdisableName);
            }
        }

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelCustomer";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);

    }

//    @Override
//    public ResultModel importExcelCustomers(MultipartFile file) throws Exception {
//        ResultModel model = new ResultModel();
//        if (file == null) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("请上传Excel文件！");
//            return model;
//        }
//
//        // 验证文件是否合法
//        // 获取上传的文件名(文件名.后缀)
//        String fileName = file.getOriginalFilename();
//        if (fileName == null
//                || !(fileName.matches("^.+\\.(?i)(xlsx)$")
//                || fileName.matches("^.+\\.(?i)(xls)$"))
//                ) {
//            String failMesg = "不是excel格式文件,请重新选择！";
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(failMesg);
//            return model;
//        }
//
//        // 判断文件的类型，是2003还是2007
//        boolean isExcel2003 = true;
//        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            isExcel2003 = false;
//        }
//
//        List<List<String>> dataLst = ExcelUtil.readExcel(file.getInputStream(), isExcel2003);
//        List<LinkedHashMap<String, String>> dataMapLst = ExcelUtil.reflectMapList(dataLst);
//
//        HttpServletRequest httpRequest = HttpUtils.currentRequest();
//        String companyId = (String)httpRequest.getParameter("companyId");
//        String userId = (String)httpRequest.getParameter("userId");
//
//        if (dataMapLst == null || dataMapLst.size() == 1) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("导入文件数据为空，请至少填写一行导入数据！");
//            return model;
//        }
//        //去掉列表名称行
//        dataMapLst.remove(0);
//
//        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
//        String msgStr = customerExcelService.checkColumnImportExcel(dataMapLst,
//                companyId,
//                userId,
//                Integer.valueOf(3),
//                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
//        if (msgStr != null && msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(this.exportExcelError(msgStr).toString());
//            return model;
//        }
//
//        //2. Excel导入字段-名称唯一性判断-在Excel文件中
//        //3. Excel导入字段-名称唯一性判断-在业务表中判断
//        //4. Excel数据添加到货品表
//        customerExcelService.addImportExcelByList(dataMapLst);
//
//
//        return model;
//    }

    @Override
    public ResultModel listTreeCustomer(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //获取全部字典树-查询条件

        List<TreeEntity> treeList = customerService.getTreeList(pd);
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setId("khlb");
        treeEntity.setPid("root");
        treeEntity.setLabel("客户列表");
        treeEntity.setName("客户列表");
        treeEntity.setValue("khlb");
        treeEntity.setIsdisable("1");
        treeList.add(treeEntity);
        TreeEntity treeObj = TreeUtil.switchTree("root", treeList);

        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);
        return model;
    }

//    private StringBuffer exportExcelError(String msgStr) {
//        StringBuffer msgBuf = new StringBuffer();
//        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
//        msgBuf.append(msgStr.trim());
//        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);
//
//        return msgBuf;
//    }


    @Override
    public void updateCustomerBalance(PageData pd) throws Exception {
        customerMapper.updateCustomerBalance(pd);
    }
}
