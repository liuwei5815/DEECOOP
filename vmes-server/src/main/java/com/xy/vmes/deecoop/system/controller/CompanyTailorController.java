package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.SystemUserRoleToolService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.common.util.CommonCompanyTailor;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：企业定制化功能Controller
 * @author 陈刚
 * @date 2019-09-30
 */
@RestController
@Slf4j
public class CompanyTailorController {
    @Autowired
    private SystemUserRoleToolService systemToolService;

    /**
     * 获取当前企业是否存在定制化
     * 1. 获取当前企业-企业管理员角色id
     * 2. 当前企业角色id是否存在定制化配置
     *
     * 备注:
     * 企业角色id(企业管理员角色id)
     * 企业角色id 定制化配置在 CommonCompanyTailor 常理类中
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/system/companyTailor/findIsExistCompanyTailorByCompanyRole")
    public ResultModel findIsExistCompanyTailorByCompanyRole() throws Exception {
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        String companyId = pageData.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        //1. 获取当前企业-企业管理员角色id
        String roleId = systemToolService.findRoleByCompany(companyId);

        //2. 企业角色id 是否存在定制化配置
        //是否存在企业定制:isExistCompanyTailor:
        //Y: 存在定制 N:不存在定制(默认值)
        String isExistCompanyTailor = new String("N");
        if (roleId != null && CommonCompanyTailor.SYS_COMPANY_ROLE.get(roleId) != null) {
            isExistCompanyTailor = new String("Y");
        }

        model.put("isExistCompanyTailor", isExistCompanyTailor);
        return model;
    }
}
