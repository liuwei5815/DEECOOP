package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.*;
import com.xy.vmes.deecoop.system.service.MenuButtonService;
import com.yvan.*;
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
* 说明：vmes_menu_button:菜单按钮表Controller
* @author 陈刚 自动生成
* @date 2018-08-03
*/
@RestController
@Slf4j
public class MenuButtonController {
    private Logger logger = LoggerFactory.getLogger(MenuButtonController.class);

    @Autowired
    private MenuButtonService menuButtonService;

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @GetMapping("/system/button/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################button/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        MenuButton menuButton = menuButtonService.selectById(id);
        model.putResult(menuButton);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @PostMapping("/system/button/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################button/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        MenuButton menuButton = (MenuButton)HttpUtils.pageData2Entity(pd, new MenuButton());
        menuButtonService.save(menuButton);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @PostMapping("/system/button/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################button/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        MenuButton menuButton = (MenuButton)HttpUtils.pageData2Entity(pd, new MenuButton());
        menuButtonService.update(menuButton);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @GetMapping("/system/button/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################button/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        menuButtonService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @PostMapping("/system/button/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################button/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<MenuButton> menuButtonList = menuButtonService.dataListPage(pd,pg);
        model.putResult(menuButtonList);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @PostMapping("/system/button/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################button/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<MenuButton> menuButtonList = menuButtonService.dataList(pd);
        model.putResult(menuButtonList);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-08-03
    */
    @GetMapping("/system/button/excelExport")
    public void excelExport()  throws Exception {

        logger.info("################button/excelExport 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();

        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
            @Override
            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
                PageData pd = HttpUtils.parsePageData();
                List<LinkedHashMap> titles = menuButtonService.findColumnList();
                request.setAttribute("titles", titles.get(0));
                //List<Map> varList = menuButtonService.findDataList(pd);
                    //request.setAttribute("varList", varList);
                }
        });
        Long endTime = System.currentTimeMillis();
        logger.info("################button/excelExport 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


    /**
     * 新增按钮
     *
     * @author 陈刚
     * @date 2018-08-03
     */
    @PostMapping("/system/button/addMeunButton")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addMeunButton() throws Exception {
        logger.info("################button/addMeunButton 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuButtonService.addMeunButton(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/addMeunButton 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改按钮
     *
     * @author 陈刚
     * @date 2018-08-03
     */
    @PostMapping("/system/button/updateMeunButton")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateMeunButton() throws Exception {
        logger.info("################button/updateMeunButton 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuButtonService.updateMeunButton(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/updateMeunButton 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改按钮(禁用)状态
     *
     * @author 陈刚
     * @date 2018-08-03
     */
    @PostMapping("/system/button/updateDisableMeunButton")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableMeunButton() throws Exception {
        logger.info("################button/updateDisableMeunButton 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuButtonService.updateDisableMeunButton(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/updateDisableMeunButton 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除按钮
     *
     * @author 陈刚
     * @date 2018-08-03
     */
    @PostMapping("/system/button/deleteMeunButtons")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteMeunButtons() throws Exception {
        logger.info("################button/deleteMeunButtons 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuButtonService.deleteMeunButtons(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/deleteMeunButtons 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 菜单按钮列表分页
     *
     * @author 陈刚
     * @date 2018-08-08
     */
    @PostMapping("/system/button/listPageMenuButtons")
    public ResultModel listPageMenuButtons() throws Exception {
        logger.info("################button/listPageMenuButtons 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = menuButtonService.listPageMenuButtons(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/listPageMenuButtons 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
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
    @PostMapping("/system/button/exportExcelMenuButtons")
    public void exportExcelMenuButtons() throws Exception {
        logger.info("################button/exportExcelMenuButtons 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        //1. 获取Excel导出数据查询条件
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        menuButtonService.exportExcelMenuButtons(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/exportExcelMenuButtons 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
     * 根据(菜单Key,登录用户角色id)获取菜单按钮List
     *
     * @author 陈刚
     * @date 2018-09-14
     */
    @PostMapping("/system/button/initMenuButtons")
    public ResultModel initMenuButtons() throws Exception {
        logger.info("################button/initMenuButtons 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = menuButtonService.initMenuButtons(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################button/initMenuButtons 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }
}

