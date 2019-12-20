package com.xy.vmes.common.util;

import com.xy.vmes.entity.TreeEntity;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class TreeUtil {

//    public static Map<String,BigDecimal> materielStockCountMap = new HashMap();
//    public static Map<String,Map<String,BigDecimal>> productMaterielRatioMap = new HashMap();
//    public static Map<String,BigDecimal> materielRatioMap = new HashMap();
//    public static Map<String,BigDecimal> semiFinishedRatioMap =  new HashMap();
//    public static Map<String,BigDecimal> productStockCountMap = new HashMap();
    /**
     * 指定节点ID-及该指定节点ID下所有节点和子节点-生成树形结构
     * 当(指定节点ID)is null 或空字符串 -从根节点(root)开始生成树形结构
     *
     * @param nodeId     (允许为空)指定节点ID
     * @param objectList (业务数据List-全部有效业务数据)-(菜单,组织架构,字典)
     * @return
     */
    public static TreeEntity switchTree(String nodeId, List<TreeEntity> objectList) {
        if (objectList == null || objectList.size() == 0) {return new TreeEntity();}
        if (nodeId == null || nodeId.trim().length() == 0) {nodeId = new String("root");}

        //获得当前节点对象
        TreeEntity nodeObject = TreeUtil.findNodeById(nodeId, objectList);
        TreeUtil.createTree(nodeObject, objectList);

        return nodeObject;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<TreeEntity> listSwitchTree(String nodeId, List<TreeEntity> objectList) {
        List<TreeEntity> treeList = new ArrayList<TreeEntity>();
        if (objectList == null || objectList.size() == 0) {return treeList;}
        if (nodeId == null || nodeId.trim().length() == 0) {nodeId = new String("root");}

        TreeEntity tree = TreeUtil.switchTree(nodeId, objectList);
        if (tree != null && tree.getChildren() != null) {
            treeList = tree.getChildren();
        }

        return treeList;
    }

    /**
     * 指定节点ID-及该指定节点ID下所有节点和子节点-生成树形结构
     * 当(指定节点ID)is null 或空字符串 -从根节点(root)开始生成树形结构
     *
     * @param nodeId     (允许为空)指定节点ID
     * @param expectCount 当前产品的期望生产数量
     * @param objectList (业务数据List-全部有效业务数据)-(菜单,组织架构,字典)
     * @return
     */
    public static TreeEntity switchBomTree(String nodeId, List<TreeEntity> objectList ,BigDecimal expectCount, Map childrenTitleMap) {
        if (objectList == null || objectList.size() == 0) {return new TreeEntity();}
        if (nodeId == null || nodeId.trim().length() == 0) {nodeId = new String("root");}
        Map<String,BigDecimal> productStockCountMap = new HashMap<String,BigDecimal>();
        Map<String,BigDecimal> materielStockCountMap = new HashMap<String,BigDecimal>();
        Map<String,BigDecimal> materielRatioMap = new HashMap<String,BigDecimal>();
        Map<String,BigDecimal> semiFinishedRatioMap =  new HashMap<String,BigDecimal>();

        Map<String,Map<String,BigDecimal>> cacheMap = new HashMap<String,Map<String,BigDecimal>>();
        cacheMap.put("productStockCountMap",productStockCountMap);
        cacheMap.put("materielStockCountMap",materielStockCountMap);
        cacheMap.put("materielRatioMap",materielRatioMap);
        cacheMap.put("semiFinishedRatioMap",semiFinishedRatioMap);
        //获得当前节点对象
        TreeEntity nodeObject = findNodeById(nodeId, objectList);

        nodeObject.setSumRatio(BigDecimal.ONE);
        nodeObject.setSplitCount(BigDecimal.ZERO);
        initCacheMap(nodeObject, objectList,cacheMap);



        BigDecimal maxCount = null;
        for(String key:materielRatioMap.keySet()){
            BigDecimal materielRatio = materielRatioMap.get(key);
            BigDecimal materielStockCount = materielStockCountMap.get(key);
            if(maxCount==null){
                maxCount = materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN);
            }else{
                if(maxCount.compareTo(materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN))>0){
                    maxCount = materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN);
                }
            }
        }
        //Bom齐套分析：期望生产数量
//        nodeObject.setExpectCount(expectCount);
        //Bom齐套分析：下级物料列表的Title
        BigDecimal totalCount = maxCount==null?BigDecimal.ZERO:maxCount;
        nodeObject.setTotalCount(totalCount);


        nodeObject.setTitles((List<LinkedHashMap>)childrenTitleMap.get("titles"));
        nodeObject.setHideTitles((List<String>)childrenTitleMap.get("hideTitles"));
        createBomTree(nodeObject, objectList,cacheMap);



        //3、第三次递归 修正物料、中间件溢出的数量
        nodeObject.setTotalCount(nodeObject.getCount().add(nodeObject.getAssembledCount()));
        correctBomTree(nodeObject,cacheMap);
        //Bom齐套分析：期望生产数量
