package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.RoleService;
import com.xy.vmes.entity.*;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import com.yvan.template.ExcelAjaxTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
* 说明：Controller
* @author 陈刚 自动生成
* @date 2018-07-30
*/
@RestController
@Slf4j
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @GetMapping("/system/role/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################role/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Role role = roleService.selectById(id);
        model.putResult(role);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @PostMapping("/system/role/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################role/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Role role = (Role)HttpUtils.pageData2Entity(pd, new Role());
        roleService.save(role);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @PostMapping("/system/role/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################role/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Role role = (Role)HttpUtils.pageData2Entity(pd, new Role());
        roleService.update(role);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @GetMapping("/system/role/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################role/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        roleService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @PostMapping("/system/role/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################role/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Role> roleList = roleService.dataListPage(pd,pg);
        model.putResult(roleList);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @PostMapping("/system/role/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################role/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Role> roleList = roleService.dataList(pd);
        model.putResult(roleList);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-30
    */
    @GetMapping("/system/role/excelExport")
    public void excelExport()  throws Exception {

        logger.info("################role/excelExport 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();

        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
            @Override
            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
                PageData pd = HttpUtils.parsePageData();
                List<LinkedHashMap> titles = roleService.findColumnList();
                request.setAttribute("titles", titles.get(0));
                //List<Map> varList = roleService.findDataList(pd);
                    //request.setAttribute("varList", varList);
                }
        });
        Long endTime = System.currentTimeMillis();
        logger.info("################role/excelExport 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**添加角色
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/listPageRoles")
    public ResultModel listPageRoles() throws Exception{
        logger.info("################role/listPageRoles 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = roleService.listPageRoles(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/listPageRoles 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
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
    @PostMapping("/system/role/exportExcelRoles")
    public void exportExcelRoles() throws Exception {
        logger.info("################role/exportExcelRoles 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        roleService.exportExcelRoles(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/exportExcelRoles 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");

    }

    /**添加角色
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/addRole")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addRole() throws Exception {
        logger.info("################role/addRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.addRole(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/addRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改角色
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/updateRole")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateRole() throws Exception {
        logger.info("################role/updateRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.updateRole(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/updateRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改角色(禁用)状态
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/updateDisableRole")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableRole() throws Exception {
        logger.info("################role/updateDisableRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.updateDisableRole(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/updateDisableRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**删除角色
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/deleteRoles")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteRoles() throws Exception {
        logger.info("################role/deleteRoles 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.deleteRoles(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/deleteRoles 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**角色批量绑定用户
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/saveRoleUsers")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel saveRoleUsers() throws Exception {
        logger.info("################role/saveRoleUsers 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.saveRoleUsers(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/saveRoleUsers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }
    /**角色批量绑定菜单
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/saveRoleMeuns")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel saveRoleMeuns() throws Exception {
        logger.info("################role/saveRoleMeuns 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.saveRoleMeuns(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/saveRoleMeuns 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 菜单树
     * 1. 获取全部菜单表-生成菜单树结构体
     * 2. 根据角色ID-角色ID绑定的菜单选中状态-查询角色菜单表(vmes_role_menu)
     *
     * @author 陈刚
     * @date 2018-08-23
     */
    @PostMapping("/system/role/treeRoleMeunsAll")
    public ResultModel treeRoleMeunsAll() throws Exception{
        logger.info("################role/treeRoleMeunsAll 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //A. 获取全部菜单树-查询条件-(企业管理员账号)
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.treeRoleMeunsAll(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/treeRoleMeunsAll 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**角色对应(已选的)菜单树
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/treeRoleMeunsSelected")
    public ResultModel treeRoleMeunsSelected() throws Exception {
        logger.info("################role/treeRoleMeunsSelected 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.treeRoleMeunsSelected(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/treeRoleMeunsSelected 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     *角色对应菜单的数据权限
     * @author 刘威
     * @date 2018-07-30
     */
    @PostMapping("/system/role/getRoleMeunsDataType")
    public ResultModel getRoleMeunsDataType() throws Exception {
        logger.info("################role/getRoleMeunsDataType 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.getRoleMeunsDataType(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/getRoleMeunsDataType 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     *保存角色对应菜单的数据权限
     * @author 刘威
     * @date 2018-07-30
     */
    @PostMapping("/system/role/saveRoleMeunsData")
    public ResultModel saveRoleMeunsData() throws Exception {
        logger.info("################role/saveRoleMeunsData 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.saveRoleMeunsData(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/saveRoleMeunsData 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**角色对应全部菜单按钮列表
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/listRoleMeunsButtonsAll")
    public ResultModel listRoleMeunsButtonsAll() throws Exception {
        logger.info("################role/listRoleMeunsButtonsAll 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.listRoleMeunsButtonsAll(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/listRoleMeunsButtonsAll 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }
    /**角色对应(已选的)菜单按钮列表
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/saveRoleMeunsButtons")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel saveRoleMeunsButtons() throws Exception {
        logger.info("################role/saveRoleMeunsButtons 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.saveRoleMeunsButtons(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/saveRoleMeunsButtons 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 陈刚 自动创建，禁止修改
     * @date 2018-07-30
     */
    @PostMapping("/system/role/dataListRoles")
    public ResultModel dataListRoles()  throws Exception {

        logger.info("################role/dataListRoles 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Map> roleList = roleService.findDataList(pd);
        model.putResult(roleList);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/dataListRoles 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * 根据部门id获取全部待选用户
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/listAllUsersByDeptId")
    public ResultModel listAllUsersByDeptId() throws Exception{
        logger.info("################role/listAllUsersByDeptId 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.listAllUsersByDeptId(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/listAllUsersByDeptId 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 获取当前角色id已经绑定用户List
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/listUsersByRole")
    public ResultModel listUsersByRole() throws Exception{
        logger.info("################role/listUsersByRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.listUsersByRole(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/listUsersByRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 获取当前角色id已经绑定用户List
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/findListUserByRole")
    public ResultModel findListUserByRole() throws Exception {
        logger.info("################role/findListUserByRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = roleService.findListUserByRole(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/findListUserByRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    @PostMapping("/system/role/listUserByRole")
    public ResultModel listUserByRole() throws Exception {
        logger.info("################role/listUserByRole 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = roleService.listUserByRole(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/listUserByRole 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 获取当前角色id已经绑定用户List
     *
     * @author 陈刚
     * @date 2018-07-30
     */
    @PostMapping("/system/role/getRoles")
    public ResultModel getRoles() throws Exception{
        logger.info("################role/getRoles 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = roleService.getRoles(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/getRoles 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/role/addRoleByName")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addRoleByName() throws Exception {
        logger.info("################role/addRoleByName 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = roleService.addRoleByName(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################role/addRoleByName 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



