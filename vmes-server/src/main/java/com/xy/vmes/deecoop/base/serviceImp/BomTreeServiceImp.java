package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.entity.*;
import com.xy.vmes.deecoop.base.service.ProductService;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.base.dao.BomTreeMapper;
import com.xy.vmes.deecoop.base.service.BomTreeService;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：操作日志 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-09-29
*/
@Service
@Transactional(readOnly = false)
public class BomTreeServiceImp implements BomTreeService {


    @Autowired
    private BomTreeMapper bomTreeMapper;
    @Autowired
    private BomTreeService bomTreeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void save(BomTree bomTree) throws Exception{
        bomTree.setCdate(new Date());
        bomTree.setUdate(new Date());
        bomTreeMapper.insert(bomTree);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void update(BomTree bomTree) throws Exception{
        bomTree.setUdate(new Date());
        bomTreeMapper.updateById(bomTree);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void updateAll(BomTree bomTree) throws Exception{
        bomTree.setUdate(new Date());
        bomTreeMapper.updateAllColumnById(bomTree);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    //@Cacheable(cacheNames = "bomTree", key = "''+#id")
    public BomTree selectById(String id) throws Exception{
        return bomTreeMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void deleteById(String id) throws Exception{
        bomTreeMapper.deleteById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        bomTreeMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<BomTree> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return bomTreeMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<BomTree> dataList(PageData pd) throws Exception{
        return bomTreeMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return bomTreeMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return bomTreeMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        bomTreeMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<BomTree> selectByColumnMap(Map columnMap) throws Exception{
    List<BomTree> bomTreeList =  bomTreeMapper.selectByMap(columnMap);
        return bomTreeList;
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return bomTreeMapper.getDataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return bomTreeMapper.getDataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        bomTreeMapper.updateToDisableByIds(ids);
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


    @Override
    public void deleteByBomIds(String[] ids) throws Exception {
        bomTreeMapper.deleteByBomIds(ids);
    }

    @Override
    public List<TreeEntity> getBomTreeList(PageData pd) throws Exception {
        return bomTreeMapper.getBomTreeList(pd);
    }

    @Override
    public List<TreeEntity> getBomTreeProductList(PageData pd) throws Exception {
        return bomTreeMapper.getBomTreeProductList(pd);
    }

    @Override
    public ResultModel listProdLackNum(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("BomTreeProduct");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }
        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }

        //获取根节点表头
        Map rootTitleMap = ColumnUtil.getTitleList(columnList);
        result.put("hideTitles",rootTitleMap.get("hideTitles"));
        result.put("titles",rootTitleMap.get("titles"));
        String dtlJsonStr = pd.getString("dtlJsonStr");
        if (dtlJsonStr == null || dtlJsonStr.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请至少添加选择一条记录！");
            return model;
        }
        List<Map<String, String>> mapList = (List<Map<String, String>>) YvanUtil.jsonToList(dtlJsonStr);
        if (mapList == null || mapList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("Json字符串-转换成List错误！");
            return model;
        }
        List<TreeEntity> varMapList = new ArrayList();
        List<Map> treeMapList = new ArrayList();
        if(mapList!=null&&mapList.size()>0) {
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, String> detailMap = mapList.get(i);

                if(detailMap.get("prodId")==null){
                    model.putCode(Integer.valueOf(1));
                    model.putMsg("产品 ID 不能为空！");
                    return model;
                }
                if(detailMap.get("id")==null){
                    model.putCode(Integer.valueOf(1));
                    model.putMsg("BOM ID 不能为空！");
                    return model;
                }
                BigDecimal planCount = BigDecimal.ZERO;
                if(detailMap.get("planCount")!=null){
                    if(!StringUtils.isEmpty(detailMap.get("planCount").toString())){
                        planCount =  BigDecimal.valueOf(Double.parseDouble(detailMap.get("planCount").toString()));
                    }
                }
                String productId = detailMap.get("prodId").toString();
                String bomId = detailMap.get("id").toString();
                PageData pageData = new PageData();
                pageData.put("bomId",bomId);

                if(pd.get("isreplaceable")!=null && "1".equals(pd.get("isreplaceable"))){
                    pageData.put("isreplaceable",null);
                }else{
                    pageData.put("isreplaceable",'0');
                }

                List<TreeEntity> treeList = bomTreeService.getBomTreeProductList(pageData);

                Map map = new HashMap();
                map.put("productId",productId);
                map.put("planCount",planCount);
                map.put("treeList",treeList);
                treeMapList.add(map);
            }
        }
        if(treeMapList!=null&&treeMapList.size()>0){
            varMapList = TreeUtil.getProdLackNum(treeMapList);
            result.put("varList",varMapList);
            model.putResult(result);
            return model;
        }else {
            model.putCode(Integer.valueOf(1));
            model.putMsg("无查询记录！");
            return model;
        }

    }

    @Override
    public ResultModel getBomTreeProduct(PageData pd)throws Exception{
        ResultModel model = new ResultModel();
        Map result = new HashMap();

        List<Column> columnList = columnService.findColumnList("BomTreeProduct");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }
        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }

        //获取根节点表头
        Map rootTitleMap = ColumnUtil.getTitleList(columnList);

        result.put("hideTitles",rootTitleMap.get("hideTitles"));
        result.put("titles",rootTitleMap.get("titles"));

        columnList = columnService.findColumnList("BomTreeProductChildren");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }
        //获取子节点表头
        Map childrenTitleMap = ColumnUtil.getTitleList(columnList);

        List<TreeEntity> varMapList = new ArrayList();

        if(pd.get("isreplaceable")!=null && "1".equals(pd.get("isreplaceable"))){
            pd.put("isreplaceable",null);
        }else{
            pd.put("isreplaceable",'0');
        }

        List<TreeEntity> treeList = bomTreeService.getBomTreeProductList(pd);
        String expectCount = StringUtils.isEmpty(pd.getString("expectCount"))?"0":pd.getString("expectCount");
        TreeEntity treeObj = TreeUtil.switchBomTree(pd.getString("productId"),treeList,BigDecimal.valueOf(Double.parseDouble(expectCount)),childrenTitleMap);
//        TreeEntity treeObj = TreeUtil.switchBomTree("99767a4e1d7f4482bc90477096b62b4e",treeList,BigDecimal.TEN,childrenTitleMap);
        varMapList.add(treeObj);
        result.put("varList",varMapList);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel listPageBomTrees(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("BomTree");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();
        if(columnList!=null&&columnList.size()>0){
            for (Column column : columnList) {
                if(column!=null){
                    if("0".equals(column.getIshide())){
                        titlesHideList.add(column.getTitleKey());
                    }
                    LinkedHashMap titlesLinkedMap = new LinkedHashMap();
                    titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
                    varModelMap.put(column.getTitleKey(),"");
                    titlesList.add(titlesLinkedMap);
                }
            }
        }
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);

        List<Map> varMapList = new ArrayList();
        List<Map> varList = bomTreeService.getDataListPage(pd,pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);
        model.putResult(result);
        return model;
    }

    @Override
    public void exportExcelBomTrees(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("BomTree");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

        String ids = (String)pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);


        pg.setSize(100000);
        List<Map> dataList = bomTreeService.getDataListPage(pd, pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelBomTree";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel importExcelBomTrees(MultipartFile file) throws Exception {
        ResultModel model = new ResultModel();
        if (file == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请上传Excel文件！");
            return model;
        }

        // 验证文件是否合法
        // 获取上传的文件名(文件名.后缀)
        String fileName = file.getOriginalFilename();
        if (fileName == null
                || !(fileName.matches("^.+\\.(?i)(xlsx)$")
                || fileName.matches("^.+\\.(?i)(xls)$"))
                ) {
            String failMesg = "不是excel格式文件,请重新选择！";
            model.putCode(Integer.valueOf(1));
            model.putMsg(failMesg);
            return model;
        }

        // 判断文件的类型，是2003还是2007
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        List<List<String>> dataLst = ExcelUtil.readExcel(file.getInputStream(), isExcel2003);
        List<LinkedHashMap<String, String>> dataMapLst = ExcelUtil.reflectMapList(dataLst);

        //1. Excel文件数据dataMapLst -->(转换) ExcelEntity (属性为导入模板字段)
        //2. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        //3. Excel导入字段-名称唯一性判断-在Excel文件中
        //4. Excel导入字段-名称唯一性判断-在业务表中判断
        //5. List<ExcelEntity> --> (转换) List<业务表DB>对象
        //6. 遍历List<业务表DB> 对业务表添加或修改
        return model;
    }

    @Override
    public ResultModel getBomTree(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        List<TreeEntity> treeList = bomTreeService.getBomTreeList(pd);
        TreeEntity treeObj = TreeUtil.switchTree(pd.getString("prodId"), treeList);
        String treeJsonStr = YvanUtil.toJson(treeObj);
//        System.out.println("treeJsonStr: " + treeJsonStr);

        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel addBomTree(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        BomTree bomTree = (BomTree)HttpUtils.pageData2Entity(pd, new BomTree());
        String id = Conv.createUuid();
        bomTree.setId(id);
        bomTree.setPathId(bomTree.getPathId()+"_"+id);
        bomTree.setLayer(bomTree.getLayer()+1);
        Set linkProdIds  = this.getLinkProdIds(bomTree.getPathId(),bomTree.getBomId(),bomTree.getParentProdId());
        if(linkProdIds!=null&&linkProdIds.contains(bomTree.getProdId())){
            Product product = productService.selectById(bomTree.getProdId());
            model.putCode(Integer.valueOf(1));
            model.putMsg("货品("+product.getName()+")已被使用，请重新选择！");
            return model;
        }
        bomTreeService.save(bomTree);
        return model;
    }

    private Set getLinkProdIds(String pathId, String bomId, String parentProdId) throws Exception {
        Set linkProdIds = new HashSet();

        String[] pathIds = pathId.split("_");
        String ids = null;
        if(pathIds!=null&&pathIds.length>0){
            for(int i=0;i<pathIds.length;i++){
                if(ids == null){
                    ids = "'"+pathIds[i]+"'";
                }else{
                    ids = ids +  " , '"+pathIds[i]+"'";
                }
            }
        }
        PageData pageData = new PageData();
        pageData.put("queryStr"," id in ("+ids+") ");
        pageData.put("isQueryAll","true");
        List<BomTree>  bomTrees1 = bomTreeService.dataList(pageData);
        if(bomTrees1!=null&&bomTrees1.size()>0){
            for(int i=0;i<bomTrees1.size();i++){
                BomTree bomTree =  bomTrees1.get(i);
                linkProdIds.add(bomTree.getProdId());
            }
        }

        pageData = new PageData();
        pageData.put("queryStr"," bom_id = '"+bomId+"' and parent_prod_id = '"+parentProdId+"' ");
        pageData.put("isQueryAll","true");
        List<BomTree>  bomTrees2 = bomTreeService.dataList(pageData);
        if(bomTrees2!=null&&bomTrees2.size()>0){
            for(int i=0;i<bomTrees2.size();i++){
                BomTree bomTree =  bomTrees2.get(i);
                linkProdIds.add(bomTree.getProdId());
            }
        }

        return  linkProdIds;

    }

    @Override
    public ResultModel addBomTrees(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String dtlJsonStr = pd.getString("dtlJsonStr");
        if (dtlJsonStr == null || dtlJsonStr.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请至少添加选择一条货品数据！");
            return model;
        }

        List<Map<String, String>> mapList = (List<Map<String, String>>) YvanUtil.jsonToList(dtlJsonStr);
        if (mapList == null || mapList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("BOM明细Json字符串-转换成List错误！");
            return model;
        }

        Set linkProdIds  = new HashSet();


        if(mapList!=null&&mapList.size()>0) {
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, String> detailMap = mapList.get(i);
                BomTree bomTree = (BomTree) HttpUtils.pageData2Entity(detailMap, new BomTree());
                if(linkProdIds==null||linkProdIds.size()==0){
                    linkProdIds = this.getLinkProdIds(bomTree.getPathId(),bomTree.getBomId(),bomTree.getParentProdId());
                    if(linkProdIds!=null&&linkProdIds.contains(bomTree.getProdId())){
                        Product product = productService.selectById(bomTree.getProdId());
                        model.putCode(Integer.valueOf(1));
                        model.putMsg("货品("+product.getName()+")已被使用，请重新选择！");
                        return model;
                    }
                }
                PageData pageData = new PageData();
                pageData.put("parentProdId",bomTree.getParentProdId());
                pageData.put("bomId",bomTree.getBomId());
                pageData.put("isreplaceable","1");
                List<Map> replaceableMap = bomTreeService.getDataList(pageData);
                if(replaceableMap!=null&&replaceableMap.size()>0){
                    model.putCode(Integer.valueOf(1));
                    model.putMsg("请先删除当前货品下面的可替代物！");
                    return model;
                }

                String id = Conv.createUuid();
                bomTree.setId(id);
                bomTree.setPathId(bomTree.getPathId()+"_"+id);
                bomTree.setLayer(bomTree.getLayer()+1);
                bomTreeService.save(bomTree);
            }
        }
        return model;
    }

    @Override
    public ResultModel updateBomTree(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        BomTree newBomTree = (BomTree)HttpUtils.pageData2Entity(pd, new BomTree());
        BomTree oldBomTree = bomTreeService.selectById(newBomTree.getId());
        bomTreeService.update(newBomTree);

        pd = new PageData();
        pd.put("isQueryAll","true");
        pd.put("queryStr"," bom_id = '"+oldBomTree.getBomId()+"' and  parent_prod_id = '"+oldBomTree.getProdId()+"' and  layer = '"+(oldBomTree.getLayer()+1)+"' ");
//        pd.put("bomId",oldBomTree.getBomId());
//        pd.put("parentProdId",oldBomTree.getProdId());
//        pd.put("layer",oldBomTree.getLayer()+1);
        List<BomTree> bomTreeList = bomTreeService.dataList(pd);

        if(bomTreeList!=null&&bomTreeList.size()>0){
            for(int i=0;i<bomTreeList.size();i++){
                BomTree bomTree =  bomTreeList.get(i);
                bomTree.setParentProdId(newBomTree.getProdId());
                bomTreeService.update(bomTree);
            }

        }
        return model;
    }

    @Override
    public ResultModel deleteBomTree(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String id = pd.getString("id");
        if(StringUtils.isEmpty(id)){
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }

        BomTree bomTree = bomTreeService.selectById(id);

        pd = new PageData();
        pd.put("isQueryAll","true");
        pd.put("queryStr"," bom_id = '"+bomTree.getBomId()+"' and  parent_prod_id = '"+bomTree.getProdId()+"' and  layer = '"+(bomTree.getLayer()+1)+"' ");
        List<BomTree> bomTreeList = bomTreeService.dataList(pd);
        if(bomTreeList!=null&&bomTreeList.size()>0){
            model.putCode("2");
            model.putMsg("请删除该节点的所有下级子节点后再进行尝试删除操作！");
            return model;
        }else{
            bomTreeService.deleteById(id);
        }
        return model;
    }

    /**
     * 添加(BOMTree) vmes_bom_tree - Excel导入时调用
     * 按货品编码顺序导入(根货品编码,第一级根货品编码,第二级根货品编码,第三级根货品编码)
     *
     * @param bomid     bomID
     * @param parentId  bom_tree 父节点id
     * @param pathId    bom_tree(pathId)
     * @param dataMap   Excel导入行数据
     * @param prodList  货品id_List
     * @param count     递归执行次数
     */
    public void addBomTreeByProdList(String bomid,
                                     String parentId,
                                     String pathId,
                                     Map<String, String> dataMap,
                                     List<String> prodList,
                                     int count) {
        //1. 货品编码-bom-Tree(根节点)
        String prodId = prodList.get(prodList.size() - count);
        if (prodId == null || prodId.trim().length() == 0) {return;}

        try {
            BomTree bomTree = new BomTree();
            bomTree.setId(Conv.createUuid());
            bomTree.setProdId(prodId);
            bomTree.setBomId(bomid);

            String userId = dataMap.get("userId");
            bomTree.setCuser(userId);

            if (parentId == null || parentId.trim().length() == 0) {
                bomTree.setPathId(bomTree.getId());
                if (pathId == null) {pathId = bomTree.getId();}

                bomTree.setLayer(0);
                bomTree.setParentProdId("root");
                bomTree.setRatio(BigDecimal.ONE);
            } else if (parentId != null && parentId.trim().length() > 0) {
                //上级产品id parentProdId
                String parentProdId = prodList.get(prodList.size() - count - 1);
                bomTree.setParentProdId(parentProdId);

                pathId = pathId + "_" + bomTree.getId();
                bomTree.setPathId(pathId);

                String ratio_str = new String();
                if (1 == (prodList.size() - count)) {
                    ratio_str = dataMap.get("ratio_1");
                } else if (2 == (prodList.size() - count)) {
                    ratio_str = dataMap.get("ratio_2");

                } else if (3 == (prodList.size() - count)) {
                    ratio_str = dataMap.get("ratio_3");
                }

                BigDecimal ratio = BigDecimal.valueOf(0D);
                if (ratio_str != null && ratio_str.trim().length() > 0) {
                    try {
                        ratio = new BigDecimal(ratio_str);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                //四舍五入到2位小数
                ratio = ratio.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
                bomTree.setRatio(ratio);
            }
            bomTreeService.save(bomTree);

            if (0 == (count - 1)) {
                return;
            } else {
                count = count - 1;
                addBomTreeByProdList(bomid,
                        bomTree.getId(),
                        pathId,
                        dataMap,
                        prodList,
                        count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



