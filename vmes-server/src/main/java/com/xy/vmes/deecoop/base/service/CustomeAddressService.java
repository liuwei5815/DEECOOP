package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.CustomeAddress;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_customer_address 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-09-20
*/
public interface CustomeAddressService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void save(CustomeAddress customeAddress) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void update(CustomeAddress customeAddress) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void updateAll(CustomeAddress customeAddress) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    CustomeAddress selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<CustomeAddress> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<CustomeAddress> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<CustomeAddress> selectByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void updateToDisableByIds(String[] ids)throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

//    String checkColumnByAddEdit(CustomeAddress object);

//    CustomeAddress findCustomeAddress(PageData object);
//    CustomeAddress findCustomeAddressById(String id);

//    List<CustomeAddress> findCustomeAddressList(PageData object);
//    List<CustomeAddress> findCustomeAddressListByCustId(String custId);

//    void updateDefaultByCustId(PageData pageData);
    void deleteCustAddrByCustId(String custId) throws Exception;//@

    ResultModel listPageCustomeAddress(PageData pd)throws Exception;

    ResultModel addCustomerAddress(PageData pageData) throws Exception;

    ResultModel updateCustomerAddress(PageData pageData)throws Exception;

    ResultModel updateDefaultCustomerAddress(PageData pageData)throws Exception ;

    ResultModel deleteCustomerAddress(PageData pageData)throws Exception ;

    void exportExcelCustomeAddresss(PageData pd, Pagination pg)throws Exception;

    ResultModel importExcelCustomeAddresss(MultipartFile file)throws Exception;
}



