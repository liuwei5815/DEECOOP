package com.xy.vmes.deecoop.system.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface EmployeeExcelService {
    String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,String companyId,String userId,Integer index,Integer maxShowRow);//@

    String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,Integer index,Integer maxShowRow);//@

    String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,Integer index,Integer maxShowRow);//@

    void addImportExcelByList(List<LinkedHashMap<String, String>> objectList);//@
}
