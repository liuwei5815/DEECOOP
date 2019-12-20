package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.EmployPostMapper;
import com.xy.vmes.entity.EmployPost;
import com.xy.vmes.deecoop.system.service.EmployPostService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：vmes_employ_post:员工岗位关系表 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-08-01
*/
@Service
@Transactional(readOnly = false)
public class EmployPostServiceImp implements EmployPostService {


    @Autowired
    private EmployPostMapper employPostMapper;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public void save(EmployPost employPost) throws Exception{
        employPost.setId(Conv.createUuid());
        employPost.setCdate(new Date());
        employPostMapper.insert(employPost);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public void update(EmployPost employPost) throws Exception{
        employPost.setUdate(new Date());
        employPostMapper.updateById(employPost);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
//    @Override
    public void updateAll(EmployPost employPost) throws Exception{
        employPost.setUdate(new Date());
        employPostMapper.updateAllColumnById(employPost);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    //@Cacheable(cacheNames = "employPost", key = "''+#id")
    public EmployPost selectById(String id) throws Exception{
        return employPostMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public void deleteById(String id) throws Exception{
        employPostMapper.deleteById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public void deleteByIds(String[] ids) throws Exception{
        employPostMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public List<EmployPost> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return employPostMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public List<EmployPost> dataList(PageData pd) throws Exception{
        return employPostMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return employPostMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return employPostMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        employPostMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-01
    */
//    @Override
    public List<EmployPost> selectByColumnMap(Map columnMap) throws Exception{
    List<EmployPost> employPostList =  employPostMapper.selectByMap(columnMap);
        return employPostList;
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
//    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return employPostMapper.getColumnList();
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
//    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return employPostMapper.getDataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
//    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return employPostMapper.getDataListPage(pd,pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-01
     */
    public void updateToDisableByIds(String[] ids)throws Exception{
        employPostMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public void deleteEmployPostByEmployId(String employId) throws Exception {
        if (employId == null || employId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("employ_id", employId);

        this.deleteByColumnMap(mapObject);
    }

    public void deleteEmployPostByPostId(String postId) throws Exception {
        if (postId == null || postId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("post_id", postId);

        this.deleteByColumnMap(mapObject);
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-08-03
     */
    @Override
    public void  updateToDisableByEmployIds(String[] ids) throws Exception{
        employPostMapper.updateToDisableByEmployIds(ids);
    }

    public List<EmployPost> findEmployPostList(PageData object) {
        if (object == null) {return null;}

        List<EmployPost> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<EmployPost> findEmployPostListByPostId(String postId) {
        if (postId == null || postId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("postId", postId);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        return this.findEmployPostList(findMap);
    }

    public EmployPost findEmployPost(PageData object) {
        if (object == null) {return null;}

        List<EmployPost> objectList = this.findEmployPostList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    public EmployPost findEmployPostById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        return this.findEmployPost(findMap);
    }

    public EmployPost findMainEmployPost(String employId) {
        if (employId == null || employId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("employId", employId);
        //是否兼岗(1:兼岗0:主岗) 数据字典:sys_isplurality
        findMap.put("isplurality", "0");
        //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        return this.findEmployPost(findMap);
    }

    /**
     * check (员工id, 岗位id字符串)-岗位id字符串 中是否含有主岗
     * @param employId 员工id
     * @param postIds  岗位id字符串
     * @return
     */
    public boolean checkEmployMainPostByPostIds(String employId, String postIds) {
        if (employId == null || employId.trim().length() == 0) {return false;}
        if (postIds == null || postIds.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("employId", employId);
        //是否兼岗(1:兼岗0:主岗) 数据字典:sys_isplurality
        findMap.put("isplurality", "0");

        postIds = StringUtil.stringTrimSpace(postIds);
        postIds = "'" + postIds.replace(",", "','") + "'";
        findMap.put("queryStr", "post_id in (" + postIds + ")");

        List<EmployPost> objectList = this.findEmployPostList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }
}



