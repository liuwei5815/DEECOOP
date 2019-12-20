package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.UserRole;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_user_role:用户角色表 Mapper.java
* 创建人：刘威 自动创建
* 创建时间：2018-07-26
*/
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {


	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-26
	*/
    List<UserRole> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
	List<UserRole> dataList(PageData pd);

	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-26
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<Map> findDataList(PageData pd);

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
	/**
	 * 创建人：陈刚
	 * 创建时间：2018-07-31
	 */
	List<Map<String, Object>> findUserRoleMapList(PageData pd);

	/**
	 * 创建人：陈刚
	 * 创建时间：2018-07-31
	 */
	void updateDisableByRoleId(String roleId);

	/**
	 * 创建人：陈刚
	 * 创建时间：2018-08-22
	 */
	List<LinkedHashMap<String, String>> listUserColumn();
	/**
	 * 创建人：陈刚
	 * 创建时间：2018-08-22
	 */
	List<Map> listUserByRole(PageData pd);


}


