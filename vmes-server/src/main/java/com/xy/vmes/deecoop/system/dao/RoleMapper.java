package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Role;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_role:角色 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2018-07-30
*/
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {


	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2018-07-30
	*/
    List<Role> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
	List<Role> dataList(PageData pd);

	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2018-07-30
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<Map> findDataList(PageData pd);



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
	/**
	 * 创建人：陈刚
	 * 创建时间：2018-07-30
	 */
	List<LinkedHashMap> getColumnList();
	/**
	 * 创建人：陈刚
	 * 创建时间：2018-07-30
	 */
	List<Map> getDataListPage(PageData pd, Pagination pg);

	/**
	 * 批量修改角色信息为禁用状态
	 *
	 * 创建人：陈刚
	 * 创建时间：2018-07-30
	 */
    void updateDisableByIds(String[] ids);
}


