package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Column;
import com.yvan.PageData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_column:模块栏位表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-08-24
*/
public interface ColumnService {
//

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    void save(Column column) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    void update(Column column) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-24
     */
    void updateAll(Column column) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    List<Column> dataList(PageData pd) throws Exception;


    List<Column> findColumnList(String modelCode) throws Exception;

    /**
     * 根据栏位字符串(逗号分隔的字符串)-重新调整List<Column>
     * 1. 栏位字符串全部显示
     * 2. 栏位字符串排列顺序就是显示顺序
     *
     * @param fieldCode   栏位key字符串 (','逗号分隔的字符串)
     * @param sourceList  List<Column>
     */
    List<Column> modifyColumnByFieldCode(String fieldCode, List<Column> sourceList);

}



