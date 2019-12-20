package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.deecoop.system.service.*;
import com.xy.vmes.entity.*;
import com.xy.vmes.entity.Dictionary;
import com.yvan.Conv;
import com.yvan.PageData;
import com.yvan.common.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(readOnly = false)
public class EmployeeExcelBySimpleServiceImp implements EmployeeExcelBySimpleService {
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

    @Autowired
    private CoderuleService coderuleService;

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
        //departmentService.implementDeptMapByCompanyId(companyId);
        //Map<String, String> deptNameKeyMap = departmentService.getDeptNameKeyMap();

        //获取全部 政治面貌
        //dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("political"), companyId);
        //Map<String, String> politicalNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;
        //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;

        String msg_column_mobile_error = "第 {0} 行: {1}:{2} 输入错误，请输入11位手机号码！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_identity_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的身份证号！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！" + Common.SYS_ENDLINE_DEFAULT;
        //String msg_column_post_error = "第 {0} 行: {1}:{2} 输入错误，该岗位名称不属于({3})部门！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //必填项验证
//            //code 员工编号 非必填:允许为空
//            String code = mapObject.get("code");
//            if (code == null || code.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "员工编号");
//                strBuf.append(str_isnull);
//
//                maxRow = maxRow + 1;
//                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//            }

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
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_error,
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
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，输入范围({3})！" + Common.SYS_ENDLINE_DEFAULT;
                    String str_error = MessageFormat.format(msg_column_error,
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

            //contractDate 合同到期
            String contractDate = mapObject.get("contractDate");
            if (contractDate != null && contractDate.trim().length() > 0) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE_FORMAT);
                    dateFormat.parse(contractDate);
                    //contractDate 合同到期
                    mapObject.put("contractDate", contractDate);
                } catch (ParseException e) {
                    //String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！"
                    String str_error = MessageFormat.format(msg_column_date_error,
                            (i + index_int),
                            "合同到期",
                            contractDate);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }


            //nativePlace 籍贯
            //politicalName 政治面貌
