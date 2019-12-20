package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.deecoop.system.service.EmployeeService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
* 说明：vmes_employee:员工管理Controller
* @author 刘威 自动生成
* @date 2018-08-02
*/
@RestController
@Slf4j
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @GetMapping("/system/employee/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################employee/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Employee employee = employeeService.selectById(id);
        model.putResult(employee);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @PostMapping("/system/employee/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################employee/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Employee employee = (Employee)HttpUtils.pageData2Entity(pd, new Employee());
        employeeService.save(employee);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @PostMapping("/system/employee/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################employee/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Employee employee = (Employee)HttpUtils.pageData2Entity(pd, new Employee());
        employeeService.update(employee);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @GetMapping("/system/employee/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################employee/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        employeeService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @PostMapping("/system/employee/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################employee/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Employee> employeeList = employeeService.dataListPage(pd,pg);
        model.putResult(employeeList);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @GetMapping("/system/employee/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################employee/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Employee> employeeList = employeeService.dataList(pd);
        model.putResult(employeeList);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-08-02
    */
    @GetMapping("/system/employee/excelExport")
    public void excelExport()  throws Exception {

        logger.info("################employee/excelExport 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();

        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
            @Override
            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
                PageData pd = HttpUtils.parsePageData();
                List<LinkedHashMap> titles = employeeService.findColumnList();
                request.setAttribute("titles", titles.get(0));
                List<Map> varList = employeeService.findDataList(pd);
                    request.setAttribute("varList", varList);
                }
        });
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/excelExport 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * @author 刘威 新增员工信息同时需要根据情况新增用户信息
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/addEmployeeAndUser")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addEmployeeAndUser()  throws Exception {
        logger.info("################employee/addEmployeeAndUser 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.addEmployeeAndUser(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################employee/addEmployeeAndUser 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 修改员工信息同时修改或新增用户信息（手机号，邮箱，角色）
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/updateEmployeeAndUser")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateEmployeeAndUser()  throws Exception {
        logger.info("################employee/updateEmployeeAndUser 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.updateEmployeeAndUser(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/updateEmployeeAndUser 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/employee/updateDisableEmployee")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisableEmployee()  throws Exception {
        logger.info("################employee/updateDisableEmployee 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = employeeService.updateDisableEmployee(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/updateDisableEmployee 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威  禁用员工信息包含主岗兼岗，同时禁用员工账号（支持批量删除，不支持物理删除）
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/deleteEmployees")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteEmployees()  throws Exception {
        logger.info("################employee/deleteEmployees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.deleteEmployees(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/deleteEmployees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 删除按钮-删除员工岗位
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/system/employee/deleteEmployeeByPost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteEmployeeByPost() throws Exception {
        logger.info("################employee/deleteEmployeeByPost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.deleteEmployeeByPost(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/deleteEmployeeByPost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威  单独启用禁用员工信息包含主岗兼岗，同时禁用员工账号
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/updateEmployeePostState")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateEmployeePostState()  throws Exception {

        logger.info("################employee/updateEmployeePostState 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.updateEmployeePostState(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/updateEmployeePostState 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * 变更员工岗位（只变更主岗，先禁用后新增）（支持批量操作）
     * @author 刘威
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/updateForChangeEmployeePost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateForChangeEmployeePost()  throws Exception {

        logger.info("################employee/updateForChangeEmployeePost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.updateForChangeEmployeePost(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/updateForChangeEmployeePost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     *  开通员工用户账号（支持批量操作）
     * @author 刘威
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/addEmployToUser")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addEmployToUser()  throws Exception {
//        employRoles ="[{\"employId\":\"3\",\"roleId\":\"1\"}," +
//                "{\"employId\":\"3\",\"roleId\":1532599975000}," +
//                "{\"employId\":\"3\",\"roleId\":1532601003000}," +
//                "{\"employId\":\"3\",\"roleId\":1532600923000}," +
//                "{\"employId\":\"3\",\"roleId\":1532600802000}," +
//                "{\"employId\":\"3\",\"roleId\":1532601034000}]";

        logger.info("################employee/addEmployToUser 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.addEmployToUser(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/addEmployToUser 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * 设置员工兼岗（支持批量操作）
     * @author 刘威
     * @date 2018-08-02
     */
    @PostMapping("/system/employee/addEmployeePluralityPost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addEmployeePluralityPost()  throws Exception {
        logger.info("################employee/addEmployeePluralityPost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = employeeService.addEmployeePluralityPost(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/addEmployeePluralityPost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 1. 员工id查询(vmes_employ_post)主岗-表对象
     * 2. 如果存在(员工id, old岗位id)设置禁用
     * 3. 插入数据(员工id, new岗位id)设置主岗
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/system/employee/addEmployeeMainPost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addEmployeeMainPost()  throws Exception {
        logger.info("################employee/addEmployeeMainPost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = employeeService.addEmployeeMainPost(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/addEmployeeMainPost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 员工信息列表查询分页
     * @date 2018-07-26
     */
    @PostMapping("/system/employee/listPageEmployees")
    public ResultModel listPageEmployees()  throws Exception {

        logger.info("################employee/listPageEmployees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = employeeService.listPageEmployees(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/listPageEmployees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
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
    @PostMapping("/system/employee/exportExcelEmployees")
    public void exportExcelEmployees() throws Exception {
        logger.info("################employee/exportExcelEmployees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        employeeService.exportExcelEmployees(pd,pg);

        Long endTime = System.currentTimeMillis();
        logger.info("################employee/exportExcelEmployees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-07-26
     */    @GetMapping("/system/employee/exportExcelUsers")
//    public void exportExcelUsers()  throws Exception {
//
//        logger.info("################employee/exportExcelUsers 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        HttpServletResponse response  = HttpUtils.currentResponse();
//        HttpServletRequest request  = HttpUtils.currentRequest();
//
//        ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {
//            @Override
//            public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
//                PageData pd = HttpUtils.parsePageData();
//                List<LinkedHashMap> titles = employeeService.getColumnList();
//                request.setAttribute("titles", titles.get(0));
//                List<Map> varList = employeeService.getDataList(pd);
//                request.setAttribute("varList", varList);
//            }
//        });
//        Long endTime = System.currentTimeMillis();
//        logger.info("################employee/exportExcelUsers 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//    }
//



    /**
     * @author 刘威 通过ID查询
     * @date 2018-07-26
     */
    @PostMapping("/system/employee/selectEmployeeAndUserById/{employPostId}")
    public ResultModel selectEmployeeAndUserById(@PathVariable("employPostId") String employPostId)  throws Exception {

        logger.info("################employee/selectEmployeeAndUserById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = employeeService.selectEmployeeAndUserById(employPostId);
        Long endTime = System.currentTimeMillis();
        logger.info("################employee/selectEmployeeAndUserById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * Excel导入
     *
     * @author 陈刚 自动创建，可以修改
     * @date 2018-11-20
     */
    @PostMapping("/system/employee/importExcelEmployee")
    public ResultModel importExcelEmployee(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/system/employee/importExcelEmployee 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = employeeService.importExcelEmployee(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/employee/importExcelEmployee 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 员工Excel导入(简版)
     *
     * @author 陈刚
     * @date 2018-11-20
     */
    @PostMapping("/system/employee/importExcelEmployeeBySimple")
    public ResultModel importExcelEmployeeBySimple(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/system/employee/importExcelEmployeeBySimple 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = employeeService.importExcelEmployeeBySimple(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/employee/importExcelEmployeeBySimple 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     * 修改员工合同到期日期
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/system/employee/updateEmployeeByContractDate")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateEmployeeByContractDate() throws Exception {
        logger.info("################/system/employee/updateEmployeeByContractDate 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();

        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();

        String employeeId = pd.getString("employeeId");
        if (employeeId == null || employeeId.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("员工id为空或空字符串！");
            return model;
        }
        String newContractDateStr = pd.getString("newContractDateStr");
        if (newContractDateStr == null || newContractDateStr.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("合同到期日期为必填项不可为空！");
            return model;
        }

        String remark = new String();
        if (pd.getString("remark") != null && pd.getString("remark").trim().length() > 0) {
            remark = pd.getString("remark").trim();
        }

        //原(合同到期日期)
        Long contractDateLong = null;
        String contractDateStr = pd.getString("contractDateStr");
        if (contractDateStr != null && contractDateStr.trim().length() > 0) {
            Date contractDate = DateFormat.dateString2Date(contractDateStr, DateFormat.DEFAULT_DATE_FORMAT);
            if (contractDate != null) {
                contractDateLong = Long.valueOf(contractDate.getTime());
            }
        }

        //新(合同到期日期)
        Date newContractDate = null;
        Long newContractDateLong = null;
        if (newContractDateStr != null && newContractDateStr.trim().length() > 0) {
            newContractDate = DateFormat.dateString2Date(newContractDateStr, DateFormat.DEFAULT_DATE_FORMAT);
            if (newContractDate != null) {
                newContractDateLong = Long.valueOf(newContractDate.getTime());
            }
        }

//        if (contractDateLong != null && newContractDateLong != null
//            && (newContractDateLong.longValue() <= contractDateLong.longValue())
//        ) {
//            String msgTemp = "合同到期日期：{0} 上期合同到期日期：{1} 合同到期日期必须大于上期合同到期日期！";
//            String msg_str = MessageFormat.format(msgTemp, newContractDateStr, contractDateStr);
//
//            model.putCode(1);
//            model.putMsg(msg_str);
//            return model;
//        }

        Employee editEmployee = new Employee();
        editEmployee.setId(employeeId);
        editEmployee.setRemark(remark);
        //合同到期日期(yyyy-mm-dd)
        if (newContractDate != null) {
            editEmployee.setContractDate(newContractDate);
        }
        employeeService.update(editEmployee);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/employee/updateEmployeeByContractDate 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}

