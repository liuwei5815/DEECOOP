package com.xy.vmes.deecoop.base.service;

import com.yvan.PageData;

import java.util.List;
import java.util.Map;

/**
 * 说明：BomTree表关联货品表 接口类
 * 创建人：陈刚
 * 创建时间：2019-09-27
 */
public interface BomTreeToProductService {
    /**
     * 本方法为递归调用:
     * @param nodeMap  当前节点Map对象
     * @param dataList BomTree查询结果集
     * @param bomTreeProdList 按引用传入方法调用后值发生改变
     * @return
     */
    void findMapLitBomTreeToProduct(Map<String, Object> nodeMap,
                                    List<Map<String, Object>> dataList,
                                    List<Map<String, Object>> bomTreeProdList);

    List<Map> findMapListExportExcel(List<Map<String, Object>> bomTreeProdList);

    List<Map<String, Object>> findBomTreeProductList(PageData pd) throws Exception;

    //Map<String, Object> findMapByBomTreeId(String bomTreeId, List<Map<String, Object>> mapList);
    List<Map<String, Object>> findMapListByParentProdId(String parentProdId, List<Map<String, Object>> mapList);
    Map<String, Object> findRootMap(List<Map<String, Object>> mapList);

    Map<String, Object> updateRootMap(Map<String, Object> rootMap);
    Map<String, Object> updateMapByParentMap(Map<String, Object> selfMap,
                                             Map<String, Object> parentMap,
                                             Integer index);
}