//            String politicalName = mapObject.get("politicalName");
//            if (politicalName != null && politicalName.trim().length() > 0) {
//                if (politicalNameKeyMap != null && politicalNameKeyMap.size() > 0 && politicalNameKeyMap.get(politicalName) == null) {
//                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
//                    String str_error = MessageFormat.format(msg_column_error,
//                            (i+index_int),
//                            "政治面貌",
//                            politicalName);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//                } else if (politicalNameKeyMap != null && politicalNameKeyMap.size() > 0 && politicalNameKeyMap.get(politicalName) != null) {
//                    //political 政治面貌id
//                    mapObject.put("political", politicalNameKeyMap.get(politicalName));
//                }
//            }
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 添加系统基础表
     * 1. 添加部门
     * 2. 添加部门岗位
     * 3. 字典表(政治面貌)
     *
     * deptPostMap:参数说明
     * deptPostMap:部门岗位结构体:<部门名称_岗位名称, 部门岗位Map>
     *   部门岗位Map:
     *     deptName 部门名称
     *     postName 岗位名称
     *     postId   岗位id
     *
     * @param objectList
     * @param deptPostMap 按引用传入,方法调用后值方式改变
     * @param companyId
     * @param userId
     */
    public void addSystemBaseTableImportExcel(List<LinkedHashMap<String, String>> objectList,
                                              Map<String, Map<String, String>> deptPostMap,
                                              String companyId,
                                              String userId) throws Exception {
        if (objectList == null || objectList.size() == 0) {return;}

        //1. 添加部门
        //部门Map结构体: <部门名称, 部门id>
        Map<String, String> sysDepartmentMap = this.findDepartmentMapBySystem(companyId);
        Map<String, String> excelDepartmentMap = this.findDepartmentMapByExcel(objectList);
        this.addDepartment(excelDepartmentMap, sysDepartmentMap, companyId, userId);

        //2. 添加部门岗位
        //部门岗位结构体:<部门名称_岗位名称, 部门岗位Map>
        //部门岗位Map:
        //  deptName 部门名称
        //  postName 岗位名称
        //  postId   岗位id
        this.addDepartmentPost(objectList, deptPostMap, companyId, userId);

        //3. 字典表(政治面貌)
        //政治面貌:political:pid:015cecdb7fdd450c8a21c7c97d406aa4
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("political"), companyId);
        Map<String, String> sysPoliticalMap = dictionaryService.getNameKeyMap();
        Map<String, String> excelPoliticalMap = this.findPoliticalMapByExcel(objectList);
        this.addPolitical(excelPoliticalMap, sysPoliticalMap, companyId, userId);

    }

    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList,
                                     Map<String, Map<String, String>> deptPostMap,
                                     String companyId) {
        if (objectList == null || objectList.size() == 0) {return;}

        //政治面貌:political:pid:015cecdb7fdd450c8a21c7c97d406aa4
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("political"), companyId);
        Map<String, String> sysPoliticalMap = dictionaryService.getNameKeyMap();

        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            Employee employee = new Employee();
            employee.setCompanyId(companyId);
            String userId = mapObject.get("userId");
            employee.setCuser(userId);

            //必填项验证
            //name 员工姓名
            String name = mapObject.get("name");
            employee.setName(name);
            //mobile 手机号
            String mobile = mapObject.get("mobile");
            employee.setMobile(mobile);

            //deptName 部门名称
            String deptName = mapObject.get("deptName");
            //postName 岗位名称
            String postName = mapObject.get("postName");
            String postId = new String();

            //mapKey:部门名称_岗位名称
            String mapKey = deptName + "_" + postName;
            //部门岗位结构体:<部门名称_岗位名称, 部门岗位Map>
            //部门岗位Map:
            //  deptName 部门名称
            //  postName 岗位名称
            //  postId   岗位id
            Map<String, String> deptPostValueMap = deptPostMap.get(mapKey);
            if (deptPostValueMap != null && deptPostValueMap.get("postId") != null) {
                postId = deptPostValueMap.get("postId").trim();
            }

            /////////////////////////////////////////////////
            //非必填项验证
            //code 员工编号
            String code = mapObject.get("code");
            if (code == null || code.trim().length() == 0) {
                //系统创建员工编号
                String empCode = coderuleService.createCoder(companyId, "vmes_employee", "E");
                employee.setCode(empCode);
            } else if (code != null && code.trim().length() > 0) {
                employee.setCode(code);
            }

            //email 邮箱
            String email = mapObject.get("email");
            if (email != null && email.trim().length() > 0) {
                employee.setEmail(email);
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

            //contractDate 合同到期
            String contractDate = mapObject.get("contractDate");
            if (contractDate != null && contractDate.trim().length() > 0) {
                Date date = DateFormat.dateString2Date(contractDate, DateFormat.DEFAULT_DATE_FORMAT);
                if (date != null) {
                    employee.setContractDate(date);
                }
            }

            //nativePlace 籍贯
            String nativePlace = mapObject.get("nativePlace");
            employee.setNativePlace(nativePlace);

            //politicalName 政治面貌 political 政治面貌id
            String political = new String();
            String politicalName = mapObject.get("politicalName");
            if (sysPoliticalMap != null && sysPoliticalMap.get(politicalName) != null) {
                political = sysPoliticalMap.get(politicalName).trim();
            }
            employee.setPolitical(political);

            //remark 备注
            String remark = mapObject.get("remark");
            employee.setRemark(remark);

            //工作位置 position
            String position = new String();
            if (mapObject.get("position") != null) {
                position = mapObject.get("position").trim();
            }
            employee.setPosition(position);

            //宿舍 dormitory
            String dormitory = new String();
            if (mapObject.get("dormitory") != null) {
                dormitory = mapObject.get("dormitory").trim();
            }
            employee.setDormitory(dormitory);

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取部门Map结构体
     * 部门Map结构体: <部门名称, 部门id>
     *
     * @param companyId
     * @return
     */
    private Map<String, String> findDepartmentMapBySystem(String companyId) throws Exception {
        Map<String, String> departmentMap = new LinkedHashMap<>();
        if (companyId == null || companyId.trim().length() == 0) {return departmentMap;}

        //查询部门表 vmes_warehouse
        PageData findMap = new PageData();
        //pid:企业id
        findMap.put("pid", companyId);
        //organizeType 组织类型(1:公司 2:部门)
        findMap.put("organizeType", "2");
        //layer:2: 一级部门
        findMap.put("layer", "2");
        //是否启用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> departmentList = departmentService.findDepartmentList(findMap);
        if (departmentList == null || departmentList.size() == 0) {return departmentMap;}

        for (Department deptObject : departmentList) {
            //name 部门名称
            String name = deptObject.getName();
            //id 部门id
            String id = deptObject.getId();

            departmentMap.put(name, id);
        }

        return departmentMap;
    }

    private Map<String, String> findDepartmentMapByExcel(List<LinkedHashMap<String, String>> objectList) {
        Map<String, String> departmentMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return departmentMap;}

        for (Map<String, String> mapObject : objectList) {
            //deptName 部门名称(一级部门名称)
            String deptName = mapObject.get("deptName");

            departmentMap.put(deptName, deptName);
        }

        return departmentMap;
    }

    private void addDepartment(Map<String, String> excelDepartmentMap,
                               Map<String, String> sysDepartmentMap,
                               String companyId, String userId) throws Exception {
        if (excelDepartmentMap == null) {return;}

        //1.(部门名称,一级部门名称) 与系统仓库表结构体比较 是否存在
        List<String> addDepartmentList = new ArrayList();
        for (Iterator iterator = excelDepartmentMap.keySet().iterator(); iterator.hasNext();) {
            //(部门名称,一级部门名称)
            String mapKey = iterator.next().toString().trim();
            if (sysDepartmentMap.get(mapKey) == null) {
                addDepartmentList.add(mapKey);
            }
        }

        if (addDepartmentList.size() > 0) {
            String pid = companyId;
            Department paterObj = departmentService.findDepartmentById(pid);

            for (String departmentValue : addDepartmentList) {
                Department addDepartment = new Department();

                //创建部门信息
                addDepartment.setId(Conv.createUuid());
                addDepartment.setPid(pid);
                //组织类型(1:公司 2:部门)
                addDepartment.setOrganizeType("2");
                addDepartment.setCuser(userId);
                addDepartment.setName(departmentValue);

                //获取部门编码
                String code = departmentService.createCoder(companyId);
                addDepartment.setCode(code);

                addDepartment = departmentService.id2DepartmentByLayer(addDepartment.getId(),
                        Integer.valueOf(paterObj.getLayer().intValue() + 1),
                        addDepartment);
                addDepartment = departmentService.paterObject2ObjectDB(paterObj, addDepartment);

                //设置默认部门顺序
                if (addDepartment.getSerialNumber() == null) {
                    Integer maxCount = departmentService.findMaxSerialNumber(addDepartment.getPid());
                    addDepartment.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
                }
                departmentService.save(addDepartment);

                //创建部门岗位信息
                //负责人(岗位)
                String code_1 = coderuleService.createCoder(companyId,"vmes_post","P");
                Post post_1 = new Post();
                post_1.setDeptId(addDepartment.getId());
                post_1.setName("负责人");
                post_1.setCompanyId(companyId);
                post_1.setCode(code_1);
                post_1.setCuser(userId);
                post_1.setRemark("负责人(岗位)-创建部门-系统自动创建");
                postService.save(post_1);

                //员工(岗位)
                String code_2 = coderuleService.createCoder(companyId,"vmes_post","P");
                Post post_2 = new Post();
                post_2.setDeptId(addDepartment.getId());
                post_2.setName("员工");
                post_2.setCompanyId(companyId);
                post_2.setCode(code_2);
                post_2.setCuser(userId);
                post_2.setRemark("员工(岗位)-创建部门-系统自动创建");
                postService.save(post_2);
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //部门岗位结构体:<部门名称_岗位名称, 部门岗位Map>
    //部门岗位Map:
    //  deptName 部门名称
    //  postName 岗位名称
    //  postId   岗位id
    private void addDepartmentPost(List<LinkedHashMap<String, String>> objectList,
                                                               Map<String, Map<String, String>> deptPostMap,
                                                               String companyId,
                                                               String userId) throws Exception {
        if (objectList == null || objectList.size() == 0) {return;}

        for (Map<String, String> mapObject : objectList) {
            Map<String, String> deptPostValueMap = new HashMap();

            //deptName 部门名称
            String deptName = mapObject.get("deptName");
            deptPostValueMap.put("deptName", deptName);

            //postName 岗位
            String postName = mapObject.get("postName");
            deptPostValueMap.put("postName", postName);

            //mapKey:部门名称_岗位
            String mapKey = deptName + "_" + postName;

            deptPostMap.put(mapKey, deptPostValueMap);
        }

        if (deptPostMap.size() > 0) {
            //部门Map结构体: <部门名称, 部门id>
            Map<String, String> sysDepartmentMap = this.findDepartmentMapBySystem(companyId);

            for (Iterator iterator = deptPostMap.keySet().iterator(); iterator.hasNext();) {
                //mapKey:部门名称_岗位
                String mapKey = (String)iterator.next();
                Map<String, String> deptPostValueMap = deptPostMap.get(mapKey);

                //deptName 部门名称
                String deptName = deptPostValueMap.get("deptName");
                //deptId 部门id
                String deptId = new String();
                if (sysDepartmentMap != null && sysDepartmentMap.get(deptName) != null) {
                    deptId = sysDepartmentMap.get(deptName).trim();
                }

                //postName 岗位
                String postName = deptPostValueMap.get("postName");

                if (deptId != null && deptId.trim().length() > 0
                    && postName != null && postName.trim().length() > 0
                ) {
                    //(部门id,岗位名称)查询岗位表(vmes_post)
                    PageData findMap = new PageData();
                    findMap.put("deptId", deptId);
                    findMap.put("name", postName);
                    //是否禁用(0:已禁用 1:启用)
                    findMap.put("isdisable", "1");
                    findMap.put("mapSize", Integer.valueOf(findMap.size()));
                    Post postDB = postService.findPost(findMap);
                    if (postDB != null) {
                        deptPostValueMap.put("postId", postDB.getId());
                    } else if (postDB == null) {
                        Post addPost = new Post();
                        addPost.setName(postName);
                        addPost.setDeptId(deptId);
                        //获取岗位编号
                        String code = coderuleService.createCoder(companyId,"vmes_post","P");
                        addPost.setCode(code);
                        addPost.setCompanyId(companyId);
                        addPost.setCuser(userId);
                        postService.save(addPost);

                        deptPostValueMap.put("postId", addPost.getId());
                    }
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> findPoliticalMapByExcel(List<LinkedHashMap<String, String>> objectList) {
        Map<String, String> politicalMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return politicalMap;}

        for (Map<String, String> mapObject : objectList) {
            //politicalName 政治面貌(允许为空)
            String politicalName = mapObject.get("politicalName");

            if (politicalName != null && politicalName.trim().length() > 0) {
                politicalMap.put(politicalName, politicalName);
            }
        }

        return politicalMap;
    }

    private void addPolitical(Map<String, String> excelPoliticalMap,
                              Map<String, String> sysPoliticalMap,
                              String companyId,
                              String userId) throws Exception {
        if (excelPoliticalMap == null) {return;}

        //1.(政治面貌名称) 与系统字典表结构体比较 是否存在
        List<String> addPoliticalList = new ArrayList();
        for (Iterator iterator = excelPoliticalMap.keySet().iterator(); iterator.hasNext();) {
            //政治面貌名称
            String mapKey = iterator.next().toString().trim();
            if (sysPoliticalMap.get(mapKey) == null) {
                addPoliticalList.add(mapKey);
            }
        }

        if (addPoliticalList.size() > 0) {
            //政治面貌:political:pid:015cecdb7fdd450c8a21c7c97d406aa4
            String pid = Common.DICTIONARY_MAP.get("political");
            Dictionary paterObj = dictionaryService.findDictionaryById(pid);

            for (String politicalName : addPoliticalList) {
                //创建字典信息
                Dictionary addDictionary = new Dictionary();
                addDictionary.setId(Conv.createUuid());
                addDictionary.setCompanyId(companyId);
                addDictionary.setCuser(userId);
                addDictionary.setName(politicalName);

                //isglobal: 0：否  1：是
                //0:非全局设置 1:是全局(超级管理员创建的数据字典都是全局设置)
                addDictionary.setIsglobal("0");

                addDictionary = dictionaryService.id2DictionaryByLayer(addDictionary.getId(),
                        Integer.valueOf(paterObj.getLayer().intValue() + 1),
                        addDictionary);
                addDictionary = dictionaryService.paterObject2ObjectDB(paterObj, addDictionary);

                //设置默认部门顺序
                if (addDictionary.getSerialNumber() == null) {
                    Integer maxCount = dictionaryService.findMaxSerialNumber(addDictionary.getPid());
                    addDictionary.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
                }
                dictionaryService.save(addDictionary);
            }
        }
    }


}
