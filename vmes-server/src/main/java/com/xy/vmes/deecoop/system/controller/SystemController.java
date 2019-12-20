package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.Dictionary;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.xy.vmes.deecoop.system.service.UserService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SystemController {
    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/system/findSystemDateTime")
    public ResultModel findSystemDateTime() throws Exception {
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        String dateFormat = pageData.getString("dateFormat");
        if (dateFormat == null || dateFormat.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("日期格式为空或空字符串！");
            return model;
        }

        String dateStr = DateFormat.date2String(new Date(), dateFormat);
        if (dateStr == null || dateStr.trim().length() == 0) {
            String msgTemp = "系统时间转换错误，日期格式({0})";
            String msgStr = MessageFormat.format(msgTemp, dateFormat);

            model.putCode("1");
            model.putMsg(msgStr);
            return model;
        }

        model.set("sysDateTime", dateStr);
        return model;
    }

    /**
     * 获取用户所属部门
     * 1. (用户id, 组织类型id) 关联表查询(用户表,用户员工,员工岗位,部门表)
     * 2. (企业id, 组织类型id) 部门表查询
     *
     * 接口参数
     *   userId: 用户id
     *   deptType: 组织类型 字典表(pid:8421e4f093a44f029dddbc4ab13068be:部门类型)
     *
     * 返回值:
     *   deptId: 部门ID
     *   deptName: 部门名称
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/system/findUserDepartmentByUser")
    public ResultModel findUserDepartmentByUser() throws Exception {
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        String userId = pageData.getString("userId");
        if(userId == null || userId.trim().length() == 0){
            model.putCode("1");
            model.putMsg("用户id为空或空字符串！");
            return model;
        }

        String companyId = pageData.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        String deptType = pageData.getString("deptType");
        if (deptType == null || deptType.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("组织类型(部门类型)id为空或空字符串！");
            return model;
        }

        //deptId 部门ID
        String deptId = new String();
        //deptName 部门名称
        String deptName = new String();
        PageData findMap = new PageData();
        //////////////////////////////////////////////////////////////////////////////////////////////
        //1.(用户id, 组织类型id) 关联表查询(用户表,用户员工,员工岗位,部门表)
        findMap.put("userId", userId);
        findMap.put("deptType", deptType);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        List<Map> varList = userService.getDataListPage(findMap, null);
        if (varList != null && varList.size() > 0) {
            Map<String, Object> valueMap = varList.get(0);
            if (valueMap != null && valueMap.get("deptId") != null) {
                deptId = valueMap.get("deptId").toString().trim();
            }

            if (valueMap != null && valueMap.get("deptName") != null) {
                deptName = valueMap.get("deptName").toString().trim();
            }

            model.put("deptId", deptId);
            model.put("deptName", deptName);
            return model;
        }

        //2. (企业id, 组织类型id) 部门表查询
        String deptTypeName = new String();
        findMap.clear();
        findMap.put("id", deptType);
        Dictionary dictionary = dictionaryService.findDictionaryById(deptType);
        if (dictionary != null && dictionary.getName() != null) {
            deptTypeName = dictionary.getName().trim();
        }

        findMap.clear();
        findMap.put("id1", companyId);
        findMap.put("deptType", deptType);
        findMap.put("layer", Integer.valueOf(2));
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Department> detpList = departmentService.findDepartmentList(findMap);
        if (detpList == null || detpList.size() == 0) {
            String msgTemp = "您所在的企业组织类型({0})的部门不存在，请与管理员联系！";
            String msgStr = MessageFormat.format(msgTemp, deptTypeName);

            model.putCode("1");
            model.putMsg(msgStr);
            return model;
        } else if (detpList != null && detpList.size() == 1) {
            model.put("deptId", detpList.get(0).getId());
            model.put("deptName", detpList.get(0).getName());
            return model;
        }

        return model;
    }
}
