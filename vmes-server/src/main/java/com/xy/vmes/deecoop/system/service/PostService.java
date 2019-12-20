package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.DeptPostEntity;
import com.xy.vmes.entity.Post;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_post:岗位管理 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-08-01
*/
public interface PostService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    void save(Post post) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    void update(Post post) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    void updateAll(Post post) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    Post selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    List<Post> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    List<Post> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    List<Post> selectByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    void updateToDisableByIds(String[] ids)throws Exception;



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    Map<String, String> getKeyNameMap();
    Map<String, String> getNameKeyMap();//@
//    void createBusinessMap();
    void implementBusinessMapByDeptId(String deptId);//@

//    /**
//     * 生成岗位编码
//     *
//     * 创建人：刘威
//     * 创建时间：2018-07-26
//     *
//     * @param companyID  公司ID-组织架构ID
//     * @return
//     */
//    String createCoder(String companyID);

    Post findPost(PageData object);
    Post findPostById(String id);
    List<Post> findPostList(PageData object);
    List<Post> findPostListByDeptId(String deptId);//@

    /**
     * 获取部门岗位List
     * 创建人：陈刚
     * 创建时间：2018-08-28
     */
//    List<Map<String, Object>> listDeptPost(PageData pd);

//    TreeEntity deptPost2Tree(DeptPostEntity deptPost, TreeEntity tree);
//    List<TreeEntity> deptPostList2TreeList(List<Map<String, Object>> mapList, List<TreeEntity> treeList);

//    String checkDelPostByIds(String ids);

    ResultModel addPost(PageData pd) throws Exception;

    ResultModel updatePost(PageData pd)  throws Exception;

    ResultModel updateDisablePost(PageData pageData) throws Exception;

    ResultModel deletePosts(PageData pd) throws Exception;

    ResultModel listPagePosts(PageData pd, Pagination pg)  throws Exception;

    void exportExcelPosts(PageData pd, Pagination pg)  throws Exception;

    ResultModel treeDeptPosts(PageData pd)  throws Exception;

    ResultModel treeDeptPostsNotMainPost(PageData pd) throws Exception;
}



