package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Role;
import com.xy.vmes.entity.UserRole;
import com.yvan.PageData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_user_role:用户角色表 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-07-26
*/
public interface UserRoleService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void save(UserRole userRole) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void update(UserRole userRole) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    void updateAll(UserRole userRole) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    UserRole selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<UserRole> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<UserRole> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<Map> findDataList(PageData pd) throws Exception;

    void deleteByColumnMap(Map columnMap) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    List<UserRole> findUserRoleList(PageData object);//@

    UserRole findUserRole(PageData object);//@

    /**
     * 创建人：刘威
     * 创建时间：2018-07-23
     */
//    void deleteByIds(String[] ids) throws Exception;

    /**
     * 用户角色-关联角色表
     * @param pd
     * @return
     */
//    List<Map<String, Object>> findUserRoleMapList(PageData pd);

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    void deleteUserRoleByUserId(String userId) throws Exception;//@
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    void deleteUserRoleByRoleId(String roleId) throws Exception;//@

    /**
     * 添加用户角色
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    void addUserRoleByUserIds(String roleId, String userIds, String cuser);//@

    /**
     * 修改禁用属性(isdisable)
     * 根据角色ID-修改用户角色
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
//    void updateDisableByRoleId(String roleId);

    /**
     * 根据userID-获取角色ID(','逗号分隔的字符串)
     * @param userID
     * @return
     */
    String findRoleIdsByByUserID(String userID);//@
    /**
     * 根据roleID-获取用户ID(','逗号分隔的字符串)
     * @param roleID
     * @return
     */
    String findUserIdsByByRoleID(String roleID);//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
//    Role userRoleMap2Role(Map<String, Object> mapObject, Role object);
//    List<Role> userRoleMap2RoleList(List<Map<String, Object>> mapList, List<Role> objectList);

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-22
     */
//    List<LinkedHashMap<String, String>> listUserColumn();
    /**
     * 创建人：陈刚
     * 创建时间：2018-08-22
     */
    List<Map> listUserByRole(PageData pd);//@

    /**
     * 获取角色ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-08-01
     *
     * @param mapList
     * @return
     */
//    String findUserIdsByMapList(List<Map<String, Object>> mapList);

}



