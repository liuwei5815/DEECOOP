package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.BomTree;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：操作日志 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-09-29
*/
public interface BomTreeService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void save(BomTree bomTree) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void update(BomTree bomTree) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void updateAll(BomTree bomTree) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    BomTree selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<BomTree> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<BomTree> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<BomTree> selectByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-29
    */
    void updateToDisableByIds(String[] ids)throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    void deleteByBomIds(String[] id_arry)throws Exception;

    List<TreeEntity> getBomTreeList(PageData pd)throws Exception;

    List<TreeEntity> getBomTreeProductList(PageData pd) throws Exception;

    ResultModel listPageBomTrees(PageData pd, Pagination pg)throws Exception;

    void exportExcelBomTrees(PageData pd, Pagination pg)throws Exception;

    ResultModel importExcelBomTrees(MultipartFile file)throws Exception;

    ResultModel getBomTree(PageData pd)throws Exception;

    ResultModel addBomTree(PageData pd)throws Exception;

    ResultModel updateBomTree(PageData pd)throws Exception;

    ResultModel deleteBomTree(PageData pd)throws Exception;

    ResultModel getBomTreeProduct(PageData pd)throws Exception;

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
    void addBomTreeByProdList(String bomid,
                              String parentId,
                              String pathId,
                              Map<String, String> dataMap,
                              List<String> prodList,
                              int count);  //@

    ResultModel listProdLackNum(PageData pd, Pagination pg)throws Exception;

    ResultModel addBomTrees(PageData pd) throws Exception;
}



