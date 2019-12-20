package com.xy.vmes.deecoop.base.serviceImp;

import com.xy.vmes.deecoop.base.dao.BomTreeToProductMapper;
import com.xy.vmes.deecoop.base.service.BomTreeToProductService;
import com.yvan.PageData;
import com.yvan.common.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 说明：BomTree表关联货品表 实现类
 * 创建人：陈刚
 * 创建时间：2019-09-27
 */
@Service
public class BomTreeToProductServiceImp implements BomTreeToProductService {
    @Autowired
    private BomTreeToProductMapper bomTreeToProductMapper;

    public List<Map<String, Object>> findBomTreeProductList(PageData pd) throws Exception {
        return bomTreeToProductMapper.findBomTreeProductList(pd);
    }

    /**
     * 本方法为递归调用:
     * @param nodeMap  当前节点Map对象
     * @param dataList BomTree查询结果集
     * @param bomTreeProdList 按引用传入方法调用后值发生改变
     * @return
     */
    public void findMapLitBomTreeToProduct(Map<String, Object> nodeMap,
                                           List<Map<String, Object>> dataList,
                                           List<Map<String, Object>> bomTreeProdList) {
        if (bomTreeProdList == null) {bomTreeProdList = new ArrayList<>();}
        if (nodeMap == null) {return;}
        if (dataList == null) {return;}

        String prodId = (String)nodeMap.get("prodId");
        //获得当前节点id下的所有孩子
        List<Map<String, Object>> childList = this.findMapListByParentProdId(prodId, dataList);
        if (childList.size() > 0) {
            for (int i = 0; i < childList.size(); i++) {
                Map<String, Object> childObject = childList.get(i);

                // 修改当前Map节点对象-根据父节点Map对象
                // 1. index 当前节点序号
                // 2. indexCode 当前节点序号字符串
                // 3. pathIndexCode 根节点至当前节点序号字符串
                // 4. pathRatio 根节点至当前节点用料比例乘积
                childObject = this.updateMapByParentMap(childObject, nodeMap, Integer.valueOf(i+1));
                bomTreeProdList.add(childObject);

                this.findMapLitBomTreeToProduct(childObject, dataList, bomTreeProdList);
            }
        } else {
            return;
        }
    }

    public List<Map> findMapListExportExcel(List<Map<String, Object>> bomTreeProdList) {
        List<Map> mapList = new ArrayList<>();
        if (bomTreeProdList == null || bomTreeProdList.size() == 0) {return mapList;}

        for (Map<String, Object> mapObject : bomTreeProdList) {
            mapList.add(mapObject);
        }

        return mapList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public Map<String, Object> findMapByBomTreeId(String bomTreeId, List<Map<String, Object>> mapList) {
//        if (bomTreeId == null || bomTreeId.trim().length() == 0) {return null;}
//        if (mapList == null || mapList.size() == 0) {return null;}
//        bomTreeId = bomTreeId.trim();
//
//        for (Map<String, Object> mapObject : mapList) {
//            String id = (String)mapObject.get("id");
//            if (bomTreeId.equals(id)) {
//                return mapObject;
//            }
//        }
//
//        return null;
//    }

    public List<Map<String, Object>> findMapListByParentProdId(String parentProdId, List<Map<String, Object>> mapList) {
        List<Map<String, Object>> objectList = new ArrayList<>();
        if (parentProdId == null || parentProdId.trim().length() == 0) {return objectList;}
        if (mapList == null || mapList.size() == 0) {return objectList;}

        parentProdId = parentProdId.trim();
        for (Map<String, Object> mapObject : mapList) {
            String parentProdId_DB = (String)mapObject.get("parentProdId");
            if (parentProdId.equals(parentProdId_DB)) {
                objectList.add(mapObject);
            }
        }

        return objectList;
    }

    public Map<String, Object> findRootMap(List<Map<String, Object>> mapList) {
        if (mapList == null || mapList.size() == 0) {return null;}

        for (Map<String, Object> mapObject : mapList) {
            String parentProdId = (String)mapObject.get("parentProdId");
            if ("root".equals(parentProdId)) {
                return this.updateRootMap(mapObject);
            }
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Map<String, Object> updateRootMap(Map<String, Object> rootMap) {
        if (rootMap == null) {return null;}

        //pathIndexCode 根节点至当前节点序号字符串
        rootMap.put("pathIndexCode", "");

        //ratio 节点用料比例
        BigDecimal ratio = BigDecimal.valueOf(1D);
        rootMap.put("ratio", ratio);

        //pathRatio 根节点至当前节点用料比例乘积
        BigDecimal pathRatio = BigDecimal.valueOf(1D);
        rootMap.put("pathRatio", pathRatio);

        return rootMap;
    }

    /**
     * 修改当前Map节点对象-根据父节点Map对象
     * 1. index 当前节点序号
     * 2. indexCode 当前节点序号字符串
     * 3. pathIndexCode 根节点至当前节点序号字符串
     * 4. pathRatio 根节点至当前节点用料比例乘积
     *
     * @param selfMap
     * @param parentMap
     * @param index
     * @return
     */
    public Map<String, Object> updateMapByParentMap(Map<String, Object> selfMap,
                                                    Map<String, Object> parentMap,
                                                    Integer index) {
        if (selfMap == null) {return null;}
        if (parentMap == null) {return selfMap;}

        //index 当前节点序号
        selfMap.put("index", index);

        //indexCode 当前节点序号字符串
        selfMap.put("indexCode", index.toString());

        //layer 当前节点级别
        Integer layer = (Integer)selfMap.get("layer");

        //pathIndexCode 根节点至当前节点序号字符串
        String pathIndexCode = new String();
        if (layer != null && 1 == layer.intValue()) {
            pathIndexCode = index.toString();
        } else if (layer != null && 1 < layer.intValue()) {
            //父节点-根节点至当前(父节点)节点序号字符串
            String pathIndexCode_parent = new String();
            if (parentMap.get("pathIndexCode") != null) {
                pathIndexCode_parent = (String)parentMap.get("pathIndexCode");
            }
            pathIndexCode = pathIndexCode_parent + "." + index.toString();
        }
        selfMap.put("pathIndexCode", pathIndexCode);

        //pathRatio 父节点-根节点至当前节点用料比例乘积
        BigDecimal pathRatio_parent = BigDecimal.valueOf(0D);
        if (parentMap.get("pathRatio") != null) {
            pathRatio_parent = (BigDecimal)parentMap.get("pathRatio");
        }

        //ratio (当前)节点用料比例
        BigDecimal ratio_self = BigDecimal.valueOf(0D);
        if (selfMap.get("ratio") != null) {
            ratio_self = (BigDecimal)selfMap.get("ratio");
        }

        //pathRatio (当前)根节点至当前节点用料比例乘积
        BigDecimal pathRatio_self = BigDecimal.valueOf(pathRatio_parent.doubleValue() * ratio_self.doubleValue());
        //四舍五入到2位小数
        pathRatio_self = pathRatio_self.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);

        selfMap.put("pathRatio", pathRatio_self);

        return selfMap;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
