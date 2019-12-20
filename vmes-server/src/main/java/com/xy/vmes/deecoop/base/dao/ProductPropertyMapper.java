package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.ProductProperty;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_product_property:产品属性 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2018-09-21
*/
@Mapper
@Repository
public interface ProductPropertyMapper extends BaseMapper<ProductProperty> {

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
	List<ProductProperty> dataList(PageData pd);

	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2018-09-21
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    List<Map> getDataListPage(PageData pd,Pagination pg);


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
}