//        if(nodeObject.getMaxCount().compareTo(expectCount)<0){
//            nodeObject.setLackCount(expectCount.subtract(nodeObject.getMaxCount()));
//        }

        return nodeObject;
    }


    public static List<TreeEntity> getProdLackNum(List<Map> treeMapList) {
        List<TreeEntity> varMapList = new ArrayList();

        Map<String,BigDecimal> productStockCountMap = new HashMap<String,BigDecimal>();



        Map<String,Map<String,BigDecimal>> cacheMap = new HashMap<String,Map<String,BigDecimal>>();
        cacheMap.put("productStockCountMap",productStockCountMap);


        if(treeMapList!=null&&treeMapList.size()>0){
            for(int i=0;i<treeMapList.size();i++){
                Map<String,BigDecimal> materielRatioMap = new HashMap<String,BigDecimal>();
                Map<String,BigDecimal> semiFinishedRatioMap =  new HashMap<String,BigDecimal>();
                Map<String,BigDecimal> materielStockCountMap = new HashMap<String,BigDecimal>();
                cacheMap.put("materielRatioMap",materielRatioMap);
                cacheMap.put("semiFinishedRatioMap",semiFinishedRatioMap);
                cacheMap.put("materielStockCountMap",materielStockCountMap);
                Map treeMap = treeMapList.get(i);
                String nodeId = (String)treeMap.get("productId");
                BigDecimal planCount = (BigDecimal)treeMap.get("planCount");
                List<TreeEntity> objectList = (List<TreeEntity>)treeMap.get("treeList");


                //由于本次操作是批量循环操作，上一次循环结束，就要减少已消耗的中间件、物料数量，得到一个实时的中间件、物料库存数量，方便本次循环的递归
                //物料、中间件的实时库存数量
                productStockCountMap = cacheMap.get("productStockCountMap");
                if(productStockCountMap!=null&&productStockCountMap.size()>0){
                    Set<String> productIdSet = productStockCountMap.keySet();
                    if(productIdSet!=null&&productIdSet.size()>0){
                        for(String productId:productIdSet){
                            BigDecimal productStockCount = productStockCountMap.get(productId)!=null?productStockCountMap.get(productId):BigDecimal.ZERO;
                            updateObjectList(productId,productStockCount,objectList);
                        }
                    }
                }


                //1、第一次递归 计算当前产品理论上的最大可生产数量   将所有产品、中间件全部转换成物料后，根据转换后物料总数除以用料比例得到一个理论最大值
                TreeEntity nodeObject = findNodeById(nodeId, objectList);
                nodeObject.setSumRatio(BigDecimal.ONE);
                nodeObject.setSplitCount(BigDecimal.ZERO);
                initCacheMap(nodeObject, objectList,cacheMap);

                BigDecimal maxCount = null;
                for(String key:materielRatioMap.keySet()){
                    //物料与产品的用料比例
                    BigDecimal materielRatio = materielRatioMap.get(key);
                    //所有产品、中间件全部转换成物料后的物料总数
                    BigDecimal materielStockCount = materielStockCountMap.get(key);
                    if(maxCount==null){
                        maxCount = materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN);
                    }else{
                        if(maxCount.compareTo(materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN))>0){
                            maxCount = materielStockCount.divide(materielRatio,0,BigDecimal.ROUND_DOWN);
                        }
                    }
                 }

//                TreeEntity nodeObjectOld = nodeObject.clone();
//                HashMap<String,Map<String,BigDecimal>> cacheMapOld = new HashMap<String,Map<String,BigDecimal>>();
//                cacheMapOld.putAll(cacheMap);

                //2、第二次递归 计算当前产品的实际的最大可生产数量
                BigDecimal totalCount = maxCount==null?BigDecimal.ZERO:maxCount;
                nodeObject.setTotalCount(totalCount);
                createBomTree(nodeObject, objectList,cacheMap);
                nodeObject.setMaxCount(nodeObject.getStockCount().add(nodeObject.getAssembledCount()));
                nodeObject.setPlanCount(planCount);
                if(planCount.compareTo(BigDecimal.ZERO)==0){
                    nodeObject.setLackCount(BigDecimal.ZERO);
                }else{
                    if(planCount.compareTo(nodeObject.getMaxCount())>0){
                        nodeObject.setLackCount(planCount.subtract(nodeObject.getMaxCount()));
                    }else{
                        nodeObject.setLackCount(BigDecimal.ZERO);
                    }
                }


                //3、第三次递归 修正物料、中间件溢出的数量
                nodeObject.setTotalCount(nodeObject.getCount().add(nodeObject.getAssembledCount()));
                correctBomTree(nodeObject,cacheMap);


                varMapList.add(nodeObject);
            }



        }
        return varMapList;
    }

    private static void correctBomTree(TreeEntity nodeObject, Map<String, Map<String, BigDecimal>> cacheMap) {
        //物料、中间件的实时库存数量
        Map<String,BigDecimal> productStockCountMap = cacheMap.get("productStockCountMap");
        //获得当前节点id下的所有孩子
        List<TreeEntity> childList = nodeObject.getChildren();

        BigDecimal pUpRatio = nodeObject.getSumRatio()==null?BigDecimal.ZERO:nodeObject.getSumRatio();
        BigDecimal pStockCount = nodeObject.getStockCount()==null?BigDecimal.ZERO:nodeObject.getStockCount();
        BigDecimal pSplitCount = nodeObject.getSplitCount()==null?BigDecimal.ZERO:nodeObject.getSplitCount();
        BigDecimal pTotalCount = nodeObject.getTotalCount()==null?BigDecimal.ZERO:nodeObject.getTotalCount();

        if(childList!=null&&childList.size()>0){
            //Bom齐套分析：上级可组装数量
            BigDecimal pAssembledCount = null;
            for(TreeEntity child : childList){
                //Bom齐套分析：用料比例
                BigDecimal ratio = child.getRatio()==null?BigDecimal.ZERO:child.getRatio();
                BigDecimal currentStockCount = productStockCountMap.get(child.getId());
                BigDecimal stockCount = child.getStockCount()==null?BigDecimal.ZERO:child.getStockCount();
                child.setTotalCount(pTotalCount);
                BigDecimal lackCount = (pTotalCount.multiply(pUpRatio).subtract(pSplitCount.add(pStockCount))).multiply(ratio);
                if(lackCount.compareTo(stockCount)<0){
                    child.setStockCount(lackCount);
                    productStockCountMap.put(child.getId(),currentStockCount.add(stockCount).subtract(lackCount));
                }
                correctBomTree(child,cacheMap);

                BigDecimal  assembledCount = child.getAssembledCount()==null?BigDecimal.ZERO:child.getAssembledCount();
                if(ratio.compareTo(BigDecimal.ZERO)>0){
                    if(pAssembledCount==null){
                        //Bom齐套分析：上级可组装数量 = （实际库存数量 + 可组装数量）/ 用料比例
                        pAssembledCount = stockCount.add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN);
                    }else{
                        //Bom齐套分析：取物料组成中可组装成品的最小值为上级可组装数量
                        if(stockCount.add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN).compareTo(pAssembledCount)<0){
                            pAssembledCount = stockCount.add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN);
                        }
                    }
                }

            }

            if(pAssembledCount!=null){
                //Bom齐套分析：可组装数量
                nodeObject.setAssembledCount(pAssembledCount);
                //Bom齐套分析：最大可生产数量
                nodeObject.setMaxCount(pAssembledCount.add(pStockCount).setScale(0,BigDecimal.ROUND_DOWN));
            }
        }else{

            //Bom齐套分析：可组装数量
            nodeObject.setAssembledCount(BigDecimal.ZERO);
            //Bom齐套分析：最大可生产数量
            nodeObject.setMaxCount(pStockCount.setScale(0,BigDecimal.ROUND_DOWN));
            return;
        }

    }

    private static void updateObjectList(String productId, BigDecimal productStockCount, List<TreeEntity> objectList) {
        if(!StringUtils.isEmpty(productId)){
            if(objectList!=null&&objectList.size()>0){
                for (TreeEntity nodeObj : objectList) {
                    if (productId.equals(nodeObj.getId())) {
                        nodeObj.setStockCount(productStockCount);
                    }
                }
            }
        }
    }


    public static List<TreeEntity> getMaterielLackNum(List<Map> treeMapList) {
        List<TreeEntity> varMapList = new ArrayList();
        List<TreeEntity> materielMapList = new ArrayList();
        Map<String,BigDecimal> productStockCountMap = new HashMap<String,BigDecimal>();
//        Map<String,BigDecimal> materielStockCountMap = new HashMap<String,BigDecimal>();
        Map<String,BigDecimal> materielNeedCountMap = new HashMap<String,BigDecimal>();

        Map<String,Map<String,BigDecimal>> cacheMap = new HashMap<String,Map<String,BigDecimal>>();
        cacheMap.put("productStockCountMap",new HashMap<String,BigDecimal>());
        cacheMap.put("materielStockCountMap",new HashMap<String,BigDecimal>());


        if(treeMapList!=null&&treeMapList.size()>0){
            for(int i=0;i<treeMapList.size();i++){
                Map<String,BigDecimal> materielRatioMap = new HashMap<String,BigDecimal>();
                Map<String,BigDecimal> semiFinishedRatioMap =  new HashMap<String,BigDecimal>();
                cacheMap.put("materielRatioMap",materielRatioMap);
                cacheMap.put("semiFinishedRatioMap",semiFinishedRatioMap);

                Map treeMap = treeMapList.get(i);
                String nodeId = (String)treeMap.get("productId");
                BigDecimal planCount = (BigDecimal)treeMap.get("planCount");
                List<TreeEntity> objectList = (List<TreeEntity>)treeMap.get("treeList");
                //获得当前节点对象
                TreeEntity nodeObject = findNodeById(nodeId, objectList);
                nodeObject.setSumRatio(BigDecimal.ONE);
                nodeObject.setSplitCount(BigDecimal.ZERO);
                initCacheMap(nodeObject, objectList,cacheMap);

                for(String key:materielRatioMap.keySet()){
                    TreeEntity materiel = findNodeById(key, objectList);
                    materielMapList.add(materiel);
                    BigDecimal materielRatio = materielRatioMap.get(key);
                    BigDecimal materielNeedCount = planCount.multiply(materielRatio).setScale(0,BigDecimal.ROUND_DOWN);
                    if(materielNeedCountMap.get(key)!=null){
                        materielNeedCountMap.put(key,materielNeedCountMap.get(key).add(materielNeedCount));
                    }else{
                        materielNeedCountMap.put(key,materielNeedCount);
                    }
                }

            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String edate = format.format(new Date());
        Map<String,BigDecimal> materielStockCountMap  = cacheMap.get("materielStockCountMap");
        for(String key:materielNeedCountMap.keySet()){
            BigDecimal materielNeedCount = materielNeedCountMap.get(key);
            BigDecimal materielStockCount = materielStockCountMap.get(key);
            if(materielStockCount.compareTo(materielNeedCount)<0){
                TreeEntity materiel = findNodeById(key, materielMapList);
                materiel.setLackCount(materielNeedCount.subtract(materielStockCount).setScale(0,BigDecimal.ROUND_DOWN));
                materiel.setEdate(edate);
                materiel.setPlanCount(materielNeedCount);
                varMapList.add(materiel);
            }
        }
        return varMapList;
    }




    public static List<TreeEntity> getMaterielNum(List<Map> treeMapList) {
        List<TreeEntity> varMapList = new ArrayList();
        List<TreeEntity> planProductMapList = new ArrayList();
        Map<String,BigDecimal> productStockCountMap = new HashMap<String,BigDecimal>();
        Map<String,BigDecimal> planProductStockCountMap = new HashMap<String,BigDecimal>();


        Map<String,Map<String,BigDecimal>> cacheMap = new HashMap<String,Map<String,BigDecimal>>();
        cacheMap.put("productStockCountMap",productStockCountMap);
        cacheMap.put("planProductStockCountMap",planProductStockCountMap);


        if(treeMapList!=null&&treeMapList.size()>0){
            for(int i=0;i<treeMapList.size();i++){

                Map treeMap = treeMapList.get(i);
                String nodeId = (String)treeMap.get("productId");
                BigDecimal planCount = (BigDecimal)treeMap.get("planCount");
                List<TreeEntity> objectList = (List<TreeEntity>)treeMap.get("treeList");
                planProductMapList.addAll(objectList);
                //获得当前节点对象
                TreeEntity nodeObject = findNodeById(nodeId, objectList);
                nodeObject.setSumRatio(BigDecimal.ONE);
                nodeObject.setSplitCount(BigDecimal.ZERO);
                nodeObject.setPlanCount(planCount);
                nodeObject.setLackCount(planCount);
                initCacheMapNew(nodeObject, objectList,cacheMap);
            }
        }
        for(String key:planProductStockCountMap.keySet()){
            BigDecimal stockCount = planProductStockCountMap.get(key);
            TreeEntity planProduct = findNodeById(key, planProductMapList);
            planProduct.setPlanCount(stockCount);
            varMapList.add(planProduct);
        }
        return varMapList;
    }



    public static void createMenuTree(TreeEntity nodeObject,List<TreeEntity> objectList) {
        List<TreeEntity> childList = findChildListById(nodeObject.getId(), objectList);
        if(childList!=null&&childList.size()>0){
            nodeObject.setChildren(childList);
            for(TreeEntity child:childList){
                createMenuTree(child,objectList);
            }
        }
    }


    /**
     * 本方法为递归调用:
     * @param nodeObject
     * @param objectList
     * @return
     */
    private static void createBomTree(TreeEntity nodeObject, List<TreeEntity> objectList,Map<String,Map<String,BigDecimal>> cacheMap) {
        //物料、中间件的实时库存数量
        Map<String,BigDecimal> productStockCountMap = cacheMap.get("productStockCountMap");
        //获得当前节点id下的所有孩子
        List<TreeEntity> childList = findChildListById(nodeObject.getId(), objectList);
        BigDecimal pUpRatio = nodeObject.getSumRatio()==null?BigDecimal.ZERO:nodeObject.getSumRatio();
        BigDecimal pStockCount = nodeObject.getStockCount()==null?BigDecimal.ZERO:nodeObject.getStockCount();
        BigDecimal pSplitCount = nodeObject.getSplitCount()==null?BigDecimal.ZERO:nodeObject.getSplitCount();
        BigDecimal pTotalCount = nodeObject.getTotalCount()==null?BigDecimal.ZERO:nodeObject.getTotalCount();

        if(pSplitCount.add(pStockCount).compareTo(pTotalCount.multiply(pUpRatio))>=0){
            //Bom齐套分析：可组装数量
            nodeObject.setAssembledCount(BigDecimal.ZERO);
            //Bom齐套分析：最大可生产数量
            nodeObject.setMaxCount(pStockCount.setScale(0,BigDecimal.ROUND_DOWN));
        }else{
            if(childList.size()>0){
                List<TreeEntity> childListNew = new ArrayList<TreeEntity>();
                //Bom齐套分析：上级可组装数量
                BigDecimal pAssembledCount = null;
                for(int i = 0; i < childList.size(); i++){
                    TreeEntity child = childList.get(i);
                    //Bom齐套分析：用料比例
                    BigDecimal ratio = child.getRatio()==null?BigDecimal.ZERO:child.getRatio();
                    //Bom齐套分析：实际库存数量
//                    BigDecimal stockCount = child.getStockCount()==null?BigDecimal.ZERO:child.getStockCount();
                    BigDecimal stockCount = productStockCountMap.get(child.getId());
                    child.setSumRatio(pUpRatio.multiply(ratio));
                    child.setTotalCount(pTotalCount);

                    child.setSplitCount(pStockCount.add(pSplitCount).multiply(ratio));

                    BigDecimal lackCount = (pTotalCount.multiply(pUpRatio).subtract(pSplitCount.add(pStockCount))).multiply(ratio);
                    if(lackCount.compareTo(stockCount)>0){
                        child.setStockCount(stockCount);
                        productStockCountMap.put(child.getId(),BigDecimal.ZERO);
                    }else{
                        child.setStockCount(lackCount);
                        productStockCountMap.put(child.getId(),stockCount.subtract(lackCount));
                    }


                    child.setHideTitles(nodeObject.getHideTitles());
                    child.setTitles(nodeObject.getTitles());
                    createBomTree(child, objectList,cacheMap);

                    BigDecimal  assembledCount = child.getAssembledCount()==null?BigDecimal.ZERO:child.getAssembledCount();
                    if(child.getStockCount()==null){
                        child.setStockCount(BigDecimal.ZERO);
                    }
                    if(ratio.compareTo(BigDecimal.ZERO)>0){
                        if(pAssembledCount==null){
                            //Bom齐套分析：上级可组装数量 = （实际库存数量 + 可组装数量）/ 用料比例
                            pAssembledCount = child.getStockCount().add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN);
                        }else{
                            //Bom齐套分析：取物料组成中可组装成品的最小值为上级可组装数量
                            if(child.getStockCount().add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN).compareTo(pAssembledCount)<0){
                                pAssembledCount = child.getStockCount().add(assembledCount).divide(ratio,0,BigDecimal.ROUND_DOWN);
                            }
                        }
                    }

                    childListNew.add(child);
                }
                if(pAssembledCount!=null){
                    //Bom齐套分析：可组装数量
                    nodeObject.setAssembledCount(pAssembledCount);
                    //Bom齐套分析：最大可生产数量
                    nodeObject.setMaxCount(pAssembledCount.add(pStockCount).setScale(0,BigDecimal.ROUND_DOWN));
                }
                nodeObject.setChildren(childListNew);
            }else{

                //Bom齐套分析：可组装数量
                nodeObject.setAssembledCount(BigDecimal.ZERO);
                //Bom齐套分析：最大可生产数量
                nodeObject.setMaxCount(pStockCount.setScale(0,BigDecimal.ROUND_DOWN));
                return;
            }
        }


    }


    private static void initCacheMap(TreeEntity nodeObject, List<TreeEntity> objectList,Map<String,Map<String,BigDecimal>> cacheMap) {
        //最末级物料与产品的用料比例
        Map<String,BigDecimal> materielRatioMap = cacheMap.get("materielRatioMap");
        //中间件与产品的用料比例
        Map<String,BigDecimal> semiFinishedRatioMap = cacheMap.get("semiFinishedRatioMap");
        //物料、中间件的实时库存数量
        Map<String,BigDecimal> productStockCountMap = cacheMap.get("productStockCountMap");
        //产品、中间件拆分后的总数量
        Map<String,BigDecimal> materielStockCountMap = cacheMap.get("materielStockCountMap");
        //获得当前节点id下的所有孩子
        List<TreeEntity> childList = findChildListById(nodeObject.getId(), objectList);
        BigDecimal pStockCount = nodeObject.getStockCount()==null?BigDecimal.ZERO:nodeObject.getStockCount();
        BigDecimal pSplitCount = nodeObject.getSplitCount()==null?BigDecimal.ZERO:nodeObject.getSplitCount();
        BigDecimal pUpRatio = nodeObject.getSumRatio()==null?BigDecimal.ZERO:nodeObject.getSumRatio();
        if(childList.size()>0){

            if(semiFinishedRatioMap.get(nodeObject.getId())!=null){
                semiFinishedRatioMap.put(nodeObject.getId(),semiFinishedRatioMap.get(nodeObject.getId()).add(pUpRatio));
            }else{
                semiFinishedRatioMap.put(nodeObject.getId(),pUpRatio);
            }

            List<TreeEntity> childListNew = new ArrayList<TreeEntity>();
             for(int i = 0; i < childList.size(); i++){
                TreeEntity child = childList.get(i);
                //Bom齐套分析：用料比例
                BigDecimal ratio = child.getRatio()==null?BigDecimal.ZERO:child.getRatio();
                child.setSumRatio(pUpRatio.multiply(ratio));

                BigDecimal productStockCount = productStockCountMap.get(nodeObject.getId());
                if(productStockCount==null){
                    child.setSplitCount(pStockCount.add(pSplitCount).multiply(ratio));
                }else{
                    child.setSplitCount(pSplitCount.multiply(ratio));
                }

                child.setHideTitles(nodeObject.getHideTitles());
                child.setTitles(nodeObject.getTitles());
                initCacheMap(child, objectList,cacheMap);
                childListNew.add(child);
            }
            nodeObject.setChildren(childListNew);

            productStockCountMap.put(nodeObject.getId(),pStockCount);
        }else{
//            BigDecimal productStockCount = productStockCountMap.get(nodeObject.getId());
//            if(productStockCount==null){
//                if(materielStockCountMap.get(nodeObject.getId())!=null){
//                    materielStockCountMap.put(nodeObject.getId(),materielStockCountMap.get(nodeObject.getId()).add(pSplitCount).add(pStockCount));
//                }else{
//                    materielStockCountMap.put(nodeObject.getId(),pSplitCount.add(pStockCount));
//                }
//            }else{
//                if(materielStockCountMap.get(nodeObject.getId())!=null){
//                    materielStockCountMap.put(nodeObject.getId(),materielStockCountMap.get(nodeObject.getId()).add(pSplitCount));
//                }else{
//                    materielStockCountMap.put(nodeObject.getId(),pSplitCount);
//                }
//            }


            if(materielStockCountMap.get(nodeObject.getId())!=null){
                materielStockCountMap.put(nodeObject.getId(),materielStockCountMap.get(nodeObject.getId()).add(pSplitCount));
            }else{
                materielStockCountMap.put(nodeObject.getId(),pSplitCount.add(pStockCount));
            }


            if(materielRatioMap.get(nodeObject.getId())!=null){
                materielRatioMap.put(nodeObject.getId(),materielRatioMap.get(nodeObject.getId()).add(pUpRatio));
            }else{
                materielRatioMap.put(nodeObject.getId(),pUpRatio);
            }
            productStockCountMap.put(nodeObject.getId(),pStockCount);
            return;
        }
    }




    private static void initCacheMapNew(TreeEntity nodeObject, List<TreeEntity> objectList,Map<String,Map<String,BigDecimal>> cacheMap) {
        Map<String,BigDecimal> productStockCountMap = cacheMap.get("productStockCountMap");
        Map<String,BigDecimal> planProductStockCountMap = cacheMap.get("planProductStockCountMap");
        //获得当前节点id下的所有孩子
        List<TreeEntity> childList = findChildListById(nodeObject.getId(), objectList);
        BigDecimal pLackCount = nodeObject.getLackCount()==null?BigDecimal.ZERO:nodeObject.getLackCount();
        BigDecimal pUpRatio = nodeObject.getSumRatio()==null?BigDecimal.ZERO:nodeObject.getSumRatio();
        if(childList.size()>0){

            List<TreeEntity> childListNew = new ArrayList<TreeEntity>();
            for(int i = 0; i < childList.size(); i++){
                TreeEntity child = childList.get(i);
                //Bom齐套分析：用料比例
                BigDecimal ratio = child.getRatio()==null?BigDecimal.ZERO:child.getRatio();
                child.setSumRatio(pUpRatio.multiply(ratio));

                BigDecimal childStockCount = productStockCountMap.get(child.getId());
                if(childStockCount==null){
                    childStockCount = child.getStockCount();
                    productStockCountMap.put(child.getId(),childStockCount);
                }

                if(pLackCount.compareTo(BigDecimal.ZERO)>0){

                    BigDecimal planProductCount = planProductStockCountMap.get(child.getId())==null?BigDecimal.ZERO: planProductStockCountMap.get(child.getId());
                    if(pLackCount.multiply(child.getRatio()).compareTo(childStockCount)>0){
                        planProductStockCountMap.put(child.getId(),planProductCount.add(childStockCount));
                        productStockCountMap.put(child.getId(),BigDecimal.ZERO);
                        child.setLackCount(pLackCount.multiply(child.getRatio()).subtract(childStockCount));
                    }else{
                        planProductStockCountMap.put(child.getId(),planProductCount.add(pLackCount.multiply(child.getRatio())));
                        productStockCountMap.put(child.getId(),childStockCount.subtract(pLackCount.multiply(child.getRatio())));
                        child.setLackCount(BigDecimal.ZERO);
                    }
                }

                child.setHideTitles(nodeObject.getHideTitles());
                child.setTitles(nodeObject.getTitles());
                initCacheMapNew(child, objectList,cacheMap);
                childListNew.add(child);
            }
            nodeObject.setChildren(childListNew);
        }
    }


    /**
     * 本方法为递归调用:
     * @param nodeObject
     * @param objectList
     * @return
     */
    private static void createTree(TreeEntity nodeObject, List<TreeEntity> objectList) {
        //获得当前节点id下的所有孩子
        List<TreeEntity> childList = TreeUtil.findChildListById(nodeObject.getId(), objectList);
        if (childList.size() > 0) {
            List<TreeEntity> childListNew = new ArrayList<TreeEntity>();
            for(int i = 0; i < childList.size(); i++){
                TreeEntity child = childList.get(i);
                TreeUtil.createTree(child, objectList);
                childListNew.add(child);
            }
            nodeObject.setChildren(childListNew);

            //isChecked 设置树节点是否选中 (true:选中 false:未选中)
            //非叶子节点是否选中 (isChecked) 判断条件 通过当前节点是否选中 (true:选中 false:未选中)
            //当前节点是否选中 (true:选中 false:未选中)
            nodeObject.setIsChecked(nodeObject.getIsBindRole());

            if (childListNew != null && childListNew.size() > 0) {
                Boolean isChecked = TreeUtil.isAllCheckedByTreeList(childListNew);
                nodeObject.setIsChecked(isChecked);
            }
        } else {
            //nodeObject.setChildren(null);

            //isChecked 设置树节点是否选中 (true:选中 false:未选中)
            //叶子节点是否选中 (isChecked) 判断条件 通过当前节点-是否绑定角色(true:绑定 false:未绑定)

            //当前节点无叶子节点情况
            //当前节点-是否绑定角色(true:绑定 false:未绑定)
            nodeObject.setIsChecked(nodeObject.getIsBindRole());

            //获取该节点下子节点list
            List<TreeEntity> nodeChildList = null;
            if (nodeObject != null && nodeObject.getChildren() != null) {
                nodeChildList = nodeObject.getChildren();
            }

            if (nodeChildList != null && nodeChildList.size() > 0) {
                Boolean isChecked = TreeUtil.isAllBindRoleByTreeList(nodeChildList);
                nodeObject.setIsChecked(isChecked);
            }

            return;
        }
    }

    /**
     * 获得当前节点id下的所有孩子节点List
     * @param id
     * @return
     */
    private static List<TreeEntity> findChildListById(String id, List<TreeEntity> objectList) {
        List<TreeEntity> childList = new ArrayList<TreeEntity>();
        if (id == null || id.trim().length() == 0) {return childList;}

        for (TreeEntity nodeObj : objectList) {
            if (id.equals(nodeObj.getPid())) {
                TreeEntity newNodeObj = nodeObj.clone();
                childList.add(newNodeObj);
            }
        }

        //按菜单排列顺序升序排列
        orderAcsTreeBySerialNumber(childList);

        return childList;
    }

    /**
     * 获得当前节点id对象
     * @param id
     * @return
     */
    public static TreeEntity findNodeById(String id, List<TreeEntity> objectList) {
        if (id == null || id.trim().length() == 0) {return null;}
        for (TreeEntity nodeObj : objectList) {
            if (id.equals(nodeObj.getId())) {
                return nodeObj;
            } else if ("root".equals(id) && id.equals(nodeObj.getPid())) {
                return nodeObj;
            }
        }
        return null;
    }

    private static void orderAcsTreeBySerialNumber(List<TreeEntity> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                TreeEntity object_0 = (TreeEntity)arg0;
                TreeEntity object_1 = (TreeEntity)arg1;
                return object_0.getSerialNumber().compareTo(object_1.getSerialNumber());
            }
        });
    }

    private static void orderAcsTreeByLayer(List<TreeEntity> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                TreeEntity object_0 = (TreeEntity)arg0;
                TreeEntity object_1 = (TreeEntity)arg1;
                return object_0.getLayer().compareTo(object_1.getLayer());
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 遍历树形结构体List<TreeEntity>，获取树节点List<TreeEntity>
     *     nodeType:= "root" 获取所有根节点
     *     nodeType:= "leaf" 获取所有叶子节点
     *
     *
     * @param nodeType  节点类型(root leaf)
     * @param treeList  树形结构体
     * @return
     */
    public static List<TreeEntity> findNodeListByTreeList(String nodeType, List<TreeEntity> treeList, List<TreeEntity> nodeList) {
        if (nodeList == null) {nodeList = new ArrayList<TreeEntity>();}
        if (treeList == null) {return nodeList;}
        if (nodeType == null || nodeType.trim().length() == 0) {return nodeList;}

        if ("root".equals(nodeType)) {
            findRootNodeListByTreeList(treeList, nodeList);
        } else if ("leaf".equals(nodeType)) {
            findLeafNodeListByTreeList(treeList, nodeList);
        }

        //按菜单级别升序排列
        orderAcsTreeByLayer(nodeList);

        return nodeList;

    }

    /**
     * 获取所有根节点List
     * 本方法为递归调用:
     *
     * @param treeList   树形结构List
     * @param nodeList   返回值根节点List
     */
    private static void findRootNodeListByTreeList(List<TreeEntity> treeList, List<TreeEntity> nodeList) {
        for (TreeEntity treeNode : treeList) {
            //获取当前节点下所有孩子List
            List<TreeEntity> childList = treeNode.getChildren();
            if (childList != null && childList.size() > 0) {
                //获取当前节点-如果是根节点
                TreeEntity rootNode = treeNode.clone();
                rootNode.setChildren(null);
                nodeList.add(rootNode);

                findRootNodeListByTreeList(childList, nodeList);
            }
        }
    }

    /**
     * 获取所有叶子节点List
     * 本方法为递归调用:
     *
     * @param treeList   树形结构List
     * @param nodeList   返回值根节点List
     */
    private static void findLeafNodeListByTreeList(List<TreeEntity> treeList, List<TreeEntity> nodeList) {
        for (TreeEntity treeNode : treeList) {
            //获取当前节点下所有孩子List
            List<TreeEntity> childList = treeNode.getChildren();
            if (childList != null && childList.size() > 0) {
                findLeafNodeListByTreeList(childList, nodeList);
            } else {
                TreeEntity leafNode = treeNode.clone();
                nodeList.add(leafNode);
            }
        }
    }

    private static Boolean isAllBindRoleByTreeList(List<TreeEntity> treeList) {
        if (treeList == null || treeList.size() == 0) {return Boolean.FALSE;}

        for (TreeEntity object : treeList) {
            if (object.getIsBindRole() == null || !object.getIsBindRole().booleanValue()) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private static Boolean isAllCheckedByTreeList(List<TreeEntity> treeList) {
        if (treeList == null || treeList.size() == 0) {return Boolean.FALSE;}

        for (TreeEntity object : treeList) {
            if (object.getIsChecked() == null || !object.getIsChecked().booleanValue()) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

}
