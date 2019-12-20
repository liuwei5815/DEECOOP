package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Product;
import com.xy.vmes.entity.ProductUnit;
import com.xy.vmes.entity.ProductUnitPrice;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_product:产品表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-09-21
*/
public interface ProductService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void save(Product product) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void update(Product product) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void updateAll(Product product) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    Product selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Product> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Product> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Product> selectByColumnMap(Map columnMap) throws Exception;


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
//    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void updateToDisableByIds(String[] ids)throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     *
     * @param companyId  企业id
     * @param isSpare    是否备件库(true: 删除的是备件库 false: 删除的是非备件库)
     * @throws Exception
     */
    void initialProductByStockCount(String companyId, String isSpare) throws Exception;

    Product findProduct(PageData object);
    Product findProductById(String id);
    List<Product> findProductList(PageData object);
//    String findIdsByProductList(List<Product> objectList);
//    String findIdsByPageMapList(List<Map> mapList);
    List<Map> getDataListPage(PageData pd) throws Exception;

//    Map<String, String> productObj2QRCodeObj(Product productObj, Map<String, String> QRCodeObj);
    ProductUnit product2ProductUnit(Product product, String unit);
    ProductUnitPrice product2ProductUnitPrice(Product product,String unit);
    Map<String, String> queryMap2ProductMap(Map queryMap);

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 修改货品库存数量
     * 修改货品(入库,出库)操作时间
     *
     * @param product     货品表对象
     * @param count       库存数量
     * @param userId      操作人用户id
     * @param operateType 仓库操作类型(in:入库 out:出库 update:修改库存)
     * @throws Exception
     */
    void updateStockCount(Product product,
                          BigDecimal count,
                          String userId,
                          String operateType) throws Exception;

    /**
     * 修改锁定库存数量
     * @param productId   货品id
     * @param oldProduct  原货品对象
     * @param lockCount   修改的货品锁定数量
     * @param uuser       用户id
     * @throws Exception
     */
    void updateLockCount(String productId, Product oldProduct, BigDecimal lockCount, String uuser) throws Exception;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ResultModel listPageProducts(PageData pd) throws Exception;

    ResultModel listPageProductPropertys(PageData pd, Pagination pg)  throws Exception;

//    ResultModel addProduct(PageData pageData) throws Exception;

    ResultModel updateProduct(PageData pageData) throws Exception;

    ResultModel updateDisableProduct(PageData pageData) throws Exception;

    ResultModel deleteProduct(PageData pageData) throws Exception;

    void exportExcelProducts(PageData pd, Pagination pg) throws Exception;

//    ResultModel importExcelProduct(MultipartFile file) throws Exception;
}



