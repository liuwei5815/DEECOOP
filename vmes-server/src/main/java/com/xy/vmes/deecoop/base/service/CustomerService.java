package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Customer;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_customer:客户供应商表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-09-18
*/
public interface CustomerService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void save(Customer customer) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void update(Customer customer) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void updateAll(Customer customer) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    Customer selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<Customer> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<Customer> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<Customer> selectByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-18
    */
    void updateToDisableByIds(String[] ids) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    Customer findCustomer(PageData object);
    Customer findCustomerById(String id);

    List<Customer> findCustomerList(PageData object);

    /**
     * check客户列表List<Customer>是否允许删除
     * true : 可以删除
     * false: 不可删除
     *
     * 创建人：陈刚
     * 创建时间：2018-09-18
     * @param ids
     * @return
     */
//    boolean checkDeleteCustomerByIds(String ids);

    /**
     * 获取(删除,修改)客户表主键id(vmes_customer)-通过','分隔的字符串
     *     <"DeleteIds", 客户表主键id>
     *     <"UpdateIds", 客户表主键id>
     * @return
     */
//    Map<String, String> findDeleteCustIdsByIds(String ids);

    List<TreeEntity> getTreeList(PageData pd) throws Exception;//@

    List<Map> getReceiveDataListPage(PageData pd,Pagination pg) throws Exception;//@

//    void updateCustomerBalance(
//            Customer customer,
//            BigDecimal balance,
//            String uuser,
//            String type,
//            String remark) throws Exception;

//    void updateCustomerBalance(
//            Customer customer,
//            BigDecimal balance,
//            String uuser) throws Exception;

//    List<Map> getPreReceiveAmount(PageData pd) throws Exception;

//    List<Map> getNowReceiveAmount(PageData pd) throws Exception;

//    Map<String,Object> getReceiveAmount(PageData pd) throws Exception;

    /**
     * (客户,供应商)名称在表中是否存在
     *
     * @param id        主键id
     * @param genre     属性id
     * @param companyId 企业id
     * @param name      (客户,供应商)名称
     * @return
     */
    boolean isExistByName(String id, String genre, String companyId, String name);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    ResultModel addCustomerBalance(PageData pd)throws Exception;
//
//    ResultModel updateCustomerBalancee(PageData pd) throws Exception;

    ResultModel listPageCustomers(PageData pd, Pagination pg)throws Exception;

    ResultModel listPageCustomerReceive(PageData pd, Pagination pg)throws Exception;

    ResultModel listPageCustomerAccountDays(PageData pd, Pagination pg)throws Exception;

//    ResultModel addCustomer(PageData pageData)throws Exception;

    ResultModel updateCustomer(PageData pageData)throws Exception;

    ResultModel updateDisableCustomer(PageData pageData)throws Exception;

    ResultModel deleteCustomers(PageData pageData)throws Exception;

    void exportExcelCustomers(PageData pd, Pagination pg)throws Exception;

//    ResultModel importExcelCustomers(MultipartFile file)throws Exception;

    ResultModel listTreeCustomer(PageData pd)throws Exception;


    void updateCustomerBalance(PageData pd)throws Exception;
}



