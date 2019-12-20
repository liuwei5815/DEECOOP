package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.DeptExcelEntity;
import com.xy.vmes.entity.TreeEntity;
import com.yvan.PageData;
import com.yvan.Tree;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_department:系统部门表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-07-23
*/
public interface DepartmentService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    void save(Department department) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    void update(Department department) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-23
     */
    void updateAll(Department department) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    Department selectById(String id) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    List<Department> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    List<Department> dataList(PageData pd) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    List<Map> findDataList(PageData pd) throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    Map<String, String> getDeptKeyNameMap();
    Map<String, String> getDeptNameKeyMap();
//    void createDeptMap();
//    void implementDeptMapByParentID(String parentId);
    void implementDeptMapByCompanyId(String companyId);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-23
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    List<LinkedHashMap> getColumnList() throws Exception;//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

    /**
     * 批量修改组织架构信息为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-07-27
     */
//    void updateDisableByIds(String[] ids) throws Exception;

    /**
     * 生成部门编码
     *
     * 创建人：陈刚
     * 创建时间：2018-07-27
     *
     * @param companyID  公司ID-组织架构ID
     * @return
     */
    String createCoder(String companyID);

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-27
     * @param object
     * @return
     */
//    String checkColumnByAdd(Department object);

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-27
     * @param object
     * @return
     */
//    String checkColumnByEdit(Department object);

    /**
     * 组织名称同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param name  (不可为空)
     * @return
     *     true : 组织名称存在名称相同
     *     false: 组织名称不存在名称相同(默认值)
     */
//    boolean isExistByName(String pid, String id, String name);

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-18
     */
    Department findDepartment(PageData object);//@
    Department findDepartmentById(String id);//@
    Department findDepartmentByRoot();//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-18
     */
    List<Department> findDepartmentList(PageData object);//@
    List<Department> findDepartmentListByPid(String pid);//@
    Integer findMaxSerialNumber(String pid);//@

    /**
     * 根据部门对象<Department>当前部门节点下面所有节点生成树形结构
     * 1. <Department>对象为空时-生成整棵树-(pid:=root)开始-部门级别(0-5)
     * 2. <Department>对象is not null
     * (允许为空)Department.id    当前部门id
     * (允许为空)Department.code  当前部门编码
     * (允许为空)Department.name  当前部门名称
     * (允许为空)Department.id1   当前部门(id_1)-公司id
     * (允许为空)Department.layer 当前部门级别
     * 3. (id,id1,code,name,layer) 不可同时为空
     * pid is not null pid对应的部门+pid下面所有子部门
     * pid is null (code,layer) is not null or (name,layer) is not null
     * <p>
     * <p>
     * <p>
     * 创建人：陈刚
     * 创建时间：2018-07-18
     *
     * @param detp 允许为空-<Department>对象(vmes_department:系统部门表)
     * @return
     */
//    Tree<Department> findTree(Department detp);

    /**
     * 获取部门id字符串-(','分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-07-19
     *
     */
    String findDeptidByDeptList(List<Department> objectList);//@

    /**
     * 获取部门最大级别-遍历部门List<Department>
     *
     * 创建人：陈刚
     * 创建时间：2018-07-24
     * @param objectList
     * @return
     */
//    Integer findMaxLayerByDeptList(List<Department> objectList);

    /**
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     * @param objectList
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
//    Map<String, String> findLongNameCode(List<Department> objectList);
    /**
     * 根据父节点<Department>对象-(id_0,id_1,...,id_5)
     * 查询获得组织架构路径List<Department>
     *
     * @param paterObject
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
//    List<Department> findPathListByPater(Department paterObject);

    /**
     * 根据父pid父节点<Department>对象-(id_0,id_1,...,id_5)-查询获得组织架构路径List<Department>
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     *
     * @param pid
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
//    Map<String, String> findLongNameCodeByPid(String pid);

    /**
     * 根据父节点<Department>对象-(id_0,id_1,...,id_5)-查询获得组织架构路径List<Department>
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     *
     * @param Pater
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
//    Map<String, String> findLongNameCodeByPater(Department Pater);
    Department paterObject2ObjectDB(Department paterObject, Department objectDB);//@
    Department id2DepartmentByLayer(String id, Integer layer, Department objectDB);//@
//    Department clearDepartmentByPath(Department objectDB);
//    Department object2objectDB(Department object, Department objectDB);

    /**
     * check部门列表List<Department>是否允许删除
     * 当前组织节点下是否含有子节点
     * 当前节点下是否含有岗位
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param ids
     * @return
     */
//    String checkDeleteDeptByIds(String ids);


    /**
     * 创建人：刘威
     * 创建时间：2018-08-01
     */
    List<TreeEntity> getTreeList(PageData pd)throws Exception;

    /**
     * 获取部门级联查询ID
     *
     * @param id     部门id
     * @param layer  部门级别
     * @param prefix 前缀
     * @return
     */
    String findDeptidById(String id, Integer layer, String prefix);//@

    /**
     * 递归调用-添加(系统组织架构)vmes_department
     *   企业id  --> id_1
     *   一级部门 --> id_2
     *   二级部门 --> id_3
     *   三级部门 --> id_4
     *
     * @param cuser     创建人id
     * @param parent    父节点对象
     * @param excelEntity  组织类型
     * @param nameList  部门名称
     * @param count     递归执行次数
     */
    String addBusinessByNameList(String cuser,Department parent,DeptExcelEntity excelEntity,List<String> nameList,int count);//@

    ResultModel treeDepartments(PageData pd) throws Exception;

    ResultModel addDepartment(PageData pageData) throws Exception;

    ResultModel updateDepartment(PageData pageData) throws Exception;

    ResultModel updateDisableDept(PageData pageData) throws Exception;

    ResultModel deleteDepartments(PageData pageData) throws Exception;
    ResultModel deleteDepartment(PageData pageData) throws Exception;

    ResultModel listPageDepartments(PageData pd, Pagination pg) throws Exception;

    void exportExcelDepartments(PageData pd, Pagination pg)throws Exception;

    ResultModel importExcelDepartments(MultipartFile file)throws Exception;

//    List<Map<String, Object>> findDepartmentListByDeptPathName(String companyId);
}



