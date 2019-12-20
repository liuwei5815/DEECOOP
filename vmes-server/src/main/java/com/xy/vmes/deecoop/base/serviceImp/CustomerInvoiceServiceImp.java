package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.base.dao.CustomerInvoiceMapper;
import com.xy.vmes.entity.Column;
import com.xy.vmes.entity.CustomerInvoice;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.CustomerInvoiceService;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_customer_invoice:客户开票信息 实现类
* 创建人：陈刚 自动创建
* 创建时间：2019-01-09
*/
@Service
@Transactional(readOnly = false)
public class CustomerInvoiceServiceImp implements CustomerInvoiceService {


    @Autowired
    private CustomerInvoiceMapper customerInvoiceMapper;
    @Autowired
    private CustomerInvoiceService customerInvoiceService;

    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2019-01-09
    */
    @Override
    public void save(CustomerInvoice customerInvoice) throws Exception{
        customerInvoice.setId(Conv.createUuid());
        customerInvoice.setCdate(new Date());
        customerInvoice.setUdate(new Date());
        customerInvoiceMapper.insert(customerInvoice);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public CustomerInvoice selectById(String id) throws Exception{
        return customerInvoiceMapper.selectById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public List<CustomerInvoice> selectByColumnMap(Map columnMap) throws Exception{
        List<CustomerInvoice> warehouseCheckDetailList =  customerInvoiceMapper.selectByMap(columnMap);
        return warehouseCheckDetailList;
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void update(CustomerInvoice object) throws Exception{
        object.setUdate(new Date());
        customerInvoiceMapper.updateById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void updateAll(CustomerInvoice object) throws Exception{
        object.setUdate(new Date());
        customerInvoiceMapper.updateAllColumnById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void deleteById(String id) throws Exception{
        customerInvoiceMapper.deleteById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        customerInvoiceMapper.deleteByIds(ids);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        customerInvoiceMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        customerInvoiceMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    *
    * @param pageData    查询参数对象<HashMap>
    * @param isQueryAll  是否查询全部
    *   true: 无查询条件返回表全部结果集
    *   false: (false or is null)无查询条件-查询结果集返回空或
    *
    * @return
    * @throws Exception
    */
    public List<CustomerInvoice> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<CustomerInvoice>();
        }

        return this.dataList(pageData);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public List<CustomerInvoice> dataList(PageData pd) throws Exception{
        return customerInvoiceMapper.dataList(pd);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customerInvoiceMapper.getDataListPage(pd,pg);
    }

    public CustomerInvoice findCustomerInvoice(PageData object) throws Exception{
        List<CustomerInvoice> objectList = this.findCustomerInvoiceList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    public CustomerInvoice findCustomerInvoiceById(String id) throws Exception{
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);

        return this.findCustomerInvoice(findMap);
    }

    public List<CustomerInvoice> findCustomerInvoiceList(PageData object) throws Exception{
        return this.findDataList(object, null);
    }

    public List<CustomerInvoice> findCustomerInvoiceListByCustomerId(String customerId) throws Exception {
        List<CustomerInvoice> objectList = new ArrayList<CustomerInvoice>();
        if (customerId == null || customerId.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("customerId", customerId);
        objectList = this.findCustomerInvoiceList(findMap);

        return objectList;
    }

    public void updateDefaultByCustomerId(PageData pageData) {
        customerInvoiceMapper.updateDefaultByCustomerId(pageData);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void exportExcelCustomerInvoices(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("customerInvoice");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }
        //根据查询条件获取业务数据List

        String ids = (String)pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);
        pg.setSize(100000);
        List<Map> dataList = customerInvoiceService.getDataListPage(pd, pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelCustomerInvoice";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel importExcelCustomerInvoices(MultipartFile file) throws Exception {
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

        //1. Excel文件数据dataMapLst -->(转换) ExcelEntity (属性为导入模板字段)
        //2. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        //3. Excel导入字段-名称唯一性判断-在Excel文件中
        //4. Excel导入字段-名称唯一性判断-在业务表中判断
        //5. List<ExcelEntity> --> (转换) List<业务表DB>对象
        //6. 遍历List<业务表DB> 对业务表添加或修改

        return model;
    }

    @Override
    public ResultModel listPageCustomerInvoices(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("customerInvoice");
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
        List<Map> varList = customerInvoiceService.getDataListPage(pd,pg);
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

    public ResultModel addCustomerInvoice(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        //1. 非空判断
        String customerId = pageData.getString("customerId");
        if (customerId == null || customerId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("客户id为空或空字符串！");
            return model;
        }

        CustomerInvoice custInvoice = (CustomerInvoice)HttpUtils.pageData2Entity(pageData, new CustomerInvoice());
        //是否默认(0:非默认 1:默认)
        custInvoice.setIsdefault("0");

        //2. 根据客户id查询(vmes_customer_address)
        //是否默认(0:非默认 1:默认)
        PageData findMap = new PageData();
        findMap.put("customerId", customerId);
        findMap.put("isdefault", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<CustomerInvoice> objectList = this.findCustomerInvoiceList(findMap);
        if (objectList == null || objectList.size() == 0) {
            custInvoice.setIsdefault("1");
        }

        customerInvoiceService.save(custInvoice);
        return model;
    }

    public ResultModel updateCustomerInvoice(PageData pageData)throws Exception {
        ResultModel model = new ResultModel();
        CustomerInvoice custInvoiceDB = (CustomerInvoice)HttpUtils.pageData2Entity(pageData, new CustomerInvoice());
        customerInvoiceService.update(custInvoiceDB);
        return model;
    }

    public ResultModel updateDefaultCustomerInvoice(PageData pageData)throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String customerId = pageData.getString("customerId");
        if (customerId == null || customerId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("客户id为空或空字符串！");
            return model;
        }
        String invoiceId = pageData.getString("invoiceId");
        if (invoiceId == null || invoiceId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("开票信息id为空或空字符串！");
            return model;
        }

        List<CustomerInvoice> invoiceList = this.findCustomerInvoiceListByCustomerId(customerId);
        if (invoiceList != null && invoiceList.size() == 1) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("当前客户地址只有一条，不可修改默认属性！");
            return model;
        }

        String isdefault = pageData.getString("isdefault");
        if ("1".equals(isdefault)) {
            PageData mapObject = new PageData();
            mapObject.put("customerId", customerId);
            //是否默认(0:非默认 1:默认)
            mapObject.put("isdefault", "0");
            this.updateDefaultByCustomerId(mapObject);
        }

        CustomerInvoice invoiceDB = new CustomerInvoice();
        invoiceDB.setId(invoiceId);
        //是否默认(0:非默认 1:默认)
        invoiceDB.setIsdefault(isdefault);
        customerInvoiceService.update(invoiceDB);

//        //有且只有一个开票信息
//        PageData findMap = new PageData();
//        findMap.put("customerId", customerId);
//        findMap.put("isdefault", "1");
//        findMap.put("mapSize", Integer.valueOf(findMap.size()));
//        List<CustomerInvoice> objectList = customerInvoiceService.findCustomerInvoiceList(findMap);
//        if (objectList != null && objectList.size() == 0) {
//            invoiceDB.setId(invoiceId);
//            //是否默认(0:非默认 1:默认)
//            invoiceDB.setIsdefault("1");
//            customerInvoiceService.update(invoiceDB);
//        }

        return model;
    }

    public ResultModel deleteCustomerInvoice(PageData pageData)throws Exception {
        ResultModel model = new ResultModel();

        //1. 非空判断
        String ids = pageData.getString("ids");
        if (ids == null || ids.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！");
            return model;
        }

        ids = StringUtil.stringTrimSpace(ids);
        String sql_ids = "'" + ids.replace(",", "','") + "'";

        PageData findMap = new PageData();
        findMap.put("ids", sql_ids);
        List<CustomerInvoice> objectList = this.findCustomerInvoiceList(findMap);
        if (objectList != null && objectList.size() > 0) {
            for(int i = 0; i < objectList.size(); i++) {
                CustomerInvoice object = objectList.get(i);
                //是否默认(0:非默认 1:默认)
                if (object.getIsdefault() != null && "1".equals(object.getIsdefault().trim())) {
                    model.putCode(Integer.valueOf(1));
                    model.putMsg("第 " + (i+1) + " 行：客户开票信息是默认值不可删除！");
                    return model;
                }
            }
        }

        customerInvoiceService.deleteByIds(ids.split(","));

        return model;
    }


}



