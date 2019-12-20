package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.base.dao.CustomeAddressMapper;
import com.xy.vmes.entity.Column;
import com.xy.vmes.entity.CustomeAddress;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.CustomeAddressService;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

import com.yvan.Conv;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_customer_address 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-09-20
*/
@Service
@Transactional(readOnly = false)
public class CustomeAddressServiceImp implements CustomeAddressService {


    @Autowired
    private CustomeAddressMapper customeAddressMapper;
    @Autowired
    private CustomeAddressService customeAddressService;
    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void save(CustomeAddress customeAddress) throws Exception{
        customeAddress.setId(Conv.createUuid());
        customeAddress.setCdate(new Date());
        customeAddress.setUdate(new Date());
        customeAddressMapper.insert(customeAddress);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void update(CustomeAddress customeAddress) throws Exception{
        customeAddress.setUdate(new Date());
        customeAddressMapper.updateById(customeAddress);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void updateAll(CustomeAddress customeAddress) throws Exception{
        customeAddress.setUdate(new Date());
        customeAddressMapper.updateAllColumnById(customeAddress);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    //@Cacheable(cacheNames = "customeAddress", key = "''+#id")
    public CustomeAddress selectById(String id) throws Exception{
        return customeAddressMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteById(String id) throws Exception{
        customeAddressMapper.deleteById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        customeAddressMapper.deleteByIds(ids);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<CustomeAddress> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customeAddressMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<CustomeAddress> dataList(PageData pd) throws Exception{
        return customeAddressMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return customeAddressMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return customeAddressMapper.findDataList(pd);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        customeAddressMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<CustomeAddress> selectByColumnMap(Map columnMap) throws Exception {
    List<CustomeAddress> customeAddressList =  customeAddressMapper.selectByMap(columnMap);
        return customeAddressList;
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return customeAddressMapper.getDataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        customeAddressMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚
     * 创建时间：2018-09-20
     * @param object
     * @return
     */
    public String checkColumnByAddEdit(CustomeAddress object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "联系姓名");
            msgBuf.append(str_isnull);
        }
        if (object.getMobile() == null || object.getMobile().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "手机号");
            msgBuf.append(str_isnull);
        }
        if (object.getAddress() == null || object.getAddress().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "详细地址");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    public CustomeAddress findCustomeAddress(PageData object) {
        if (object == null) {return null;}

        List<CustomeAddress> objectList = null;
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

    public CustomeAddress findCustomeAddressById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<CustomeAddress> objectList = this.findCustomeAddressList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }


    public List<CustomeAddress> findCustomeAddressList(PageData object) {
        if (object == null) {return null;}

        List<CustomeAddress> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<CustomeAddress> findCustomeAddressListByCustId(String custId) {
        List<CustomeAddress> objectList = new ArrayList<CustomeAddress>();
        if (custId == null || custId.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("customerId", custId);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        objectList = this.findCustomeAddressList(findMap);

        return objectList;
    }


    public void updateDefaultByCustId(PageData pageData) {
        customeAddressMapper.updateDefaultByCustId(pageData);
    }


    public void deleteCustAddrByCustId(String custId) throws Exception {
        if (custId == null || custId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("customer_id", custId);

        this.deleteByColumnMap(mapObject);
    }

    @Override
    public ResultModel listPageCustomeAddress(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("customeAddress");
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
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Map> varList = customeAddressService.getDataListPage(pd,pg);
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
    public ResultModel addCustomerAddress(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String customerId = pageData.getString("customerId");
        if (customerId == null || customerId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("客户id为空或空字符串！");
            return model;
        }

        CustomeAddress custAddr = (CustomeAddress)HttpUtils.pageData2Entity(pageData, new CustomeAddress());
        //是否默认(0:非默认 1:默认)
        custAddr.setIsdefault("0");

        String msgStr = this.checkColumnByAddEdit(custAddr);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //2. 根据客户id查询(vmes_customer_address)
        //是否默认(0:非默认 1:默认)
        PageData findMap = new PageData();
        findMap.put("customerId", customerId);
        findMap.put("isdefault", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<CustomeAddress> objectList = this.findCustomeAddressList(findMap);
        if (objectList == null || objectList.size() == 0) {
            custAddr.setIsdefault("1");
        }

        customeAddressService.save(custAddr);
        return model;
    }

    @Override
    public ResultModel updateCustomerAddress(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        CustomeAddress custAddrDB = (CustomeAddress)HttpUtils.pageData2Entity(pageData, new CustomeAddress());
        String msgStr = this.checkColumnByAddEdit(custAddrDB);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        customeAddressService.update(custAddrDB);
        return model;
    }

    @Override
    public ResultModel updateDefaultCustomerAddress(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String customerId = pageData.getString("customerId");
        if (customerId == null || customerId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("客户id为空或空字符串！");
            return model;
        }
        String addressId = pageData.getString("addressId");
        if (addressId == null || addressId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("地址id为空或空字符串！");
            return model;
        }

        List<CustomeAddress> addressList = this.findCustomeAddressListByCustId(customerId);
        if (addressList != null && addressList.size() == 1) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("当前客户地址只有一条，不可修改默认属性！");
            return model;
        }


        String isdefault = pageData.getString("isdefault");
        if ("1".equals(isdefault)) {
            //2. 客户id-修改客户地址表字段(vmes_customer_address.isdefault) 全部为(0:非默认)
            PageData mapObject = new PageData();
            mapObject.put("customerId", customerId);
            //是否默认(0:非默认 1:默认)
            mapObject.put("isdefault", "0");
            this.updateDefaultByCustId(mapObject);
        }

        CustomeAddress custAddrDB = this.findCustomeAddressById(addressId);
        if (custAddrDB != null) {
            //是否默认(0:非默认 1:默认)
            custAddrDB.setIsdefault(isdefault);
            this.update(custAddrDB);
        }
        return model;
    }

    @Override
    public ResultModel deleteCustomerAddress(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String ids = pageData.getString("ids");
        if (ids == null || ids.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！");
            return model;
        }

        ids = StringUtil.stringTrimSpace(ids);
        customeAddressService.deleteByIds(ids.split(","));
        return model;
    }

    @Override
    public void exportExcelCustomeAddresss(PageData pd, Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("customeAddress");
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
        List<Map> dataList = customeAddressService.getDataListPage(pd, pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelCustomeAddress";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);

    }

    @Override
    public ResultModel importExcelCustomeAddresss(MultipartFile file) throws Exception {
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
}



