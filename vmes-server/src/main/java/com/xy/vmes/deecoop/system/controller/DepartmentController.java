package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.deecoop.system.service.PostService;
import com.xy.vmes.entity.*;
import com.yvan.*;
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
import java.util.*;


/**
* 说明：Controller
* @author 陈刚 自动生成
* @date 2018-07-23
*/
@RestController
@Slf4j
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PostService postService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private ColumnService columnService;


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @GetMapping("/system/department/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Department department = departmentService.selectById(id);
        model.putResult(department);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @PostMapping("/system/department/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Department department = (Department)HttpUtils.pageData2Entity(pd, new Department());
        departmentService.save(department);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @PostMapping("/system/department/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Department department = (Department)HttpUtils.pageData2Entity(pd, new Department());
        departmentService.update(department);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @GetMapping("/system/department/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        departmentService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @PostMapping("/system/department/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Department> departmentList = departmentService.dataListPage(pd,pg);
        model.putResult(departmentList);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @PostMapping("/system/department/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Department> departmentList = departmentService.dataList(pd);
        model.putResult(departmentList);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2018-07-23
    */
    @GetMapping("/system/department/excelExport")
    public void excelExport()  throws Exception {

        logger.info("################department/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();

        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
            @Override
            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
                PageData pd = HttpUtils.parsePageData();
                List<LinkedHashMap> titles = departmentService.findColumnList();
                request.setAttribute("titles", titles.get(0));
                List<Map> varList = departmentService.findDataList(pd);
                    //request.setAttribute("varList", varList);
                }
        });
        Long endTime = System.currentTimeMillis();
        logger.info("################department/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/



    /**
     * @author 刘威
     * @date 2018-07-31
     */
    @PostMapping("/system/department/treeDepartments")
    public ResultModel treeDepartments()  throws Exception {
        logger.info("################/department/treeDepartments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();

        ResultModel model = departmentService.treeDepartments(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/department/treeDepartments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


//    /**
//     * 获得部门树形结构
//     * 当前部门节点下面所有子节点树形结构
//     *
//     * 创建人：陈刚
//     * 创建时间：2018-07-20
//     */
//    @GetMapping("/department/treeDepartments")
//    public String treeDepartments() {
//        PageData mapObj = HttpUtils.parsePageData();
//
//        //递归调用获得(当前部门+当前部门下所有子部门)List结构体
//        //Department detp = new Department();
//        //detp.setId1("1");
//        //detp.setName("公司1");
//        //detp.setLayer(Integer.valueOf(1));
//        //detp = null;
//        //Tree<Department> treeObj = departmentService.findTree(detp);
//
//        return null;
//    }

    /**新增组织架构(组织类型:部门)
     * (organize_type:组织类型(1:公司 2:部门))
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/department/addDepartment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addDepartment() throws Exception {
        logger.info("################department/addDepartment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = departmentService.addDepartment(pageData);

        Long endTime = System.currentTimeMillis();
        logger.info("################/department/addDepartment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改组织架构(组织类型:部门)
     * (organize_type:组织类型(1:公司 2:部门))
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/department/updateDepartment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDepartment() throws Exception {
        logger.info("################department/updateDepartment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = departmentService.updateDepartment(pageData);

        Long endTime = System.currentTimeMillis();
        logger.info("################/department/updateDepartment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改组织架构(禁用)状态
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/department/updateDisableDept")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableDept() throws Exception {
        logger.info("################department/updateDisableDept 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = departmentService.updateDisableDept(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/department/updateDisableDept 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**删除组织架构(组织类型:部门)
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/department/deleteDepartments")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteDepartments() throws Exception {
        logger.info("################department/deleteDepartments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = departmentService.deleteDepartments(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/department/deleteDepartments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**删除组织架构(组织类型:部门)
     *
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/department/deleteDepartment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteDepartment() throws Exception {
        logger.info("################/system/department/deleteDepartment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = departmentService.deleteDepartment(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/department/deleteDepartment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

//    /**组织管理分页查询List
//     *
//     * @author 陈刚
//     * @date 2018-08-08
//     */
//    @PostMapping("/department/listPageDepartments")
//    public ResultModel listPageDepartments() throws Exception {
//        ResultModel model = new ResultModel();
//        Map<String, Object> mapObj = new HashMap<String, Object>();
//
//
//        //1. 查询遍历List列表
//        LinkedHashMap<String, String> titlesLinkedMap = new LinkedHashMap<String, String>();
//        List<String> titlesHideList = new ArrayList<String>();
//        Map<String, String> varModelMap = new HashMap<String, String>();
//        List<LinkedHashMap<String, String>> titleList = departmentService.getColumnList();
//        if(titleList != null && titleList.size() >0 ){
//            LinkedHashMap<String, String> titlesMap = titleList.get(0);
//            for (Map.Entry<String, String> entry : titlesMap.entrySet()) {
//                if(entry.getKey().indexOf("_hide") != -1){
//                    titlesLinkedMap.put(entry.getKey().replace("_hide",""), entry.getValue());
//                    titlesHideList.add(entry.getKey().replace("_hide",""));
//                    varModelMap.put(entry.getKey().replace("_hide",""), "");
//                }else{
//                    titlesLinkedMap.put(entry.getKey(), entry.getValue());
//                    varModelMap.put(entry.getKey(), "");
//                }
//            }
//        }
//        mapObj.put("hideTitles",titlesHideList);
//        mapObj.put("titles",titlesLinkedMap);
//
//        //2. 分页查询数据List
//        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
//        PageData pd = HttpUtils.parsePageData();
//        Pagination pg = HttpUtils.parsePagination(pd);
//        List<Map<String, Object>> varList = departmentService.getDataListPage(pd, pg);
//        if(varList != null && varList.size() > 0){
//            for(int i=0; i < varList.size(); i++){
//                Map<String, Object> map = varList.get(i);
//                Map<String, String> varMap = new HashMap<String, String>();
//                varMap.putAll(varModelMap);
//                for (Map.Entry<String, String> entry : varMap.entrySet()) {
//                    varMap.put(entry.getKey(), map.get(entry.getKey()) != null ? map.get(entry.getKey()).toString() : "");
//                }
//                varMapList.add(varMap);
//            }
//        }
//        mapObj.put("varList",varMapList);
//        mapObj.put("pageData", YvanUtil.toJson(pg));
//
//        model.putResult(mapObj);
//        return model;
//    }


    /**
     * @author 刘威 自动创建，可以修改
     * @date 2018-08-23
     */
    @PostMapping("/system/department/listPageDepartments")
    public ResultModel listPageDepartments()  throws Exception {

        logger.info("################department/listPageDepartments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = departmentService.listPageDepartments(pd,pg);
        Long endTime = System.currentTimeMillis();

        logger.info("################department/listPageDepartments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
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
    @PostMapping("/system/department/exportExcelDepartments")
    public void exportExcelDepartments() throws Exception {
        logger.info("################department/exportExcelDepartments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        departmentService.exportExcelDepartments(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/exportExcelDepartments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
     * Excel文件导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/system/department/importExcelDepartments")
    public ResultModel importExcelDepartments(@RequestParam(value="excelFile") MultipartFile file) throws Exception {
        logger.info("################department/importExcelDepartments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = departmentService.importExcelDepartments(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################department/importExcelDepartments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }
}



