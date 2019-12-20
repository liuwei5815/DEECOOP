package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DepartmentCustomerMapper extends BaseMapper {
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-10-24
     */
    List<Map> getDataListPage(PageData pd, Pagination pg);
}
