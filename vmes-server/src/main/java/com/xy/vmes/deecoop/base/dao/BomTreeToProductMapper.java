package com.xy.vmes.deecoop.base.dao;

import com.yvan.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 说明：BomTree表关联货品表 Mapper.java
 * 创建人：陈刚
 * 创建时间：2019-09-27
 */
@Mapper
@Repository
public interface BomTreeToProductMapper {
    List<Map<String, Object>> findBomTreeProductList(PageData pd);
}
