package com.xy.vmes.deecoop.system.service;

public interface DictionaryDeleteCheckService {

    /**
     * 删除字典时验证是否允许删除
     * 根据(字典id)查询相关业务表
     *
     * @param id 字典表id
     *
     * @return
     */
    String checkDeleteDictionary(String id, String companyId) throws Exception;
}
