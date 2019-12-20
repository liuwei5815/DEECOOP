package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.CompanyService;
import com.yvan.*;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：企业管理-企业和企业账号或企业管理员Controller
 * @author 陈刚
 * @date 2018-08-06
 */
@RestController
@Slf4j
public class CompanyController {
    private Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;


    @Autowired
    private ColumnService columnService;

//    @PostMapping("/system/company/listPageCompanyAdmins")
//    public ResultModel listPageCompanyAdmins() throws Exception {
//        ResultModel model = new ResultModel();
//
//        //1. 查询遍历List列表
//        List<LinkedHashMap<String, String>> titleOutList = new ArrayList<LinkedHashMap<String, String>>();
//        List<String> titlesHideList = new ArrayList<String>();
//        Map<String, String> varModelMap = new HashMap<String, String>();
//        List<LinkedHashMap<String, String>> titleList = companyService.getColumnList();
//        if (titleList != null && titleList.size() > 0) {
//            LinkedHashMap<String, String> titlesMap = titleList.get(0);
//            for (Map.Entry<String, String> entry : titlesMap.entrySet()) {
//                LinkedHashMap<String, String> titleMap = new LinkedHashMap<String, String>();
//                if (entry.getKey().indexOf("_hide") != -1) {
//                    titleMap.put(entry.getKey().replace("_hide",""), entry.getValue());
//                    titlesHideList.add(entry.getKey().replace("_hide",""));
//                    varModelMap.put(entry.getKey().replace("_hide",""), "");
//                } else if (entry.getKey().indexOf("_hide") == -1) {
//                    titleMap.put(entry.getKey(), entry.getValue());
//                    varModelMap.put(entry.getKey(), "");
//                }
//                titleOutList.add(titleMap);
//            }
//        }
//
//        Map<String, Object> mapObj = new HashMap<String, Object>();
//        mapObj.put("hideTitles", titlesHideList);
//        mapObj.put("titles", YvanUtil.toJson(titleOutList));
//
//        //2. 分页查询数据List
//        PageData pageData = HttpUtils.parsePageData();
//        String userType = (String)pageData.get("userType");
//        String userId = (String)pageData.get("userId");
//        String companyId = (String)pageData.get("companyId");
//
//        pageData.put("layer", "1");
//        if ("1".equals(userType) && companyId != null && companyId.trim().length() > 0) {
//            pageData.put("id", companyId);
//        } else if ("2".equals(userType) && userId != null && userId.trim().length() > 0) {
//            pageData.put("cuser", userId);
//        }
//
//        Pagination pg = HttpUtils.parsePagination(pageData);
//        List<Map<String, Object>> varList = companyService.getDataListPage(pageData, pg);
//        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
//        if(varList != null && varList.size() > 0) {
//            for (Map<String, Object> map : varList) {
//                Map<String, String> varMap = new HashMap<String, String>();
//                varMap.putAll(varModelMap);
//                for (Map.Entry<String, String> entry : varMap.entrySet()) {
//                    varMap.put(entry.getKey(), map.get(entry.getKey()) != null ? map.get(entry.getKey()).toString() : "");
//                }
//                varMapList.add(varMap);
//            }
//        }
//        mapObj.put("varList", YvanUtil.toJson(varMapList));
//        mapObj.put("pageData", YvanUtil.toJson(pg));
//
//        model.putResult(mapObj);
//        return model;
//    }

    /**
     * @author 刘威 自动创建，可以修改
     * @date 2018-08-23
     */
    @PostMapping("/system/company/listPageCompanyAdmins")
    public ResultModel listPageCompanyAdmins()  throws Exception {
        logger.info("################company/listPageCompanyAdmins 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = companyService.listPageCompanyAdmins(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################company/listPageCompanyAdmins 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/company/findListCompany")
    public ResultModel findListCompany() throws Exception {
        logger.info("################/system/company/findListCompany 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = companyService.findListCompany(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/company/findListCompany 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
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
    @PostMapping("/system/company/exportExcelCompanys")
    public void exportExcelCompanys() throws Exception {
        logger.info("################company/exportExcelCompanys 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);

        companyService.exportExcelCompanys(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################company/exportExcelCompanys 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**添加企业信息-同时创建企业账号或企业管理员
     *
     * @author 陈刚
     * @date 2018-08-06
     */
    @PostMapping("/system/company/addCompanyAdmin")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addCompanyAdmin() throws Exception {
        logger.info("################company/addCompanyAdmin 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();

        ResultModel model = companyService.addCompanyAdmin(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################company/addCompanyAdmin 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改企业信息
     *
     * @author 陈刚
     * @date 2018-08-06
     */
    @PostMapping("/system/company/updateCompany")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateCompany() throws Exception {
        logger.info("################company/updateCompany 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();

        ResultModel model = companyService.updateCompany(pageData);

        Long endTime = System.currentTimeMillis();
        logger.info("################company/updateCompany 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/company/updateCompanyByCompanyUser")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateCompanyByCompanyUser() throws Exception {
        logger.info("################/system/company/updateCompanyByCompanyUser 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();

        ResultModel model = companyService.updateCompanyByCompanyUser(pageData);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/company/updateCompanyByCompanyUser 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**修改企业账号或企业管理员信息
     *
     * @author 陈刚
     * @date 2018-08-06
     */
    @PostMapping("/system/company/updateAdmin")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateAdmin() {

        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        return model;
    }

    /**修改企业信息-(禁用)状态
     *
     * @author 陈刚
     * @date 2018-08-06
     */
//    @PostMapping("/system/company/updateDisableCompany")
//    public ResultModel updateDisableCompany() {
//        ResultModel model = new ResultModel();
//        PageData pageData = HttpUtils.parsePageData();
//
//        try {
//        } catch (Exception e) {
//            throw new RestException("", e.getMessage());
//        }
//
//        return model;
//    }

    /**删除企业信息-有关联数据不可禁用户和删除
     *
     * @author 陈刚
     * @date 2018-08-06
     */
    @PostMapping("/system/company/deleteCompanyAdmins")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteCompanyAdmins() throws Exception {
        logger.info("################company/deleteCompanyAdmins 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();

        ResultModel model = companyService.deleteCompanyAdmins(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################company/deleteCompanyAdmins 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}
