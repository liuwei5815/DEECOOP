package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.ProductUnitPrice;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_product_unit_price:货品价格 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-12-04
*/
public interface ProductUnitPriceService {

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void save(ProductUnitPrice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void update(ProductUnitPrice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void updateAll(ProductUnitPrice object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    ProductUnitPrice selectById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-11-15
     */
    List<ProductUnitPrice> selectByColumnMap(Map columnMap) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    List<ProductUnitPrice> dataList(PageData pd) throws Exception;
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

//    /**
//     *
//     * @param pageData    查询参数对象<HashMap>
//     * @param isQueryAll  是否查询全部
//     *   true: 无查询条件返回表全部结果集
//     *   false: (false or is null)无查询条件-查询结果集返回空或
//     *
//     * @return
//     * @throws Exception
//     */
//    List<ProductUnitPrice> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;

    ResultModel listPageProductUnitPrices(PageData pd, Pagination pg)throws Exception;
}



