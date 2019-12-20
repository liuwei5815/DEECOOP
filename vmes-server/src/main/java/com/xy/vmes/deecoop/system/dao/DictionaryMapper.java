package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Dictionary;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：数据字典 Mapper.java
* 创建人：刘威 自动创建
* 创建时间：2018-07-31
*/
@Mapper
@Repository
public interface DictionaryMapper extends BaseMapper<Dictionary> {


	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-31
	*/
    List<Dictionary> dataListPage(PageData pd,Pagination pg);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
	List<Dictionary> dataList(PageData pd);

	/**
	* 创建人：刘威 自动创建，禁止修改
	* 创建时间：2018-07-31
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<LinkedHashMap> findColumnList();


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<Map> findDataList(PageData pd);


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    List<LinkedHashMap> getColumnList();


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    List<Map> getDataList(PageData pd);

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    List<Map> getDataListPage(PageData pd,Pagination pg);

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    void updateToDisableByIds(String[] ids);



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/



    /**
     * 创建人：刘威
     * 创建时间：2018-08-01
     */
    List<TreeEntity> getTreeList(PageData pd);

    List<Map<String, Object>> findDictionaryListByPathName(PageData pd);
}


