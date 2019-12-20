package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.MenuButton;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_menu_button:菜单按钮表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-08-03
*/
public interface MenuButtonService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void save(MenuButton menuButton) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void update(MenuButton menuButton) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-03
     */
    void updateAll(MenuButton menuButton) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    MenuButton selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<MenuButton> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<MenuButton> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<MenuButton> selectByColumnMap(Map columnMap) throws Exception;


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    void updateToDisableByIds(String[] ids)throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    List<MenuButton> findMenuButtonList(PageData object);//@
//    List<MenuButton> findMenuButtonListByIds(String ids);
    /**
     * 根据ID-查询菜单按钮表(vmes_menu_button)
     * @param id
     * @return
     */
//    MenuButton findMenuButtonById(String id);

    /**
     * 遍历List<MenuButton>-获取(修改禁用属性主键ID,物理删除主键ID)
     * 启用状态改为(禁用)-禁用执行物理删除
     * Map<String, String>
     *     "updateDisableIds"
     *     "deleteIds"
     *
     * @param objectList
     * @return
     */
//    Map<String, String> checkDeleteMenuButtonByList(List<MenuButton> objectList);

    /**
     * 根据菜单ID-删除菜单按钮
     * 创建人：陈刚
     * 创建时间：2018-08-01
     */
    void deleteMenuButtonByMenuId(String menuId) throws Exception;//@

    /**
     * 修改禁用属性(isdisable)
     * 根据菜单ID-修改菜单按钮
     * 创建人：陈刚
     * 创建时间：2018-08-01
     */
//    void updateDisableByMenuId(String menuId);

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-03
     * @param object
     * @return
     */
//    String checkColumnByAdd(MenuButton object);
    /**
     * 创建人：陈刚
     * 创建时间：2018-08-03
     * @param object
     * @return
     */
//    String checkColumnByEdit(MenuButton object);

    /**
     * 菜单ID-按钮名称是否相同
     *
     * @param menuId  (不可为空)
     * @param id      (允许为空)-(添加时is null, 修改时 is not null)
     * @param name    (不可为空)
     * @return
     *     true : 菜单名称存在名称相同
     *     false: 菜单名称不存在名称相同(默认值)
     */
//    boolean isExistByName(String menuId, String id, String name);

    /**
     * 菜单ID-按钮属性值是否相同
     *
     * @param menuId  (不可为空)
     * @param id      (允许为空)-(添加时is null, 修改时 is not null)
     * @param nameEn  (不可为空)-按钮英文名称
     * @return
     *     true : 菜单名称存在名称相同
     *     false: 菜单名称不存在名称相同(默认值)
     */
//    boolean isExistByNameEn(String menuId, String id, String nameEn);
//    Integer findMaxSerialNumber(String menuId);
//    MenuButton object2objectDB(MenuButton object, MenuButton objectDB);

    /**
     * 获取按钮ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-09-14
     *
     * @param objectList
     * @return
     */
//    String findButtonIdsByMenuButtonList(List<MenuButton> objectList);

    ResultModel addMeunButton(PageData pageData) throws Exception;

    ResultModel updateMeunButton(PageData pageData) throws Exception;

    ResultModel updateDisableMeunButton(PageData pageData) throws Exception;

    ResultModel deleteMeunButtons(PageData pageData) throws Exception;

    ResultModel listPageMenuButtons(PageData pd, Pagination pg) throws Exception;

    void exportExcelMenuButtons(PageData pd, Pagination pg) throws Exception;

    ResultModel initMenuButtons(PageData pageData) throws Exception;
}



