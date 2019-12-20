package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.entity.User;
import com.xy.vmes.entity.UserRole;
import com.xy.vmes.deecoop.system.service.RoleService;
import com.xy.vmes.deecoop.system.service.SystemUserRoleToolService;
import com.xy.vmes.deecoop.system.service.UserRoleService;
import com.xy.vmes.deecoop.system.service.UserService;
import com.yvan.PageData;
import com.yvan.common.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：用户角色工具类 实现类
 * 创建人：陈刚
 * 创建时间：2019-09-30
 */
@Service
public class SystemUserRoleToolServiceImp implements SystemUserRoleToolService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    /**
     * 获取角色-当前企业的企业管理员角色
     *
     * @param companyId
     * @return
     */
    public String findRoleByCompany(String companyId) {
        if (companyId == null || companyId.trim().length() == 0) {return null;}

        //1. 获取当前企业-企业管理员用户对象
        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //企业管理员:userType_company: 2fb9bbee46ca4ce1913f3a673a7dd68f
        findMap.put("userType", Common.DICTIONARY_MAP.get("userType_company"));
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        User user = userService.findUser(findMap);
        if (user == null || user.getId() == null || user.getId().trim().length() == 0) {return null;}

        //2. 获取当前企业-企业管理员角色id
        findMap = new PageData();
        findMap.put("userId", user.getId().trim());
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        UserRole userRole = userRoleService.findUserRole(findMap);

        if (userRole != null && userRole.getRoleId() != null && userRole.getRoleId().trim().length() > 0) {
            return userRole.getRoleId().trim();
        }

        return null;
    }


}
