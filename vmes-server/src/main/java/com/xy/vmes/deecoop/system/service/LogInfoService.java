package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.LogInfo;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：操作日志 接口类
 * 创建人：刘威 自动生成
 * 创建时间：2018-08-28
 */
public interface LogInfoService {


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void save(LogInfo logInfo) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void update(LogInfo logInfo) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void updateAll(LogInfo logInfo) throws Exception;


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    LogInfo selectById(String id) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<LogInfo> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<LogInfo> dataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<LogInfo> selectByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/



    /**
     * 获取调用方法名称前缀：
     * 根据Controller调用方法全路径名称(com.xy.vmes.deecoop.system.controller.DepartmentController.addDepartment())
     * @param methodPath
     * @return
     */
    String findMethodPrefix(String methodPath);//@

    /**
     * 获取业务表名：
     * 根据Controller调用方法全路径名称(com.xy.vmes.deecoop.system.controller.DepartmentController.addDepartment())
     * @param methodPath
     * @return
     */
    String findTable(String methodPath);//@

    /**
     * 创建新的日志对象<Loginfo>
     * @return
     */
    LogInfo createLoginfo(LogInfo object);//@

    ResultModel listPageLogInfos(PageData pd, Pagination pg) throws Exception;

    void exportExcelLogInfos (PageData pd) throws Exception;

    ResultModel importExcelLogInfos(MultipartFile file) throws Exception;
}



