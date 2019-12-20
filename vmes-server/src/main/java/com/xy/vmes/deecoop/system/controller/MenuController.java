package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.MenuService;
import com.xy.vmes.deecoop.system.service.RoleMenuService;
import com.xy.vmes.deecoop.system.service.UserRoleService;
import com.xy.vmes.deecoop.system.service.UserService;
import com.xy.vmes.entity.Menu;
import com.xy.vmes.entity.RoleMenu;
import com.xy.vmes.entity.User;
import com.yvan.*;
import com.yvan.common.util.Common;
import com.yvan.springmvc.ResultModel;
import com.yvan.template.ExcelAjaxTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

/**
* 说明：vmes_menu:系统功能菜单Controller
* @author 陈刚 自动生成
* @date 2018-08-01
*/
@RestController
@Slf4j
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    RoleMenuService roleMenuService;

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @GetMapping("/system/menu/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################menu/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Menu menu = menuService.selectById(id);
        model.putResult(menu);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @PostMapping("/system/menu/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################menu/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Menu menu = (Menu)HttpUtils.pageData2Entity(pd, new Menu());
        menuService.save(menu);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @PostMapping("/system/menu/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################menu/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Menu menu = (Menu)HttpUtils.pageData2Entity(pd, new Menu());
        menuService.update(menu);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @GetMapping("/system/menu/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################menu/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        menuService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @PostMapping("/system/menu/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################menu/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Menu> menuList = menuService.dataListPage(pd,pg);
        model.putResult(menuList);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @PostMapping("/system/menu/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################menu/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Menu> menuList = menuService.dataList(pd);
        model.putResult(menuList);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-01
    */
    @GetMapping("/system/menu/excelExport")
    public void excelExport()  throws Exception {

        logger.info("################menu/excelExport 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();

        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
            @Override
            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
                PageData pd = HttpUtils.parsePageData();
                List<LinkedHashMap> titles = menuService.findColumnList();
                request.setAttribute("titles", titles.get(0));
                //List<Map> varList = menuService.findDataList(pd);
                    //request.setAttribute("varList", varList);
                }
        });
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/excelExport 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    @Autowired
//    RedisClient redisClient;

    /**
     * 菜单列表分页
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/listPageMenus")
    public ResultModel listPageMenus() throws Exception {
        logger.info("################menu/listPageMenus 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = menuService.listPageMenus(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/listPageMenus 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 根据给定的菜单Url(菜单路由名称)与当前企业管理员角色配置的系统菜单-是否匹配
     * 返回值:
     * isExist:
     *     true:  与当前企业管理员角色配置的系统菜单匹配
     *     false: 与当前企业管理员角色配置的系统菜单不匹配
     * message:
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/findIsExistMenu")
    public ResultModel findIsExistMenu() throws Exception {
        logger.info("################menu/listPageMenus 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();

        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();

        String companyId = pd.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        String menuUrl = pd.getString("menuUrl");
        if (menuUrl == null || menuUrl.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("菜单Url为空或空字符串！");
            return model;
        }
        String menuId = Common.SYS_MENU_MAP.get(menuUrl);
        String menuName = Common.SYS_WAREHOUSE_MENU_MAP.get(menuId);

        try {
            //获取当前企业管理员
            String companyUserId = new String();
            PageData findMap = new PageData();
            findMap.put("companyId", companyId);
            //企业管理员:userType_company:2fb9bbee46ca4ce1913f3a673a7dd68f  数据字典:pid:744f2d88c9f647d0a4d967a714193850
            findMap.put("userType", Common.DICTIONARY_MAP.get("userType_company"));
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<User> userList = userService.findUserList(findMap);
            if (userList != null && userList.size() > 0) {
                companyUserId = userList.get(0).getId();
            }
            if (companyUserId == null || companyUserId.trim().length() == 0) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("您所在的企业无企业管理员，请于管理员联系！");
                return model;
            }

            //获取当前企业管理员-全部角色id(','分隔的字符串)
            String roleIds = userRoleService.findRoleIdsByByUserID(companyUserId);
            if (roleIds == null || roleIds.trim().length() == 0) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("您所在的企业管理员未分配角色，请于管理员联系！");
                return model;
            }

            //获取当前企业管理员-全部菜单
            findMap = new PageData();
            findMap.put("roleId", roleIds);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()).toString());
            List<RoleMenu> roleMenuList = roleMenuService.findRoleMenuList(findMap);

            //获取当前企业管理员-全部系统菜单Map
            Map<String, String> sysMenuMap = new HashMap<String, String>();
            if (roleMenuList != null && roleMenuList.size() > 0) {
                for (RoleMenu roleMenu : roleMenuList) {
                    String sysMenuId = roleMenu.getMenuId();
                    if (menuId != null && menuId.trim().length() > 0) {
                        sysMenuMap.put(sysMenuId, sysMenuId);
                    }
                }
            }

            //判断菜单id 与 获取当前企业管理员角色配置菜单是否匹配
            if (sysMenuMap.get(menuId) != null) {
                model.put("isExist", "true");
            } else {
                String msgTemp = "您所在的企业无({0})模块";
                String message = MessageFormat.format(msgTemp, menuName);

                model.put("isExist", "false");
                model.put("message", message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        logger.info("################menu/listPageMenus 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * Excel导出功能：
     * 1. 勾选指定行导出-(','逗号分隔的id字符串)
     * 2. 按查询条件导出(默认查询方式)
     * 参数说明:
     *   ids          : 业务id字符串-(','分隔的字符串)
     *   queryColumn  : 查询字段(sql where 子句)
     *   showFieldcode: 导出Excel字段Code-显示顺序按照字符串排列顺序-(','分隔的字符串)

     * 注意: 参数(ids,queryColumn)这两个参数是互斥的，(有且有一个参数不为空)
     *
     * @throws Exception
     */
    @GetMapping("/system/menu/exportExcelMenus")
    public void exportExcelMenus() throws Exception {
        logger.info("################menu/exportExcelMenus 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //1. 获取Excel导出数据查询条件
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        menuService.exportExcelMenus(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/exportExcelMenus 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");

    }

    /**
     * 新增菜单
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/addMenu")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addMenu() throws Exception {
        logger.info("################menu/addMenu 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuService.addMenu(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/addMenu 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改菜单
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/updateMenu")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateMenu() throws Exception {
        logger.info("################menu/updateMenu 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuService.updateMenu(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/updateMenu 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改菜单(禁用)状态
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/updateDisableMenu")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableMenu() throws Exception {
        logger.info("################menu/updateDisableMenu 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuService.updateDisableMenu(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/updateDisableMenu 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**删除菜单
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/deleteMenus")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteMenus() throws Exception {
        logger.info("################menu/deleteMenus 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuService.deleteMenus(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/deleteMenus 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * 菜单Excel导入
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    @PostMapping("/system/menu/importExcelMenus")
    public ResultModel importExcelMenus(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################menu/importExcelMenus 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = menuService.importExcelMenus(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/importExcelMenus 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 菜单树
     * 1. 从Redis缓存中获取-当前用户(角色ID)-','逗号分隔的字符串
     * 2. (角色ID字符串)-获取菜单List<Meun>
     *
     * @author 陈刚
     * @date 2018-08-01
     */
    //@GetMapping("/system/menu/treeMeuns")
    @PostMapping("/system/menu/treeMeuns")
    public ResultModel treeMeuns()  throws Exception  {
        logger.info("################menu/treeMeuns 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuService.treeMeuns(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################menu/treeMeuns 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


}



