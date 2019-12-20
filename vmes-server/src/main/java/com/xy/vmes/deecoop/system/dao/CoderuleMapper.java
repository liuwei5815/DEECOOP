package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xy.vmes.entity.Coderule;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_coderule:系统编码规则表 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2018-07-26
*/
@Mapper
@Repository
public interface CoderuleMapper extends BaseMapper<Coderule> {

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
	List<Coderule> dataList(PageData pd);

	/**
	 * 创建人：陈刚 自动创建，禁止修改
	 * 创建时间：2018-07-23
	 */
	List<Map<String, Object>> findDataList(PageData pd);

	/**
	 * 创建人：陈刚
	 * 创建时间：2018-07-26
	 */
	Integer updateCoderule(PageData pd);
}


