package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.entity.Employee;
import com.xy.vmes.entity.User;
import com.xy.vmes.entity.UserRole;
import com.yvan.MD5Utils;
import com.yvan.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

@Service
public class UserExcelServiceImp implements UserExcelService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CoderuleService coderuleService;

    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                  String companyId,
                                  Integer index,
                                  Integer maxShowRow) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //获取当前企业下全部部门 deptKeyNameMap<部门id, 部门名称> deptNameKeyMap<部门名称, 部门id>
        departmentService.implementDeptMapByCompanyId(companyId);
        Map<String, String> deptNameKeyMap = departmentService.getDeptNameKeyMap();

        //获取当前企业下全部角色
        roleService.implementBusinessMapByCompanyId(companyId);
        Map<String, String> roleNameKeyMap = roleService.getNameKeyMap();

        //获取当前企业下全部用户类型 userType_company userType_admin
        String tempStr = "''{0}'',''{1}''";
        String nameNotin = MessageFormat.format(tempStr,
                Common.DICTIONARY_MAP.get("userType_company"),
                Common.DICTIONARY_MAP.get("userType_admin"));
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("userType"), null, nameNotin);
        Map<String, String> userTypeNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_mobile_error = "第 {0} 行: {1}:{2} 输入错误，请输入11位手机号码！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        int maxRow = 0;
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);
            //companyId 企业ID
            mapObject.put("companyId", companyId);

            //姓名 userName
            String userName = mapObject.get("userName");
            if (userName == null || userName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "姓名");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //所属部门 deptName
            String deptName = mapObject.get("deptName");
            if (deptName == null || deptName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "所属部门");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (deptName != null && deptName.trim().length() > 0) {
                if (deptNameKeyMap != null && deptNameKeyMap.size() > 0 && deptNameKeyMap.get(deptName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "所属部门",
                            deptName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (deptNameKeyMap != null && deptNameKeyMap.size() > 0 && deptNameKeyMap.get(deptName) != null) {
                    //deptId 所属部门
                    mapObject.put("deptId", deptNameKeyMap.get(deptName));
                }
            }

            //角色名称 roleName
            String roleName = mapObject.get("roleName");
            if (roleName == null || roleName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "角色名称");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (roleName != null && roleName.trim().length() > 0) {
                if (roleNameKeyMap != null && roleNameKeyMap.size() > 0 && roleNameKeyMap.get(roleName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "角色名称",
                            roleName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (roleNameKeyMap != null && roleNameKeyMap.size() > 0 && roleNameKeyMap.get(roleName) != null) {
                    //roleId 角色id
                    mapObject.put("roleId", roleNameKeyMap.get(roleName));
                }
            }

            //用户类型 userTypeName
            String userTypeName = mapObject.get("userTypeName");
            if (userTypeName == null || userTypeName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "用户类型");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (userTypeName != null && userTypeName.trim().length() > 0) {
                if (userTypeNameKeyMap != null && userTypeNameKeyMap.size() > 0 && userTypeNameKeyMap.get(userTypeName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "用户类型",
                            userTypeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (userTypeNameKeyMap != null && userTypeNameKeyMap.size() > 0 && userTypeNameKeyMap.get(userTypeName) != null) {
                    //userType 用户类型id
                    mapObject.put("userType", userTypeNameKeyMap.get(userTypeName));
                }
            }

            //手机号 mobile (非空,全数字,字符串长度)验证
            String mobile = mapObject.get("mobile");
            if (mobile == null || mobile.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "手机号");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (mobile != null && mobile.trim().length() > 0) {
                try {
                    BigDecimal mobile_big = new BigDecimal(mobile);
                    if (mobile_big.toBigInteger().toString().length() != 11) {
                        //String msg_column_mobile_error = "第 {0} 行: {1}:{2} 输入错误，请输入11位手机号码！"
                        String str_error = MessageFormat.format(msg_column_mobile_error,
                                (i+index_int),
                                "手机号",
                                mobile);
                        strBuf.append(str_error);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                    } else if (mobile_big.toBigInteger().toString().length() == 11) {
                        mapObject.put("mobile", mobile_big.toBigInteger().toString());
                    }
                } catch (NumberFormatException e) {
                    //e.printStackTrace();
                    //String msg_column_mobile_error = "第 {0} 行: {1}:{2} 输入错误，请输入11位手机号码！"
                    String str_error = MessageFormat.format(msg_column_mobile_error,
                            (i+index_int),
                            "手机号",
                            mobile);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }
            }
        }

        return strBuf.toString();
    }

    public String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,
                                       Integer index,
                                       Integer maxShowRow) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int maxRow = 0;
        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！" + Common.SYS_ENDLINE_DEFAULT;
        Map<String, Integer> userCodeMap = new HashMap<String, Integer>();
        Map<String, Integer> userMobileMap = new HashMap<String, Integer>();
        Map<String, Integer> empCodeMap = new HashMap<String, Integer>();

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //账号 (userCode)唯一性判断 -- 允许为空
            String userCode = mapObject.get("userCode");
            if (userCode != null && userCode.trim().length() > 0) {
                if (userCodeMap.get(userCode) != null) {
                    //String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！"
                    String str_error = MessageFormat.format(msg_column_exist_1,
                            (i+index_int),
                            "账号",
                            userCode,
                            userCodeMap.get(userCode).toString());
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    userCodeMap.put(userCode, Integer.valueOf(i + index_int));
                }
            }

            //手机号 (mobile)唯一性判断
            String mobile = mapObject.get("mobile");
            if (mobile != null && mobile.trim().length() > 0) {
                if (userMobileMap.get(mobile) != null) {
                    //String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！"
                    String str_error = MessageFormat.format(msg_column_exist_1,
                            (i+index_int),
                            "手机号",
                            mobile,
                            userMobileMap.get(mobile).toString());
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    userMobileMap.put(mobile, Integer.valueOf(i + index_int));
                }
            }

            //employeeCode 员工编号
            String employeeCode = mapObject.get("employeeCode");
            if (employeeCode != null && employeeCode.trim().length() > 0) {
                if (empCodeMap.get(employeeCode) != null) {
                    //String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！"
                    String str_error = MessageFormat.format(msg_column_exist_1,
                            (i+index_int),
                            "员工编号",
                            employeeCode,
                            empCodeMap.get(employeeCode).toString());
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    empCodeMap.put(employeeCode, Integer.valueOf(i + index_int));
                }
            }
        }

        return strBuf.toString();
    }

    public String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,
                                           Integer index,
                                           Integer maxShowRow) throws Exception {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int maxRow = 0;
        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        String msg_column_exist_userCode = "第 {0} 行: ({1}:{2})在用户管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_exist_mobile_user = "第 {0} 行: ({1}:{2})在用户管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_exist_mobile_employee = "第 {0} 行: ({1}:{2})在员工管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //账号 (userCode)唯一性判断 -- 允许为空
            String userCode = mapObject.get("userCode");
            if (userCode != null && userCode.trim().length() > 0) {
                Boolean isExistUserCode = userService.isExistUserByUserCode(null, userCode);
                if (isExistUserCode != null && isExistUserCode.booleanValue()) {
                    //String msg_column_exist_userCode = "第 {0} 行: ({1}:{2})在用户管理中已存在！"
                    String str_error = MessageFormat.format(msg_column_exist_userCode,
                            (i+index_int),
                            "账号",
                            userCode);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }
            }

            //手机号 (mobile)唯一性判断
            String mobile = mapObject.get("mobile");
            if (mobile != null && mobile.trim().length() > 0) {
                //用户表
                PageData pageData = new PageData();
                pageData.put("mobile",mobile);
                if (userService.isExistMobile(pageData)) {
                    //String msg_column_exist_mobile_user = "第 {0} 行: ({1}:{2})在用户管理中已存在！"
                    String str_error = MessageFormat.format(msg_column_exist_mobile_user,
                            (i+index_int),
                            "手机号",
                            mobile);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }
//                //员工表
//                if (employeeService.isExistByMobile(null, mobile)) {
//                    //String msg_column_exist_mobile_user = "第 {0} 行: ({1}:{2})在用户管理中已存在！"
//                    String str_error = MessageFormat.format(msg_column_exist_mobile_employee,
//                            (i+index_int),
//                            "手机号",
//                            mobile);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//                }
            }

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            //employeeCode 员工编号
            String employeeCode = mapObject.get("employeeCode");
            if (employeeCode != null && employeeCode.trim().length() > 0) {
                PageData findMap = new PageData();
                findMap.put("currentCompanyId", companyId);
                findMap.put("code", employeeCode);
                //是否启用(0:已禁用 1:启用)
                findMap.put("isdisable", "1");
                findMap.put("mapSize", Integer.valueOf(findMap.size()));
                Employee employee = employeeService.findEmployee(findMap);
                if (employee == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在或已经离职！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "员工编号",
                            employeeCode);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (employee != null) {
                    //employId 员工id
                    mapObject.put("employId", employee.getId());
                }
            }
        }

        return strBuf.toString();
    }

    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList, String userId) {
        for (int i = 0; i < objectList.size(); i++) {
            User user = new User();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //企业ID
            String companyId = mapObject.get("companyId");
            user.setCompanyId(companyId);
            user.setCuser(userId);

            //userCode 账号
            String userCode = mapObject.get("userCode");
            if (userCode != null && userCode.trim().length() > 0) {
                user.setUserCode(userCode);
            } else {
                String code = coderuleService.createCoder(companyId,"vmes_user");
                user.setUserCode(code);
            }

            //userName 姓名
            String userName = mapObject.get("userName");
            user.setUserName(userName);

            //deptName 所属部门 deptId 所属部门id
            String deptId = mapObject.get("deptId");
            user.setDeptId(deptId);

            //userTypeName 用户类型 userType 用户类型id
            String userType = mapObject.get("userType");
            user.setUserType(userType);

            //mobile 手机号
            String mobile = mapObject.get("mobile");
            user.setMobile(mobile);

            //email 邮箱
            String email = mapObject.get("email");
            user.setEmail(email);

            //remark 备注
            String remark = mapObject.get("remark");
            user.setRemark(remark);

            //employeeCode 员工编号 employId 员工id
            Employee employee = null;
            String employId = mapObject.get("employId");
            if (employId != null && employId.trim().length() > 0) {
                user.setEmployId(employId);
                employee = employeeService.findEmployeeById(employId);
            }

            //password 用户登录密码 手机号后六位进行加密作为默认密码
            String password = mobile.substring(mobile.length()-6, mobile.length());
            user.setPassword(MD5Utils.MD5(password));

            //roleName 角色名称 roleId
            String roleId = mapObject.get("roleId");
            try {
                //添加 用户表
                userService.save(user);
                //添加 用户角色表
                if (roleId != null && roleId.trim().length() > 0) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(user.getId());
                    userRole.setCuser(userId);
                    userRole.setUuser(userId);
                    userRoleService.save(userRole);
                }
                //修改 员工表
                if (employee != null) {
                    Employee employeeEdit = new Employee();

                    employeeEdit.setId(employee.getId());
                    //mobile:手机号码
                    employeeEdit.setMobile(user.getMobile());
                    //email:邮箱地址
                    employeeEdit.setEmail(user.getEmail());
                    //user_name:姓名->name:员工姓名
                    employeeEdit.setName(user.getUserName());
                    employeeEdit.setUserId(user.getId());
                    //是否开通用户(0:不开通 1:开通 is null 不开通)
                    employeeEdit.setIsOpenUser("1");
                    employeeService.update(employeeEdit);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
