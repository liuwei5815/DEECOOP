package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.deecoop.system.dao.RoleMenuMapper;
import com.xy.vmes.entity.Menu;
import com.xy.vmes.entity.MenuEntity;
import com.xy.vmes.entity.RoleMenu;
import com.xy.vmes.entity.TreeEntity;
import com.xy.vmes.deecoop.system.service.MenuService;
import com.xy.vmes.deecoop.system.service.MenuTreeService;
import com.xy.vmes.deecoop.system.service.RoleMenuService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.xy.vmes.common.util.StringUtil;
import com.yvan.platform.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：vmes_role_menu:角色菜单表:角色按钮 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-07-30
*/
@Service
@Transactional(readOnly = false)
public class RoleMenuServiceImp implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuTreeService menuTreeService;

    @Autowired
    MenuService menuService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void save(RoleMenu roleMenu) throws Exception{
        roleMenu.setId(Conv.createUuid());
        roleMenu.setCdate(new Date());
        roleMenu.setUdate(new Date());
        roleMenuMapper.insert(roleMenu);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void update(RoleMenu roleMenu) throws Exception{
        roleMenu.setUdate(new Date());
        roleMenuMapper.updateById(roleMenu);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    @Override
    public void updateAll(RoleMenu roleMenu) throws Exception{
        roleMenu.setUdate(new Date());
        roleMenuMapper.updateAllColumnById(roleMenu);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    //@Cacheable(cacheNames = "roleMenu", key = "''+#id")
    public RoleMenu selectById(String id) throws Exception{
        return roleMenuMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void deleteById(String id) throws Exception{
        roleMenuMapper.deleteById(id);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<RoleMenu> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return roleMenuMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<RoleMenu> dataList(PageData pd) throws Exception{
        return roleMenuMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return roleMenuMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return roleMenuMapper.findDataList(pd);
    }

    public void deleteByColumnMap(Map columnMap) throws Exception {
        roleMenuMapper.deleteByMap(columnMap);
    }
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public List<RoleMenu> findRoleMenuList(PageData object) {
        List<RoleMenu> objectList = new ArrayList<RoleMenu>();
        if (object == null) {return objectList;}

        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    /**
     * 根据当前角色ID-删除角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void deleteRoleMenuByRoleId(String roleId) throws Exception {
        if (roleId == null || roleId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("role_id", roleId);

        this.deleteByColumnMap(mapObject);
    }
    public void deleteRoleMenuByMenuId(String menuId) throws Exception {
        if (menuId == null || menuId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("menu_id", menuId);

        this.deleteByColumnMap(mapObject);
    }

    /**
     * 添加角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void addRoleMenuByMeunIds(String roleId, String meunIds) {
        if (roleId == null || roleId.trim().length() == 0) {return;}
        if (meunIds == null || meunIds.trim().length() == 0) {return;}

        meunIds = StringUtil.stringTrimSpace(meunIds);
        String[] strArry = meunIds.split(",");
        for (int i = 0; i < strArry.length; i++) {
            String meunID = strArry[i];
            RoleMenu objectDB = new RoleMenu();
            objectDB.setRoleId(roleId);
            objectDB.setMenuId(meunID);
            try {
                this.save(objectDB);
            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }
        }
    }

    /**
     * 修改禁用属性(isdisable)
     * 根据角色ID-修改角色菜单
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void updateDisableByRoleId(String roleId) {
        roleMenuMapper.updateDisableByRoleId(roleId);
    }

    /**
     * 角色菜单-关联菜单表-<Map>
     * @param pd
     * @return
     */
    public List<Map<String, Object>> findRoleMenuMapList(PageData pd) {
        return roleMenuMapper.findRoleMenuMapList(pd);
    }


    public List<TreeEntity> getRoleMenuMapList(PageData pd) throws Exception {
        return roleMenuMapper.getRoleMenuMapList(pd);
    }

    public List<TreeEntity> getNoRoleMenuMapList(PageData pd) throws Exception {
        return roleMenuMapper.getNoRoleMenuMapList(pd);
    }


    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public Menu mapObject2Menu(Map<String, Object> mapObject, Menu object) {
        if (object == null) {object = new Menu();}
        if (mapObject == null) {return object;}

        //a.id,
        if (mapObject.get("id") != null) {
            object.setId(mapObject.get("id").toString().trim());
        }
        //b.pid as menuPid,
        if (mapObject.get("menuPid") != null) {
            object.setPid(mapObject.get("menuPid").toString().trim());
        }
        //b.company_id as menuCompanyId,
        if (mapObject.get("menuCompanyId") != null) {
            object.setCompanyId(mapObject.get("menuCompanyId").toString().trim());
        }
        //b.code as menuCode,
        if (mapObject.get("menuCode") != null) {
            object.setCode(mapObject.get("menuCode").toString().trim());
        }
        //b.name as menuName,
        if (mapObject.get("menuName") != null) {
            object.setName(mapObject.get("menuName").toString().trim());
        }
        //b.name_en as menuNameEn,
        if (mapObject.get("menuNameEn") != null) {
            object.setNameEn(mapObject.get("menuNameEn").toString().trim());
        }
        //b.serial_numbe as menuSerialNumber,
        if (mapObject.get("menuSerialNumber") != null) {
            try {
                object.setSerialNumber(Integer.valueOf(mapObject.get("menuSerialNumber").toString().trim()) );
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //b.icon as menuIcon,
        if (mapObject.get("menuIcon") != null) {
            object.setIcon(mapObject.get("menuIcon").toString().trim());
        }
        //b.type as menuType,
        if (mapObject.get("menuType") != null) {
            object.setType(mapObject.get("menuType").toString().trim());
        }
        //b.url as menuUrl,
        if (mapObject.get("menuUrl") != null) {
            object.setUrl(mapObject.get("menuUrl").toString().trim());
        }
        //b.isleaf as menuIsleaf,
        if (mapObject.get("menuIsleaf") != null) {
            object.setIsleaf(mapObject.get("menuIsleaf").toString().trim());
        }
        //b.layer as menuLayer,
        if (mapObject.get("menuLayer") != null) {
            try {
                object.setLayer(Integer.valueOf(mapObject.get("menuLayer").toString().trim()) );
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //b.isdisable as menuIsdisable,
        if (mapObject.get("menuIsdisable") != null) {
            object.setIsdisable(mapObject.get("menuIsdisable").toString().trim());
        }
        //b.cdate as menuCdate,
        if (mapObject.get("menuCdate") != null) {
            String dateStr = mapObject.get("menuCdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //b.cuser as menuCuser,
        if (mapObject.get("menuCuser") != null) {
            object.setCuser(mapObject.get("menuCuser").toString().trim());
        }

        //b.udate as menuUdate,
        if (mapObject.get("menuUdate") != null) {
            String dateStr = mapObject.get("menuUdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //b.uuser as menuUuser,
        if (mapObject.get("menuUuser") != null) {
            object.setUuser(mapObject.get("menuUuser").toString().trim());
        }
        //b.id_0 as menuId0,
        if (mapObject.get("menuId0") != null) {
            object.setId0(mapObject.get("menuId0").toString().trim());
        }
        //b.id_1 as menuId1,
        if (mapObject.get("menuId1") != null) {
            object.setId1(mapObject.get("menuId1").toString().trim());
        }
        //b.id_2 as menuId2,
        if (mapObject.get("menuId2") != null) {
            object.setId2(mapObject.get("menuId2").toString().trim());
        }

        //b.id_3 as menuId3,
        if (mapObject.get("menuId3") != null) {
            object.setId3(mapObject.get("menuId3").toString().trim());
        }
        //b.id_4 as menuId4,
        if (mapObject.get("menuId4") != null) {
            object.setId4(mapObject.get("menuId4").toString().trim());
        }
        //b.id_5 as menuId5
        if (mapObject.get("menuId5") != null) {
            object.setId5(mapObject.get("menuId5").toString().trim());
        }

        return object;
    }

    public List<Menu> mapList2MenuList(List<Map<String, Object>> mapList, List<Menu> objectList) {
        if (objectList == null) {
            objectList = new ArrayList<Menu>();
        }
        if (mapList == null || mapList.size() == 0) {return objectList;}

        for (Map<String, Object> mapObj : mapList) {
            Menu object = new Menu();
            object = this.mapObject2Menu(mapObj, object);
            objectList.add(object);
        }

        return objectList;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-23
     */
    public List<Map<String, Object>> listMenuMapByRole(PageData pd) {
        return roleMenuMapper.listMenuMapByRole(pd);
    }


    /**
     * 角色菜单ListList<Map<String, Object>>转换成-树结构体List<TreeEntity>
     * @param mapList  角色菜单List<Map<String, Object>>
     * @param treeList 树结构体List<TreeEntity>
     * @return
     */
    public List<TreeEntity> roleMenuList2TreeList(List<Map<String, Object>> mapList, List<TreeEntity> treeList) {
        if (treeList == null) {treeList = new ArrayList<TreeEntity>();}
        if (mapList == null || mapList.size() == 0) {return treeList;}

        //遍历mapList-生成treeList
        for (Map<String, Object> mapObj : mapList) {
            Menu menu = this.mapObject2Menu(mapObj, null);
            TreeEntity treeObj = menuTreeService.menu2Tree(menu, null);
            //当前节点-是否绑定角色(1:绑定 0:未绑定)
            treeObj.setIsBindRole(Boolean.FALSE);

            //当前菜单是否绑定角色
            // 判断条件: 角色id(roleId)是否存在--
            if (mapObj.get("roleId") != null && mapObj.get("roleId").toString().trim().length() > 0) {
                treeObj.setIsBindRole(Boolean.TRUE);
            }

            treeList.add(treeObj);
        }

        return treeList;
    }

    public MenuEntity menu2MenuEntity(Menu menu, MenuEntity entity) {
        if (entity == null) {entity = new MenuEntity();}
        if (menu == null) {return entity;}

        //id;
        entity.setId(menu.getId());
        //name;
        entity.setName(menu.getName());
        //nameEn;
        entity.setNameEn(menu.getNameEn());
        //url;
        entity.setUrl(menu.getUrl());
        //layer;
        if (menu.getLayer() != null) {
            entity.setLayer(menu.getLayer());
        }

        return entity;
    }

    public List<MenuEntity> menuList2MenuEntityList(List<Menu> menuList, List<MenuEntity> entityList) {
        if (entityList == null) {entityList = new ArrayList<MenuEntity>();}
        if (menuList == null || menuList.size() == 0) {return entityList;}

        for (Menu menu : menuList) {
            MenuEntity entity = this.menu2MenuEntity(menu, null);
            entityList.add(entity);
        }
        return entityList;
    }

    public MenuEntity treeEntity2MenuEntity(TreeEntity treeEntity, MenuEntity entity) {
        if (entity == null) {entity = new MenuEntity();}
        if (treeEntity == null) {return entity;}

        //id;
        entity.setId(treeEntity.getId());
        //name;
        entity.setName(treeEntity.getName());
        //nameEn;
        //entity.setNameEn(treeEntity.getNameEn());
        //url;
        entity.setUrl(treeEntity.getUrl());
        //layer;
        if (treeEntity.getLayer() != null) {
            entity.setLayer(treeEntity.getLayer());
        }

        return entity;
    }

    public List<MenuEntity> treeList2MenuEntityList(List<TreeEntity> treeList, List<MenuEntity> entityList) {
        if (entityList == null) {entityList = new ArrayList<MenuEntity>();}
        if (treeList == null || treeList.size() == 0) {return entityList;}

        for (TreeEntity treeEntity : treeList) {
            MenuEntity entity = this.treeEntity2MenuEntity(treeEntity, null);
            entityList.add(entity);
        }
        return entityList;
    }

    public String findMenuidByRoleIds(String roleIds) {
        if (roleIds == null || roleIds.trim().length() == 0) {return new String();}

        roleIds = StringUtil.stringTrimSpace(roleIds);
        roleIds = "'" + roleIds.replace(",", "','") + "'";

        PageData findMap = new PageData();
        findMap.put("queryStr", "b.role_id in (" +  roleIds + ")");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Map<String, Object>> mapList = this.findRoleMenuMapList(findMap);
        List<Menu> menuList = this.mapList2MenuList(mapList, null);

        return menuService.findMenuidByMenuList(menuList);
    }

    public void orderAcsByLayer(List<MenuEntity> entityList) {
        Collections.sort(entityList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                MenuEntity object_0 = (MenuEntity)arg0;
                MenuEntity object_1 = (MenuEntity)arg1;
                return object_0.getLayer().compareTo(object_1.getLayer());
            }
        });
    }

    /**
     * 获取仓库属性(复杂版仓库,简版仓库)
     * 1. 根据(用户角色id)查询角色菜单表(vmes_role_menu)
     * 2. 返回值(warehouseByComplex:复杂版仓库 warehouseBySimple:简版仓库)
     *
     * @param roleIds  用户角色id
     * @return
     */
    public String findWarehouseAttribute(String roleIds) {
        if (roleIds == null || roleIds.trim().length() == 0) {
            return null;
        }

        roleIds = StringUtil.stringTrimSpace(roleIds);
        roleIds = "'" + roleIds.replace(",", "','") + "'";

        List<Map<String, Object>> mapList = null;
        try {
            PageData findMap = new PageData();
            findMap.put("roleIds", roleIds);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            mapList = this.findRoleMenuMapList(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mapList == null || mapList.size() == 0) {return null;}

        List<Menu> menuList = this.mapList2MenuList(mapList, null);
        String menuIds = menuService.findMenuidByMenuList(menuList);
        if (menuIds == null || menuIds.trim().length() == 0) {return null;}

        //warehouseByComplex:复杂版仓库:15a6c4ca92fe42a0a82320287538b727(菜单id)
        //warehouseBySimple:简版仓库:5abe8f434e114a87a73b85ed74bc78e7(菜单id)
        menuIds = StringUtil.stringTrimSpace(menuIds);
        if (menuIds.indexOf(Common.SYS_WAREHOUSE_NAMEKEY_MAP.get("warehouseByComplex")) != -1) {
            return Common.SYS_WAREHOUSE_COMPLEX;
        } else if (menuIds.indexOf(Common.SYS_WAREHOUSE_NAMEKEY_MAP.get("warehouseBySimple")) != -1) {
            return Common.SYS_WAREHOUSE_SIMPLE;
        }

        return null;
    }

    @Override
    public void deleteMenuFromParentRole(PageData pd) throws Exception {
        roleMenuMapper.deleteMenuFromParentRole(pd);
    }
}



