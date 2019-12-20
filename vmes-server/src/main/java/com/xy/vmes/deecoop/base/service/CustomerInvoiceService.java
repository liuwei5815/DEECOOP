package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.CustomerInvoice;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_customer_invoice:客户开票信息 接口类
* 创建人：陈刚 自动生成
* 创建时间：2019-01-09
*/
public interface CustomerInvoiceService {

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void save(CustomerInvoice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void update(CustomerInvoice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void updateAll(CustomerInvoice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    CustomerInvoice selectById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    List<CustomerInvoice> selectByColumnMap(Map columnMap) throws Exception;


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    List<CustomerInvoice> dataList(PageData pd) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-08
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     *
     *   true: 无查询条件返回表全部结果集
     *   false: (false or is null)无查询条件-查询结果集返回空或
     *
     * @return
     * @throws Exception
     */
    List<CustomerInvoice> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;



    CustomerInvoice findCustomerInvoice(PageData object) throws Exception;
    CustomerInvoice findCustomerInvoiceById(String id) throws Exception;

    List<CustomerInvoice> findCustomerInvoiceList(PageData object) throws Exception;
    List<CustomerInvoice> findCustomerInvoiceListByCustomerId(String customerId) throws Exception;

    void updateDefaultByCustomerId(PageData pageData);

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ResultModel listPageCustomerInvoices(PageData pd, Pagination pg)throws Exception;
    ResultModel addCustomerInvoice(PageData pageData) throws Exception;
    ResultModel updateCustomerInvoice(PageData pageData)throws Exception;
    ResultModel updateDefaultCustomerInvoice(PageData pageData)throws Exception;
    ResultModel deleteCustomerInvoice(PageData pageData)throws Exception;

    void exportExcelCustomerInvoices(PageData pd, Pagination pg) throws Exception;
    ResultModel importExcelCustomerInvoices(MultipartFile file)throws Exception;
}



