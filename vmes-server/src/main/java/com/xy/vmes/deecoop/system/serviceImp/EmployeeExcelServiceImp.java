package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.entity.EmployPost;
import com.xy.vmes.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EmployeeExcelServiceImp implements EmployeeExcelService {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private PostService postService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployPostService employPostService;

    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                         String companyId,
                                         String userId,
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

        //获取当前企业下全部部门 deptKeyNameMap<部门id, 部门名称> deptNameKeyMap<部门名称, 部门id>
        departmentService.implementDeptMapByCompanyId(companyId);
        Map<String, String> deptNameKeyMap = departmentService.getDeptNameKeyMap();

        //获取全部 政治面貌
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("political"), companyId);
        Map<String, String> politicalNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error_1 = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;

        String msg_column_mobile_error = "第 {0} 行: {1}:{2} 输入错误，请输入11位手机号码！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_post_error = "第 {0} 行: {1}:{2} 输入错误，该岗位名称不属于({3})部门！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_identity_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的身份证号！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //必填项验证
            //code 员工编号
            String code = mapObject.get("code");
            if (code == null || code.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "员工编号");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //name 员工姓名
            String name = mapObject.get("name");
            if (name == null || name.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "员工姓名");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //mobile 手机号
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
            //deptName 部门
            String deptName = mapObject.get("deptName");
            if (deptName == null || deptName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "部门");
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

            //postName 岗位
            String postName = mapObject.get("postName");
            if (postName == null || postName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "岗位");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (postName != null && postName.trim().length() > 0) {
                String deptId = mapObject.get("deptId");
                postService.implementBusinessMapByDeptId(deptId);
                Map<String, String> postNameKeyMap = postService.getNameKeyMap();
                if (postNameKeyMap != null && postNameKeyMap.size() > 0 && postNameKeyMap.get(postName) == null) {
                    //String msg_column_post_error = "第 {0} 行: {1}:{2} 输入错误，该岗位名称不属于({3})部门！"
                    String str_error = MessageFormat.format(msg_column_post_error,
                            (i+index_int),
                            "岗位",
                            postName,
                            deptName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (postNameKeyMap != null && postNameKeyMap.size() > 0 && postNameKeyMap.get(postName) != null) {
                    //postId 岗位id
                    mapObject.put("postId", postNameKeyMap.get(postName));
                }

            }
            /////////////////////////////////////////////////
            //非必填项验证
            //email 邮箱
            //identityNumber 身份证号
            String identityNumber = mapObject.get("identityNumber");
            if (identityNumber != null && identityNumber.trim().length() > 0) {
                try {
                    BigDecimal identity_big = new BigDecimal(identityNumber);
                    //mapObject.put("identityNumber", mobile_big.toBigInteger().toString());
                    if (identity_big.toBigInteger().toString().length() <= 14) {
                        //String msg_column_identity_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的身份证号！"
                        String str_error = MessageFormat.format(msg_column_mobile_error,
                                (i+index_int),
                                "身份证号",
                                identityNumber);
                        strBuf.append(str_error);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                    } else if (identity_big.toBigInteger().toString().length() == 11) {
                        mapObject.put("identityNumber", identity_big.toBigInteger().toString());
                    }
                } catch (NumberFormatException e) {
                    //String msg_column_identity_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的身份证号！"
                    String str_error = MessageFormat.format(msg_column_identity_error,
                            (i+index_int),
                            "身份证号",
                            identityNumber);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }
            }

            //maritalName 婚姻状况 婚姻状况(1:已婚 0:未婚)
            String maritalName = mapObject.get("maritalName");
            if (maritalName != null && maritalName.trim().length() > 0) {
                if ("已婚,未婚".indexOf(maritalName.trim()) == -1) {
                    //String msg_column_error_1 = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_error_1,
                            (i+index_int),
                            "婚姻状况",
                            maritalName,
                            "已婚,未婚");
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    //marital 婚姻状况id (1:已婚 0:未婚)
                    if ("已婚".equals(maritalName.trim())) {
                        mapObject.put("marital", "1");
                    } else if ("未婚".equals(maritalName.trim())) {
                        mapObject.put("marital", "0");
                    }
                }
            }
            //sexName 性别 (1:男0:女)
            String sexName = mapObject.get("sexName");
            if (sexName != null && sexName.trim().length() > 0) {
                if ("男,女".indexOf(sexName.trim()) == -1) {
                    //String msg_column_error_1 = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_error_1,
                            (i+index_int),
                            "性别",
                            sexName,
                            "男,女");
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    //sex 性别id (1:男0:女)
                    if ("男".equals(sexName.trim())) {
                        mapObject.put("sex", "1");
                    } else if ("女".equals(sexName.trim())) {
                        mapObject.put("sex", "0");
                    }
                }
            }

            //birthday 出生日期
            String birthday = mapObject.get("birthday");
            if (birthday != null && birthday.trim().length() > 0) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE_FORMAT);
                    dateFormat.parse(birthday);
                    //birthday 出生日期
                    mapObject.put("birthday", birthday);
                } catch (ParseException e) {
                    //String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！"
                    String str_error = MessageFormat.format(msg_column_date_error,
                            (i + index_int),
                            "出生日期",
                            birthday);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }

            //entryDate 入职日期
            String entryDate = mapObject.get("entryDate");
            if (entryDate != null && entryDate.trim().length() > 0) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE_FORMAT);
                    dateFormat.parse(entryDate);
                    //entryDate 入职日期
                    mapObject.put("entryDate", entryDate);
                } catch (ParseException e) {
                    //String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！"
                    String str_error = MessageFormat.format(msg_column_date_error,
                            (i + index_int),
                            "入职日期",
                            entryDate);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }
            //nativePlace 籍贯
            //politicalName 政治面貌
            String politicalName = mapObject.get("politicalName");
            if (politicalName != null && politicalName.trim().length() > 0) {
                if (politicalNameKeyMap != null && politicalNameKeyMap.size() > 0 && politicalNameKeyMap.get(politicalName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "政治面貌",
                            politicalName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (politicalNameKeyMap != null && politicalNameKeyMap.size() > 0 && politicalNameKeyMap.get(politicalName) != null) {
                    //political 政治面貌id
                    mapObject.put("political", politicalNameKeyMap.get(politicalName));
                }
            }
            //remark 备注
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

        String msg_column_exist_1 = "第 {0} 行: {1}:{2}输入重复，与第 {3} 行重复！" + Common.SYS_ENDLINE_DEFAULT;
        Map<String, Integer> empMobileMap = new HashMap<String, Integer>();
        Map<String, Integer> empCodeMap = new HashMap<String, Integer>();

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //code 员工编号
            String code = mapObject.get("code");
            if (code != null && code.trim().length() > 0) {
                if (empCodeMap.get(code) != null) {
                    //String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！"
                    String str_error = MessageFormat.format(msg_column_exist_1,
                            (i+index_int),
                            "员工编号",
                            code,
                            empCodeMap.get(code).toString());
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    empCodeMap.put(code, Integer.valueOf(i + index_int));
                }
            }

            //手机号 (mobile)唯一性判断
            String mobile = mapObject.get("mobile");
            if (mobile != null && mobile.trim().length() > 0) {
                if (empMobileMap.get(mobile) != null) {
                    //String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！"
                    String str_error = MessageFormat.format(msg_column_exist_1,
                            (i+index_int),
                            "手机号",
                            mobile,
                            empMobileMap.get(mobile).toString());
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    empMobileMap.put(mobile, Integer.valueOf(i + index_int));
                }
            }
        }

        return strBuf.toString();
    }

    public String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,
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

        String msg_column_exist_employee = "第 {0} 行: {1}:{2}在员工管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");

            //code 员工编号
            String code = mapObject.get("code");
            if (code != null && code.trim().length() > 0) {
                if (employeeService.isExistByCode(null, code, companyId)) {
                    //String msg_column_exist_employee = "第 {0} 行: ({1}:{2})在员工管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_exist_employee,
                            (i+index_int),
                            "员工编号",
                            code);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }

            }

            //手机号 (mobile)唯一性判断
            String mobile = mapObject.get("mobile");
            if (mobile != null && mobile.trim().length() > 0) {
                //员工表
                if (employeeService.isExistByMobile(null, mobile, companyId)) {
                    //String msg_column_exist_employee = "第 {0} 行: ({1}:{2})在员工管理中已存在！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_exist_employee,
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

    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
        if (objectList == null || objectList.size() == 0) {return;}

        for (int i = 0; i < objectList.size(); i++) {
            Employee employee = new Employee();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            String userId = mapObject.get("userId");
            employee.setCuser(userId);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            employee.setCompanyId(companyId);

            //必填项验证
            //code 员工编号
            String code = mapObject.get("code");
            employee.setCode(code);
            //name 员工姓名
            String name = mapObject.get("name");
            employee.setName(name);
            //mobile 手机号
            String mobile = mapObject.get("mobile");
            employee.setMobile(mobile);
            //deptName 部门 deptId 所属部门
            //postName 岗位 postId 岗位id
            String postId = mapObject.get("postId");

            /////////////////////////////////////////////////
            //非必填项验证
            //email 邮箱
            String email = mapObject.get("email");
            if (email != null && email.trim().length() > 0) {
                employee.setMobile(email);
            }
            //identityNumber 身份证号
            String identityNumber = mapObject.get("identityNumber");
            employee.setIdentityNumber(identityNumber);
            //maritalName 婚姻状况 marital 婚姻状况id
            String marital = mapObject.get("marital");
            employee.setMarital(marital);
            //sexName 性别 sex 性别id
            String sex = mapObject.get("sex");
            employee.setSex(sex);
            //birthday 出生日期
            String birthday = mapObject.get("birthday");
            if (birthday != null && birthday.trim().length() > 0) {
                Date date = DateFormat.dateString2Date(birthday, DateFormat.DEFAULT_DATE_FORMAT);
                if (date != null) {
                    employee.setBirthday(date);
                }
            }

            //entryDate 入职日期
            String entryDate = mapObject.get("entryDate");
            if (entryDate != null && entryDate.trim().length() > 0) {
                Date date = DateFormat.dateString2Date(entryDate, DateFormat.DEFAULT_DATE_FORMAT);
                if (date != null) {
                    employee.setEntryDate(date);
                }
            }
            //nativePlace 籍贯
            String nativePlace = mapObject.get("nativePlace");
            employee.setNativePlace(nativePlace);
            //politicalName 政治面貌 political 政治面貌id
            String political = mapObject.get("political");
            employee.setPolitical(political);
            //remark 备注
            String remark = mapObject.get("remark");
            employee.setRemark(remark);

            try {
                employeeService.save(employee);

                //新增员工主岗信息
                EmployPost employPost = new EmployPost();
                employPost.setEmployId(employee.getId());
                employPost.setPostId(postId);
                employPost.setCuser(employee.getCuser());
                employPost.setIsplurality("0");//主岗
                employPostService.save(employPost);

                //System.out.println("第" + (i+1) + "行：添加成功！");

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
