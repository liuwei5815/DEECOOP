package com.xy.vmes.deecoop.system.service;

import com.xy.vmes.entity.User;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserExcelService {
    String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,String companyId,Integer index,Integer maxShowRow);//@

    String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,Integer index,Integer maxShowRow);//@

    String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,Integer index,Integer maxShowRow) throws Exception;//@

    void addImportExcelByList(List<LinkedHashMap<String, String>> objectList, String userId);//@
}
