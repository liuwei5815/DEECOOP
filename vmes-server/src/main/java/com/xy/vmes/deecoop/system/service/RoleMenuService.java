package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.MenuEntity;
import com.xy.vmes.entity.RoleMenu;
import com.xy.vmes.entity.Menu;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_role_menu:角色菜单表:角色按钮 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-07-30
*/
public interface RoleMenuService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    void save(RoleMenu roleMenu) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    void update(RoleMenu roleMenu) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    void updateAll(RoleMenu roleMenu) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    RoleMenu selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<RoleMenu> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    List<RoleMenu> dataList(PageData pd) throws Exception;

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


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    List<RoleMenu> findRoleMenuList(PageData object);
    /**
     * 根据当前角色ID-删除角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    void deleteRoleMenuByRoleId(String roleId) throws Exception;//@
    void deleteRoleMenuByMenuId(String menuId) throws Exception;//@

    /**
     * 添加角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    void addRoleMenuByMeunIds(String roleId, String meunIds);//@

    /**
     * 修改禁用属性(isdisable)
     * 根据角色ID-修改角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
//    void updateDisableByRoleId(String roleId);

    /**
     * 角色菜单-关联菜单表-<Map>
     * @param pd
     * @return
     */
    List<Map<String, Object>> findRoleMenuMapList(PageData pd);//@

    List<TreeEntity> getRoleMenuMapList(PageData pd) throws Exception;

    List<TreeEntity> getNoRoleMenuMapList(PageData pd) throws Exception;

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
//    Menu mapObject2Menu(Map<String, Object> mapObject, Menu object);

    List<Menu> mapList2MenuList(List<Map<String, Object>> mapList, List<Menu> objectList);//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-23
     */
    List<Map<String, Object>> listMenuMapByRole(PageData pd);//@

    /**
     * 角色菜单ListList<Map<String, Object>>转换成-树结构体List<TreeEntity>
     * @param mapList  角色菜单List<Map<String, Object>>
     * @param treeList 树结构体List<TreeEntity>
     * @return
     */
    List<TreeEntity> roleMenuList2TreeList(List<Map<String, Object>> mapList, List<TreeEntity> treeList);//@

//    MenuEntity menu2MenuEntity(Menu menu, MenuEntity entity);
//    List<MenuEntity> menuList2MenuEntityList(List<Menu> menuList, List<MenuEntity> entityList);

//    MenuEntity treeEntity2MenuEntity(TreeEntity treeEntity, MenuEntity entity);
    List<MenuEntity> treeList2MenuEntityList(List<TreeEntity> treeList, List<MenuEntity> entityList);//@

    void orderAcsByLayer(List<MenuEntity> entityList);//@

    String findMenuidByRoleIds(String roleIds);//@

    /**
     * 获取仓库属性(复杂版仓库,简版仓库)
     * 1. 根据(用户角色id)查询角色菜单表(vmes_role_menu)
     * 2. 返回值(warehouseByComplex:复杂版仓库 warehouseBySimple:简版仓库)
     * @param roleId  用户角色id
     * @return
     */
    String findWarehouseAttribute(String roleId);

    void deleteMenuFromParentRole(PageData pd) throws Exception;
}



