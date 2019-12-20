package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.system.dao.PostMapper;
import com.xy.vmes.entity.*;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_post:岗位管理 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-08-01
*/
@Service
@Transactional(readOnly = false)
public class PostServiceImp implements PostService {


    @Autowired
    private PostMapper postMapper;
    @Autowired
    private EmployPostService employPostService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public void save(Post post) throws Exception{
        post.setId(Conv.createUuid());
        post.setCdate(new Date());
        post.setUdate(new Date());
        postMapper.insert(post);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public void update(Post post) throws Exception{
        post.setUdate(new Date());
        postMapper.updateById(post);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public void updateAll(Post post) throws Exception{
        post.setUdate(new Date());
        postMapper.updateAllColumnById(post);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    //@Cacheable(cacheNames = "post", key = "''+#id")
    public Post selectById(String id) throws Exception{
        return postMapper.selectById(id);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public void deleteById(String id) throws Exception{
        postMapper.deleteById(id);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        postMapper.deleteByIds(ids);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public List<Post> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return postMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public List<Post> dataList(PageData pd) throws Exception{
        return postMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return postMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return postMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        postMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
    @Override
    public List<Post> selectByColumnMap(Map columnMap) throws Exception{
    List<Post> postList =  postMapper.selectByMap(columnMap);
        return postList;
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return postMapper.getColumnList();
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return postMapper.getDataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return postMapper.getDataListPage(pd,pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        postMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    private Map<String, String> keyNameMap;
    private Map<String, String> nameKeyMap;

    public Map<String, String> getKeyNameMap() {return keyNameMap;}
    public Map<String, String> getNameKeyMap() {
        return nameKeyMap;
    }
    public void createBusinessMap() {
        this.keyNameMap = new HashMap<String, String>();
        this.nameKeyMap = new HashMap<String, String>();
    }
    public void implementBusinessMapByDeptId(String deptId) {
        this.createBusinessMap();

        List<Post> postList = this.findPostListByDeptId(deptId);
        if (postList == null || postList.size() == 0) {return;}
        for (Post object : postList) {
            String mapKey = object.getId();
            String mapName = object.getName();
            if (mapName != null && mapName.trim().length() > 0) {
                this.keyNameMap.put(mapKey, mapName);
                this.nameKeyMap.put(mapName, mapKey);
            }
        }
    }

//    /**
//     * 生成岗位编码
//     *
//     * 创建人：刘威
//     * 创建时间：2018-07-26
//     *
//     * @param companyID  公司ID-组织架构ID
//     * @return
//     */
//    @Override
//    public String createCoder(String companyID) {
//        //(企业编号+前缀字符+日期字符+流水号)-(company+prefix+date+code)
//        //(无需+前缀字符+无需+流水号)-W000142
//        CoderuleEntity object = new CoderuleEntity();
//        //tableName 业务名称(表名)
//        object.setTableName("vmes_post");
//        //companyID 公司ID
//        object.setCompanyID(companyID);
//        //length 指定位数(5)
//        object.setLength(Common.CODE_RULE_LENGTH_DEFAULT);
//        //firstName 第一个编码名称
//        object.setFirstName("prefix");
//
//        //separator 分隔符
//        //object.setSeparator("-");
//        //filling 填充字符(0)
//        object.setFilling(Common.CODE_RULE_DEFAULT_FILLING);
//
//        //isNeedPrefix 是否需要前缀
//        object.setIsNeedPrefix(Boolean.TRUE);
//        object.setIsNeedDate(Boolean.TRUE);
//        object.setDateFormat("");
//        //prefix 前缀字符
//        object.setPrefix("P");
//
//        return coderuleService.findCoderule(object);
//    }

    public Post findPost(PageData object) {
        if (object == null) {return null;}

        List<Post> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    public Post findPostById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findPost(findMap);
    }

    public List<Post> findPostList(PageData object) {
        if (object == null) {return null;}

        List<Post> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<Post> findPostListByDeptId(String deptId) {
        if (deptId == null || deptId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("deptId", deptId);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findPostList(findMap);
    }

    /**
     * 获取部门岗位List
     * 创建人：陈刚
     * 创建时间：2018-08-28
     */
    public List<Map<String, Object>> listDeptPost(PageData pd) {
        return postMapper.listDeptPost(pd);
    }

    public TreeEntity deptPost2Tree(DeptPostEntity deptPost, TreeEntity tree) {
        if (tree == null) {tree = new TreeEntity();}
        if (deptPost == null) {return tree;}

        //id;
        tree.setId(deptPost.getId());
        //pid;
        tree.setPid(deptPost.getPid());
        //isdisable
        tree.setIsdisable(deptPost.getIsdisable());
        tree.setName(deptPost.getName());
        //deptName;
        tree.setDeptName(deptPost.getDeptName());
        //postName;
        tree.setPostName(deptPost.getPostName());
        //layer;
        tree.setLayer(deptPost.getLayer());
        //serialNumber;
        tree.setSerialNumber(deptPost.getSerialNumber());
        //"dept" 部门 "post" 岗位
        //type;
        tree.setType(deptPost.getType());

        return tree;
    }

    public List<TreeEntity> deptPostList2TreeList(List<Map<String, Object>> mapList, List<TreeEntity> treeList) {
        if (treeList == null) {treeList = new ArrayList<TreeEntity>();}
        if (mapList == null || mapList.size() == 0) {return treeList;}

        //遍历mapList-生成treeList
        for (Map<String, Object> mapObj : mapList) {
            DeptPostEntity deptPost = (DeptPostEntity)HttpUtils.pageData2Entity(mapObj, new DeptPostEntity());
            TreeEntity tree = this.deptPost2Tree(deptPost, null);
            treeList.add(tree);
        }
        return treeList;
    }

    public String checkDelPostByIds(String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return new String();
        }

        String msgTemp_1 = "勾选数据 第 {0} 行: 该岗位已经绑定员工不可删除！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer msgBuf = new StringBuffer();
        String[] id_arry = ids.split(",");
        for (int i = 0; i < id_arry.length; i++) {
            String postId = id_arry[i];
            List<EmployPost> objectList = employPostService.findEmployPostListByPostId(postId);
            if (objectList != null && objectList.size() > 0) {
                String msg_Str = MessageFormat.format(msgTemp_1, (i+1));
                msgBuf.append(msg_Str);
            }
        }

        return  msgBuf.toString();
    }

    @Override
    public ResultModel addPost(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Post post = (Post)HttpUtils.pageData2Entity(pd, new Post());
        String sessionID = pd.getString("sessionID");

        String deptId = pd.getString("deptId");
        if(StringUtils.isEmpty(deptId)){
            model.putCode(1);
            model.putMsg("所属部门不能为空！");
            return model;
        }
        Department department = departmentService.selectById(deptId);

        String companyId = department.getId1();
        if(!StringUtils.isEmpty(companyId)){
            post.setCompanyId(companyId);
        }else{
            //如果没有公司ID，那么就是创建根节点下
            post.setCompanyId(department.getId0());
        }
        String code = coderuleService.createCoder(companyId,"vmes_post","P");
        if(StringUtils.isEmpty(code)){
            model.putCode(2);
            model.putMsg("编码规则创建异常，请重新操作！");
            return model;
        }
        post.setCode(code);
        this.save(post);
        return model;
    }

    @Override
    public ResultModel updatePost(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Post post = (Post)HttpUtils.pageData2Entity(pd, new Post());

        String remark = new String();
        if (post.getRemark() != null && post.getRemark().trim().length() > 0) {
            remark = post.getRemark().trim();
        }
        post.setRemark(remark);

        this.update(post);
        return model;
    }

    private boolean checkExsitOnlineEmployee(String postId,Set<String> postOnlineSet) throws Exception {
        PageData pd = new PageData();
        pd.putQueryStr(" isdisable = 1 ");
        return checkExsitEmployee(postId,postOnlineSet,pd);
    }

    private boolean checkExsitDownlineEmployee(String postId,Set<String> postDownlineSet) throws Exception {
        PageData pd = new PageData();
        pd.putQueryStr(" isdisable = 0 ");
        return checkExsitEmployee(postId,postDownlineSet,pd);
    }

    private boolean checkExsitEmployee(String postId,Set<String> postSet,PageData pd) throws Exception  {
        if(pd==null){
            pd = new PageData();
        }
        if(postSet==null){
            postSet = new HashSet<String>();
            List<Map> varList = employPostService.getDataList(pd);
            if(varList!=null&&varList.size()>0){
                for(int i=0;i<varList.size();i++){
                    if(varList.get(i)!=null&&varList.get(i).get("postId")!=null){
                        postSet.add(varList.get(i).get("postId").toString());
                    }
                }
            }
        }
        if(postSet.contains(postId)){
            return true;
        }
        return false;
    }

    @Override
    public ResultModel updateDisablePost(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String id = (String)pageData.get("id");
        String isdisable = (String)pageData.get("isdisable");

        String msgStr = new String();
        if (id == null || id.trim().length() == 0) {
            msgStr = msgStr + "id为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (isdisable == null || isdisable.trim().length() == 0) {
            msgStr = msgStr + "isdisable为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //当前岗位ID-员工岗位表(vmes_employ_post)中是否存在
        if ("0".equals(isdisable) && this.checkExsitOnlineEmployee(id, null)) {
            model.putCode(1);
            model.putMsg("该岗位下面存在绑定的员工不能禁用！");
            return model;
        }

        Post postDB = this.findPostById(id);
        postDB.setIsdisable(isdisable);
        postDB.setUdate(new Date());
        this.update(postDB);
        return model;
    }


    @Override
    public ResultModel deletePosts(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String postIds = pd.getString("ids");
        if (postIds == null || postIds.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！");
            return model;
        }

        postIds = StringUtil.stringTrimSpace(postIds);
        String[] ids = postIds.split(",");
        //当前岗位是否绑定有员工
        String msgStr = this.checkDelPostByIds(postIds);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }
        //删除(vmes_post)岗位表
        this.deleteByIds(ids);
        return model;
    }

    @Override
    public ResultModel listPagePosts(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        //1. 查询遍历List列表
        List<Column> columnList = columnService.findColumnList("post");
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

        Map result = new HashMap();
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);

        String isdisable = pd.getString("isdisable");
        if(StringUtils.isEmpty(isdisable)){
            pd.put("isdisable","1");
        }

        //Service (deptId)参数已经使用 -- 更改为(userDeptId)
        if (pd.get("userDeptId") != null && pd.get("userDeptId").toString().trim().length() > 0) {
            String deptId = ((String)pd.get("userDeptId")).trim();
            String queryIdStr = departmentService.findDeptidById(deptId, null, "department.");
            pd.put("queryStr", queryIdStr);
        }


        List<Map> varMapList = new ArrayList();
        List<Map> varList = this.getDataListPage(pd, pg);
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
    public void exportExcelPosts(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("post");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

        String ids = pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "post.id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);

        pg.setSize(100000);
        List<Map> dataList = this.getDataListPage(pd, pg);
        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);

        HttpServletResponse response  = HttpUtils.currentResponse();
        //查询数据-Excel文件导出
        //String fileName = "Excel数据字典数据导出";
        String fileName = "ExcelPost";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel treeDeptPosts(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //部门id 为空查询整棵部门树
        //部门id 非空查询当前部门下所有子部门(包含当前部门节点)
        String deptId = (String)pd.get("currentCompanyId");

        PageData findMap = new PageData();
        findMap.put("deptDisable", pd.getString("deptDisable"));
        findMap.put("postDisable", pd.getString("postDisable"));
        if (deptId != null && deptId.trim().length() > 0) {
            String queryIdStr = departmentService.findDeptidById(deptId, null, null);
            findMap.put("deptQuery", queryIdStr);

            String queryIdStr_1 = departmentService.findDeptidById(deptId, null, "b.");
            findMap.put("postQuery", queryIdStr_1);
        }

        //1. 查询(部门+岗位)表
        List<Map<String, Object>> deptPostList = this.listDeptPost(findMap);
        List<TreeEntity> treeList = this.deptPostList2TreeList(deptPostList, null);

        //2. 获得部门岗位树形结构
        TreeEntity treeObj = TreeUtil.switchTree(deptId, treeList);
        String treeJsonStr = YvanUtil.toJson(treeObj);
        System.out.println("treeJsonStr: " + treeJsonStr);

        //3. 树形结构返回前端
        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel treeDeptPostsNotMainPost(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //部门id 为空查询整棵部门树
        //部门id 非空查询当前部门下所有子部门(包含当前部门节点)
        String deptId = pd.getString("currentCompanyId");

        PageData findMap = new PageData();
        findMap.put("deptDisable", pd.getString("deptDisable"));
        findMap.put("postDisable", pd.getString("postDisable"));
        if (deptId != null && deptId.trim().length() > 0) {
            String queryIdStr = departmentService.findDeptidById(deptId, null, null);
            findMap.put("deptQuery", queryIdStr);

            String queryIdStr_1 = departmentService.findDeptidById(deptId, null, "b.");
            findMap.put("postQuery", queryIdStr_1);
        }

        //获取员工id-获取员工主岗id
        String employeeId = pd.getString("employeeId");
        EmployPost employPost = employPostService.findMainEmployPost(employeeId);
        if (employPost != null && employPost.getPostId() != null && employPost.getPostId().trim().length() > 0) {
            findMap.put("mainPostQuery", "a.id not in ('"+ employPost.getPostId().trim() +"')");
        }

        //1. 查询(部门+岗位)表
        List<Map<String, Object>> deptPostList = this.listDeptPost(findMap);
        List<TreeEntity> treeList = this.deptPostList2TreeList(deptPostList, null);

        //2. 获得部门岗位树形结构
        TreeEntity treeObj = TreeUtil.switchTree(deptId, treeList);
        String treeJsonStr = YvanUtil.toJson(treeObj);
        System.out.println("treeJsonStr: " + treeJsonStr);

        //3. 树形结构返回前端
        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);
        return model;
    }
}



