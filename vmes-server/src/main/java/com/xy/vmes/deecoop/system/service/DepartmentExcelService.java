package com.xy.vmes.deecoop.system.service;

import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.DeptExcelEntity;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 组织管理-Excel导入Service
 */
public interface DepartmentExcelService {
//    DeptExcelEntity map2ExcelEntity(LinkedHashMap<String, String> mapObject, DeptExcelEntity excelEntity);
//    String findDeptNampPath(DeptExcelEntity excelEntity);
    List<DeptExcelEntity> mapList2ImportExcelList(List<LinkedHashMap<String, String>> mapList, List<DeptExcelEntity> excelList);//@
    String checkColumnImportExcel(List<DeptExcelEntity> objectList,String companyId,Integer index,Integer maxShowRow);//@

    String checkExistImportExcelBySelf(List<DeptExcelEntity> excelList,Integer index, Integer maxShowRow);//@
    String checkExistImportExcelByDatabase(List<DeptExcelEntity> excelList,String companyId,Integer index,Integer maxShowRow);//@

    void addImportExcelByList(List<DeptExcelEntity> excelList,String cuser,String companyId);
}
