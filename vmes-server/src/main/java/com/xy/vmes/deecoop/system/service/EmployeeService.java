package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Employee;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_employee:员工管理 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-08-02
*/
public interface EmployeeService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    void save(Employee employee) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    void update(Employee employee) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-02
     */
    void updateAll(Employee employee) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    Employee selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    List<Employee> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    List<Employee> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-08-02
    */
    List<Employee> selectByColumnMap(Map columnMap) throws Exception;


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-02
     */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-02
     */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-02
     */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;
    List<Map> getDataListPage(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-02
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * 创建人：刘威
     * 创建时间：2018-08-06
     */
    List<Map> selectEmployeeAndUserById(PageData pd) throws Exception;//@

    Employee findEmployee(PageData object);//@
    List<Employee> findEmployeeList(PageData object);
    Employee findEmployeeById(String id);//@

    //boolean isExistByMobile(String id, String mobile);//@
    boolean isExistByMobile(String id, String mobile, String companyId);
    boolean isExistByCode(String id, String code, String companyId);//@

    ResultModel addEmployeeAndUser(PageData pd) throws Exception;


    ResultModel updateEmployeeAndUser(PageData pd) throws Exception;

    ResultModel updateDisableEmployee(PageData pageData) throws Exception;

    ResultModel deleteEmployees(PageData pd)throws Exception;
    ResultModel deleteEmployeeByPost(PageData pd)throws Exception;

    ResultModel updateEmployeePostState(PageData pd)throws Exception;

    ResultModel updateForChangeEmployeePost(PageData pd) throws Exception;

    ResultModel addEmployToUser(PageData pd) throws Exception;

    ResultModel addEmployeePluralityPost(PageData pageData)throws Exception;

    ResultModel addEmployeeMainPost(PageData pageData)throws Exception;

    ResultModel listPageEmployees(PageData pd)throws Exception;

    void exportExcelEmployees(PageData pd, Pagination pg)throws Exception;

    ResultModel selectEmployeeAndUserById(String employPostId) throws Exception;

    ResultModel importExcelEmployee(MultipartFile file) throws Exception;
    //员工Excel导入(简版)
    ResultModel importExcelEmployeeBySimple(MultipartFile file) throws Exception;
}



