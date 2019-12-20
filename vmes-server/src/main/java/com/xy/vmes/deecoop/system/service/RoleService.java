package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Role;
import com.xy.vmes.entity.UserRole;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_role:角色 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-07-30
*/
public interface RoleService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    void save(Role role) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    void update(Role role) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    void updateAll(Role role) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    Role selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<Role> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<Role> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<Map> findDataList(PageData pd) throws Exception;

    void deleteByIds(String[] ids) throws Exception;
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    List<LinkedHashMap> getColumnList() throws Exception;//@

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;//@

    /**
     * 批量修改角色信息为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-07-30
     */
//    void updateDisableByIds(String[] ids) throws Exception;

    List<Role> findRoleList(PageData object);//@
    Role findRoleById(String id);//@

    /**
     * check角色ID是否允许删除
     * 当前角色ID(用户角色,角色菜单,角色按钮)-是否使用
     *
     * 创建人：陈刚
     * 创建时间：2018-07-30
     * @param roleIds
     * @return
     */
//    String checkDeleteRoleByRoleIds(String roleIds);

    /**
     * 获取角色ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-08-01
     *
     * @param objectList
     * @return
     */
    String findRoleIdsByRoleList(List<Role> objectList);//@

    /**
     * 角色名称是否相同
     *
     * @param companyId  (不可为空)
     * @param id         (允许为空)-(添加时is null, 修改时 is not null)
     * @param name       (不可为空)
     * @return
     *     true : 角色名称相同
     *     false: 角色名称不相同(默认值)
     */
//    boolean isExistByName(String companyId, String id, String name);

    ResultModel listPageRoles(PageData pd, Pagination pg) throws Exception;

    void exportExcelRoles(PageData pd, Pagination pg) throws Exception;

    ResultModel addRole(PageData pageData) throws Exception;

    ResultModel updateRole(PageData pageData) throws Exception;

    ResultModel updateDisableRole(PageData pageData) throws Exception;

    ResultModel deleteRoles(PageData pageData) throws Exception;

    ResultModel saveRoleUsers(PageData pageData) throws Exception;

    ResultModel saveRoleMeuns(PageData pageData) throws Exception;

    ResultModel treeRoleMeunsAll(PageData pageData)  throws Exception;

    ResultModel treeRoleMeunsSelected(PageData pageData) throws Exception;

    ResultModel listRoleMeunsButtonsAll(PageData pageData) throws Exception;

    ResultModel saveRoleMeunsButtons(PageData pageData)  throws Exception;

    ResultModel listAllUsersByDeptId(PageData pageData)  throws Exception;

    ResultModel listUsersByRole(PageData pageData)  throws Exception;

    ResultModel findListUserByRole(PageData pageData)  throws Exception;

    ResultModel getRoles(PageData pd)  throws Exception;

    ResultModel addRoleByName(PageData pageData)  throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    Map<String, String> getKeyNameMap();
    Map<String, String> getNameKeyMap();//@
//    void createBusinessMap();
    void implementBusinessMapByCompanyId(String companyId);//@

    ResultModel listUserByRole(PageData pd)  throws Exception;

    ResultModel getRoleMeunsDataType(PageData pageData) throws Exception;

    ResultModel saveRoleMeunsData(PageData pageData) throws Exception;
}



