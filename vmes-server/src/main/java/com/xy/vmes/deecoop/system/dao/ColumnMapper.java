package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Column;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_column:模块栏位表 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2018-08-24
*/
@Mapper
@Repository
public interface ColumnMapper extends BaseMapper<Column> {

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
	List<Column> dataList(PageData pd);

}


