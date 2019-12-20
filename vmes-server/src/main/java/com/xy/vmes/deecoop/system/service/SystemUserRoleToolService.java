package com.xy.vmes.deecoop.system.service;

/**
 * 说明：用户角色工具类 接口类
 * 创建人：陈刚
 * 创建时间：2019-09-30
 */
public interface SystemUserRoleToolService {

    /**
     * 获取角色-当前企业的企业管理员角色
     *
     * @param companyId
     * @return
     */
    String findRoleByCompany(String companyId);

}
