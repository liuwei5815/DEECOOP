package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.UserDefinedMenu;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：用户自定义菜单 Mapper.java
* 创建人：刘威 自动创建
* 创建时间：2018-07-27
*/
@Mapper
@Repository
public interface UserDefinedMenuMapper extends BaseMapper<UserDefinedMenu> {


	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-27
	*/
    List<UserDefinedMenu> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
	List<UserDefinedMenu> dataList(PageData pd);

	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-27
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<Map> findDataList(PageData pd);



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


	/**
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	List<LinkedHashMap> getColumnList();


	/**
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	List<Map> getDataList(PageData pd);
}


