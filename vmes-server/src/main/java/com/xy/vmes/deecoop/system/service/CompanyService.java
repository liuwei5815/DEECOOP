package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Department;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface CompanyService {
    /**
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param object
     * @return
     */
//    String checkColumnByAdd(Department object);

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param object
     * @return
     */
//    String checkColumnByEdit(Department object);

    /**
     * 企业名称同一层级是否相同
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
     * 企业编码同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param code  (不可为空)
     * @return
     *     true : 组织名称存在名称相同
     *     false: 组织名称不存在名称相同(默认值)
     */
//    boolean isExistByCode(String pid, String id, String code);

//    Department object2objectDB(Department object, Department objectDB);
    //Integer findMaxSerialNumber(String pid);

    /**
     * check企业ID是否允许删除
     * 当前企业ID(子节点)-是否使用
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param ids
     * @return
     */
//    String checkDeleteCompanyByIds(String ids);

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-16
     */
//    List<LinkedHashMap> getColumnList() throws Exception;

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-16
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

    /**
     * 根据企业ID-判断当前企业有效期是否超时
     *
     * @param companyID  企业ID
     * @return
     *   true : 已超时
     *   false: 未超时
     */
    String checkCompanyValidityDate(String companyID);

    void exportExcelCompanys (PageData pd, Pagination pg) throws Exception;

    ResultModel addCompanyAdmin(PageData pageData) throws Exception;

    ResultModel updateCompany(PageData pageData) throws Exception;
    ResultModel updateCompanyByCompanyUser(PageData pageData) throws Exception;

    ResultModel deleteCompanyAdmins(PageData pageData) throws Exception;

    ResultModel findListCompany(PageData pageData) throws Exception;

    ResultModel listPageCompanyAdmins(PageData pd) throws Exception;
}
