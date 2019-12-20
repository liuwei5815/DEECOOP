package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.List;
import java.util.Map;

public interface DepartmentCustomerService {

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-10-24
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;//@

    ResultModel listPageDepartmentCustomer(PageData pd, Pagination pg)throws Exception;
}
