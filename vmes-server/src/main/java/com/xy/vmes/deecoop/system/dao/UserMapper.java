package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.User;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_user:系统用户表 Mapper.java
* 创建人：刘威 自动创建
* 创建时间：2018-07-26
*/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {


	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-26
	*/
    List<User> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
	List<User> dataList(PageData pd);

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
	 * 判断用户手机号是否存在
	 *
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	List<User> isExistMobile(PageData pd);


	/**
	 * 批量修改用户信息为禁用状态
	 *
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	void updateToDisableByIds(String[] ids);

	/**
	 * 创建人：刘威
	 * 创建时间：2018-08-03
	 */
	void updateToDisableByEmployIds(String[] ids);

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

	/**
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	List<Map> getDataListPage(PageData pd);
	List<Map> getDataListPage(PageData pd,Pagination pg);

	/**
	 * 批量修改(企业管理员)为禁用状态
	 *
	 * 创建人：陈刚
	 * 创建时间：2018-08-06
	 */
	void updateDisableByCompanyIds(String[] companyIds);


	/**
	 * 创建人：刘威
	 * 创建时间：2018-07-26
	 */
	List<Map> selectCountUserNum(PageData pd);

	List<Map> findUserCountByCompanyId(PageData pd);

}


