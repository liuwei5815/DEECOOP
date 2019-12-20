package com.xy.vmes.deecoop.system.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeExcelBySimpleService {
    String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                  String companyId,
                                  String userId,
                                  Integer index,
                                  Integer maxShowRow);

    String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,
                                       Integer index,
                                       Integer maxShowRow);

    String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,
                                           Integer index,
                                           Integer maxShowRow);

    /**
     * 添加系统基础表
     * 1. 添加部门
     * 2. 添加部门岗位
     * 3. 字典表(政治面貌)
     *
     * deptPostMap:参数说明
     * deptPostMap:部门岗位结构体:<部门名称_岗位名称, 部门岗位Map>
     *   部门岗位Map:
     *     deptName 部门名称
     *     postName 岗位名称
     *     postId   岗位id
     *
     * @param objectList
     * @param deptPostMap 按引用传入,方法调用后值方式改变
     * @param companyId
     * @param userId
     */
    void addSystemBaseTableImportExcel(List<LinkedHashMap<String, String>> objectList,
                                       Map<String, Map<String, String>> deptPostMap,
                                       String companyId,
                                       String userId) throws Exception;

    void addImportExcelByList(List<LinkedHashMap<String, String>> objectList,
                              Map<String, Map<String, String>> deptPostMap,
                              String companyId);

}
