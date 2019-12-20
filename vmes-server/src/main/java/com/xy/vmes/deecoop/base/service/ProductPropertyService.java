package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.ProductProperty;
import com.yvan.PageData;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_product_property:产品属性 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-09-21
*/
public interface ProductPropertyService {

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void save(ProductProperty productProperty) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void update(ProductProperty productProperty) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void updateAll(ProductProperty productProperty) throws Exception;

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
    ProductProperty selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<ProductProperty> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<ProductProperty> selectByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    ProductProperty findProductProperty(PageData object);
//    ProductProperty findProductPropertyById(String id);

    List<ProductProperty> findProductPropertyList(PageData object);//@
    List<ProductProperty> findProductPropertyListByProdId(String prodId);

    void deleteProdPropertyByProdId(String prodId) throws Exception;//@

//    ProductProperty map2ProdProperty(Map<String, Object> mapObj, ProductProperty object);
//    List<ProductProperty> mapList2ProductPropertyList(List<Map<String, Object>> mapList);

    //货品(添加,修改)时调用
    void addProductProperty(String cuser, String prodId, List<ProductProperty> objectList);
    //获取自定义属性字符串-'_'分隔的字符串
    String findPropertyValue(List<ProductProperty> objectList);

//    Map<String, String> prodProperty2Map(ProductProperty object, Map<String, String> mapObject);

//    String prodPropertyList2JsonString(List<ProductProperty> objectList);

    /////////////////////////////////////////////////////////////////////////////////////
    Map<String, String> findProdPropertyJsonByProductPropertyList(List<ProductProperty> objectList);//@
//    Map<String, String> findProdPropertyNameByProductPropertyList(List<ProductProperty> objectList);

//    Map<String, List<ProductProperty>> findProdPropertyMap(List<ProductProperty> objectList);
//    Map<String, String> findProdPropertyJson(Map<String, List<ProductProperty>> mapObject);

}



