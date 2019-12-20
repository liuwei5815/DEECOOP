package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.CustomeAddress;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_customer_address Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2018-09-20
*/
@Mapper
@Repository
public interface CustomeAddressMapper extends BaseMapper<CustomeAddress> {


	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2018-09-20
	*/
    List<CustomeAddress> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
	List<CustomeAddress> dataList(PageData pd);

	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2018-09-20
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> findDataList(PageData pd);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> getDataListPage(PageData pd,Pagination pg);


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void updateToDisableByIds(String[] ids);

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-20
     */
    void updateDefaultByCustId(PageData pageData);

}


