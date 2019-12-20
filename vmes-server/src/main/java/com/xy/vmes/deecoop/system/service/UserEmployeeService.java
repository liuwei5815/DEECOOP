package com.xy.vmes.deecoop.system.service;

import com.xy.vmes.entity.User;
import com.xy.vmes.entity.Employee;
import com.yvan.PageData;

import java.util.List;
import java.util.Map;

/**
 * 说明：用户-员工 接口类
 * 创建人：自动生成
 * 创建时间：2018-07-18
 */
public interface UserEmployeeService {
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    List<Map<String, Object>> findViewUserEmployList(PageData object);//@

    Map<String, Object> findViewUserEmployByUserId(String userId);//@
//    Map<String, Object> findViewUserEmployByEmployID(String employID);

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    User mapObject2User(Map<String, Object> mapObject, User object);//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    Employee mapObject2Employee(Map<String, Object> mapObject, Employee object);//@

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-24
     */
//    Map<String, Object> userEmployMap2RedisMap(Map<String, Object> objectMap, Map<String, Object> redisMap);
}
