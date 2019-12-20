package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.ProductUnit;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：产品计价单位 Mapper.java
* 创建人：刘威 自动创建
* 创建时间：2018-11-15
*/
@Mapper
@Repository
public interface ProductUnitMapper extends BaseMapper<ProductUnit> {


	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-11-15
	*/
    List<ProductUnit> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
	List<ProductUnit> dataList(PageData pd);

	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-11-15
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> findDataList(PageData pd);


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<LinkedHashMap> getColumnList();


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> getDataList(PageData pd);


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    List<Map> getDataListPage(PageData pd,Pagination pg);
    List<Map> getDataListPage(PageData pd);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-11-15
    */
    void updateToDisableByIds(String[] ids);


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    void updateToNotDefaultByPorId(String productId);

    void updateTypeByProductUnit(PageData pd);
}


