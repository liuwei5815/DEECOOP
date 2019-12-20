package com.xy.vmes.deecoop.system.dao;

import com.yvan.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 创建人：陈刚
 * 创建时间：2018-07-20
 */
@Mapper
@Repository
public interface UserEmployeeMapper {

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    List<Map<String, Object>> findViewUserEmployList(PageData pd);
}
