package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Customer;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：vmes_customer:客户供应商表 Mapper.java
 * 创建人：陈刚 自动创建
 * 创建时间：2018-09-18
 */
@Mapper
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    List<Customer> dataListPage(PageData pd,Pagination pg);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    List<Customer> dataList(PageData pd);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    void deleteByIds(String[] ids);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    List<LinkedHashMap> findColumnList();


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    List<Map> findDataList(PageData pd);


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    List<Map> getDataListPage(PageData pd,Pagination pg);


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-09-18
     */
    void updateToDisableByIds(String[] ids);



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    List<TreeEntity> getTreeList(PageData pd);

    List<Map> getReceiveDataListPage(PageData pd,Pagination pg);

    void updateCustomerBalance(PageData pd);

    List<Map> getPreReceiveAmount(PageData pd);

    List<Map> getNowReceiveAmount(PageData pd);

    Map<String,Object> getReceiveAmount(PageData pd);
}

