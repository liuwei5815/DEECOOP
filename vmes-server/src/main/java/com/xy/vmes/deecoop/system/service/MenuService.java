package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Menu;
import com.xy.vmes.exception.ApplicationException;
import com.yvan.PageData;
import com.yvan.Tree;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_menu:系统功能菜单 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-07-31
*/
public interface MenuService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    void save(Menu menu) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    void update(Menu menu) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    void updateAll(Menu menu) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    Menu selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<Menu> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<Menu> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    List<Map> findDataList(PageData pd) throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;


    /**
     * 查询菜单表(vmes_menu)
     * @param object
     * @return
     */
    List<Menu> findMenuList(PageData object);//@
    /**
     * 查询菜单表(vmes_menu)
     * @param object
     * @return
     */
    Menu findMenu(PageData object);//@
    /**
     * 根据ID-查询菜单表(vmes_menu)
     * @param id
     * @return
     */
    Menu findMenuById(String id);//@


    /**
     * 获取菜单id字符串-(','分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-07-19
     *
     */
    String findMenuidByMenuList(List<Menu> objectList);//@

    /**
     * 获取菜单最大级别-遍历菜单List<Menu>
     *
     * 创建人：陈刚
     * 创建时间：2018-07-24
     * @param objectList
     * @return
     */
    Integer findMaxLayerByMenuList(List<Menu> objectList);//@


    ResultModel listPageMenus(PageData pd,Pagination pg) throws Exception;

    void exportExcelMenus(PageData pd,Pagination pg) throws Exception;

    ResultModel addMenu(PageData pageData) throws Exception;

    ResultModel updateMenu(PageData pageData) throws Exception;

    ResultModel updateDisableMenu(PageData pageData) throws Exception;

    ResultModel deleteMenus(PageData pageData) throws Exception;

    ResultModel importExcelMenus(MultipartFile file) throws Exception;

    ////////////////////////////////////////////////////////////////////////////

    /**
     * 验证当前用户(用户id,角色id) 是否配置有角色，角色菜单
     * 1. 验证当前用户，角色id是否为空
     * 2. 验证当前用户角色id，是否关联有菜单
     *
     * @param userId  当前用户id
     * @param roleId  当前用户角色id
     * @throws ApplicationException
     */
    void checkMeunByUserRole(String userId, String roleId) throws ApplicationException;//@
    ResultModel treeMeuns(PageData pageData) throws Exception;

    List<Map<String, Object>> listMenuKeyByApp(String roleId, String appMenuId) throws Exception;//@
}



