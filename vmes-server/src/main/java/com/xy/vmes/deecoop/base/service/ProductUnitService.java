package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.ProductUnit;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：产品计价单位 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-11-15
*/
public interface ProductUnitService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void save(ProductUnit productUnit) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void update(ProductUnit productUnit) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void updateAll(ProductUnit productUnit) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    ProductUnit selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<ProductUnit> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<ProductUnit> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<ProductUnit> selectByColumnMap(Map columnMap) throws Exception;


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;
    List<Map> getDataListPage(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void updateToDisableByIds(String[] ids)throws Exception;

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
    List<ProductUnit> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;

    ProductUnit findProductUnit(PageData object) throws Exception;
    ProductUnit findProductUnitById(String id) throws Exception;

    List<ProductUnit> findProductUnitList(PageData object) throws Exception;

    void updateToNotDefaultByPorId(String productId) throws Exception;
    /**
     * 更改货品单位表(vmes_product_unit)
     * @param productId  货品id
     * @param type       单位类型(1:计量单位 0:计价单位)
     */
    void updateTypeByProductUnit(String productId, String type);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ResultModel updateIsDefaultProductUnit(PageData pd) throws Exception;

    ResultModel listPageProductUnits(PageData pd, Pagination pg) throws Exception;

    ResultModel updateProductUnitPrice(PageData pd) throws Exception;

    ResultModel findListProductUnitByProduct(PageData pd) throws Exception;

    ResultModel findListProductUnit(PageData pd) throws Exception;

    ResultModel formulaReckonByProductCount(PageData pd) throws Exception;

    void exportExcelProductUnits(PageData pd,Pagination pg) throws Exception;

    ResultModel importExcelProductUnitByProductPrice(MultipartFile file)  throws Exception;
}



