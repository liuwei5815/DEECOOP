package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.ColumnMapper;
import com.xy.vmes.entity.Column;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：vmes_column:模块栏位表 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-08-24
*/
@Service
@Transactional(readOnly = false)
public class ColumnServiceImp implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;
//
    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    @Override
    public void save(Column column) throws Exception{
        column.setId(Conv.createUuid());
        column.setCdate(new Date());
        columnMapper.insert(column);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    @Override
    public void update(Column column) throws Exception{
        columnMapper.updateById(column);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-24
     */
    @Override
    public void updateAll(Column column) throws Exception{
        columnMapper.updateAllColumnById(column);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-24
    */
    @Override
    public List<Column> dataList(PageData pd) throws Exception{
        return columnMapper.dataList(pd);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-24
     */
    @Override
    public List<Column> findColumnList(String modelCode) throws Exception {
        if (modelCode == null || modelCode.trim().length() == 0) {return new ArrayList<Column>();}

        PageData findMap = new PageData();
        findMap.put("modelCode", modelCode);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        return this.dataList(findMap);
    }

    /**
     * 根据栏位字符串(逗号分隔的字符串)-重新调整List<Column>
     * 1. 栏位字符串全部显示
     * 2. 栏位字符串排列顺序就是显示顺序
     *
     * @param fieldCode   栏位key字符串 (','逗号分隔的字符串)
     * @param sourceList  List<Column>
     */
    public List<Column> modifyColumnByFieldCode(String fieldCode, List<Column> sourceList) {
        if (sourceList == null) {sourceList = new ArrayList<Column>();}
        if (fieldCode == null || fieldCode.trim().length() == 0) {return sourceList;}

        fieldCode = StringUtil.stringTrimSpace(fieldCode);
        sourceList = ColumnUtil.listAllhideByColumnList(sourceList);
        LinkedHashMap<String, Column> columnMap = ColumnUtil.columnList2ColumnMap(sourceList, null);

        List<Column> newColumnList = ColumnUtil.modifyColumnList(fieldCode, columnMap);
        if (newColumnList != null && newColumnList.size() > 0) {
            ColumnUtil.orderAcsBySerialNumber(newColumnList);
        }

        return newColumnList;
    }

}



