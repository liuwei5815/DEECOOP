package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.UserDefinedMenu;
import com.yvan.PageData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：用户自定义菜单 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-07-27
*/
public interface UserDefinedMenuService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    void save(UserDefinedMenu userDefinedMenu) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    void update(UserDefinedMenu userDefinedMenu) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-27
     */
    void updateAll(UserDefinedMenu userDefinedMenu) throws Exception;


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    UserDefinedMenu selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<UserDefinedMenu> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<UserDefinedMenu> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    List<Map> findDataList(PageData pd) throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    void deleteUserDefinedMenuByUserId(String userId) throws Exception;//@
    void deleteUserDefinedMenuByMenuId(String menuId) throws Exception;//@

    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    void deleteByColumnMap(Map columnMap) throws Exception;//@


    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    List<UserDefinedMenu> selectByColumnMap(Map columnMap) throws Exception;//@




    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    List<LinkedHashMap> getColumnList() throws Exception;//@

    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    List<Map> getDataList(PageData pd) throws Exception;//@
}



