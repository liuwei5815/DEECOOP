package com.xy.vmes.deecoop.system.serviceImp;

import com.yvan.common.util.Common;
import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.DeptExcelEntity;
import com.xy.vmes.deecoop.system.service.DepartmentExcelService;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.yvan.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
public class DepartmentExcelServiceImp implements DepartmentExcelService {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DictionaryService dictionaryService;

    public List<DeptExcelEntity> mapList2ImportExcelList(List<LinkedHashMap<String, String>> mapList, List<DeptExcelEntity> excelList) {
        if (excelList == null) {excelList = new ArrayList<DeptExcelEntity>();}
        if (mapList == null || mapList.size() <= 1) {return excelList;}

        for (int i = 1; i < mapList.size(); i++) {
            LinkedHashMap mapObject = mapList.get(i);
            DeptExcelEntity excelEntity = this.map2ExcelEntity(mapObject, null);
            excelList.add(excelEntity);
        }

        return excelList;
    }

    public String findDeptNampPath(DeptExcelEntity excelEntity) {
        StringBuffer namePath = new StringBuffer();
        if (excelEntity == null) {return namePath.toString();}

        if (excelEntity.getDeptName_1() != null && excelEntity.getDeptName_1().trim().length() > 0) {
            namePath.append(excelEntity.getDeptName_1().trim());
            namePath.append("-");
        }
        if (excelEntity.getDeptName_2() != null && excelEntity.getDeptName_2().trim().length() > 0) {
            namePath.append(excelEntity.getDeptName_2().trim());
            namePath.append("-");
        }
        if (excelEntity.getDeptName_3() != null && excelEntity.getDeptName_3().trim().length() > 0) {
            namePath.append(excelEntity.getDeptName_3().trim());
            namePath.append("-");
        }

        //去掉最后一个"-"
        String strTmp = "";
        if (namePath.toString().trim().length() > 0 && namePath.toString().trim().lastIndexOf("-") != -1) {
            strTmp = namePath.toString().trim();
            strTmp = strTmp.substring(0, strTmp.lastIndexOf("-"));
        }

        return strTmp;
    }

    public DeptExcelEntity map2ExcelEntity(LinkedHashMap<String, String> mapObject, DeptExcelEntity excelEntity) {
        if (excelEntity == null) {excelEntity = new DeptExcelEntity();}
        if (mapObject == null || mapObject.size() == 0) {return excelEntity;}

        //deptTypeName 组织类型名称
        excelEntity.setDeptTypeName(mapObject.get("deptTypeName"));
        //deptName_1 一级部门
        excelEntity.setDeptName_1(mapObject.get("deptName_1"));
        //deptName_2 二级部门
        excelEntity.setDeptName_2(mapObject.get("deptName_2"));
        //deptName_3 三级部门
        excelEntity.setDeptName_3(mapObject.get("deptName_3"));
        //remark 备注
        excelEntity.setRemark(mapObject.get("remark"));

        return excelEntity;
    }

    public String checkColumnImportExcel(List<DeptExcelEntity> objectList,
                                  String companyId,
                                  Integer index,
                                  Integer maxShowRow) {
        StringBuffer strBuf = new StringBuffer();

        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //deptType 部门类型(deptType:pid:8421e4f093a44f029dddbc4ab13068be)-查询字典表(vmes_dictionary)
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("deptType"), null);
        Map<String, String> nameKeyMap = dictionaryService.getNameKeyMap();


        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_dept_isnull_1 = "第 {0} 行: ({1})输入为空或空字符串，({2})请至少输入一项！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_dept_isnull_2 = "第 {0} 行: ({1})输入错误，({2})输入为空或空字符串，({2})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        String msg_column_error_1 = "第 {0} 行: ({1}:{2})输入错误，请填写正确的(组织类型)！";

        int maxRow = 0;
        for (int i = 0; i < objectList.size(); i++) {
            DeptExcelEntity excelEntity = objectList.get(i);

            //1. 非空判断
            //deptTypeName 组织类型
            if (excelEntity.getDeptTypeName() == null || excelEntity.getDeptTypeName().trim().length() == 0) {
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int) ,
                        "组织类型");
                strBuf.append(str_isnull);
                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //一级部门 二级部门 三级部门 共有8种组合
            //下面三种情况是允许的
            //一级部门(非空) 二级部门(空) 三级部门(空)
            //一级部门(非空) 二级部门(非空) 三级部门(空)
            //一级部门(非空) 二级部门(非空) 三级部门(非空)

            //下面5种情况是异常非空判断
            if((excelEntity.getDeptName_1() == null || excelEntity.getDeptName_1().trim().length() == 0)
                    && (excelEntity.getDeptName_2() == null || excelEntity.getDeptName_2().trim().length() == 0)
                    && (excelEntity.getDeptName_3() == null || excelEntity.getDeptName_3().trim().length() == 0)
            ) {
                //(1)一级部门(空) 二级部门(空) 三级部门(空)
                //第 {0} 行: (部门名称)输入为空或空字符串，(一级部门,二级部门,三级部门)请至少输入一项！";
                String str_isnull = MessageFormat.format(msg_dept_isnull_1,
                        (i+index_int),
                        "部门名称",
                        "一级部门,二级部门,三级部门");
                strBuf.append(str_isnull);
                maxRow = maxRow + 1;
            } else if ((excelEntity.getDeptName_1() == null || excelEntity.getDeptName_1().trim().length() == 0)
                    && (excelEntity.getDeptName_2() == null || excelEntity.getDeptName_2().trim().length() == 0)
                    && (excelEntity.getDeptName_3() != null && excelEntity.getDeptName_3().trim().length() > 0)
                    ) {
                //(2)一级部门(空) 二级部门(空) 三级部门(非空)
                //第 {0} 行: (部门名称)输入错误，(一级部门,二级部门)输入为空或空字符串，(一级部门,二级部门)是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_dept_isnull_2,
                        (i+index_int),
                        "部门名称",
                        "一级部门,二级部门");
                strBuf.append(str_isnull);
                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if((excelEntity.getDeptName_1() == null || excelEntity.getDeptName_1().trim().length() == 0)
                    && (excelEntity.getDeptName_2() != null && excelEntity.getDeptName_2().trim().length() > 0)
            ) {
                //(3)一级部门(空) 二级部门(非空) 三级部门(非空)
                //(4)一级部门(空) 二级部门(非空) 三级部门(空)
                //第 {0} 行: (部门名称)输入错误，(一级部门)输入为空或空字符串，(一级部门)是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_dept_isnull_2,
                        (i+index_int),
                        "部门名称",
                        "一级部门");
                strBuf.append(str_isnull);
                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if((excelEntity.getDeptName_1() != null && excelEntity.getDeptName_1().trim().length() > 0)
                    && (excelEntity.getDeptName_2() == null || excelEntity.getDeptName_2().trim().length() == 0)
                    && (excelEntity.getDeptName_3() != null && excelEntity.getDeptName_3().trim().length() > 0)
            ) {
                //(5)一级部门(空) 二级部门(非空) 三级部门(空)
                //第 {0} 行: (部门名称)输入错误，(二级部门)输入为空或空字符串，(二级部门)是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_dept_isnull_2,
                        (i+index_int),
                        "部门名称",
                        "二级部门");
                strBuf.append(str_isnull);
                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }
            //remark 备注(允许为空)

            //deptTypeName 组织类型
            String deptTypeName = excelEntity.getDeptTypeName();
            if (deptTypeName != null && deptTypeName.trim().length() > 0) {
                if (nameKeyMap.get(deptTypeName.trim()) == null) {
                    //"第 {0} 行: ({1}:{2})输入错误，请填写正确的(组织类型)！"
                    String str_error = MessageFormat.format(msg_column_error_1,
                            (i+index_int),
                            "组织类型",
                            deptTypeName.trim());
                    strBuf.append(str_error);
                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else {
                    excelEntity.setDeptType(nameKeyMap.get(deptTypeName.trim()));
                }
            }

            //id1 企业id
            excelEntity.setId1(companyId);
        }

        return strBuf.toString();
    }

    public String checkExistImportExcelBySelf(List<DeptExcelEntity> excelList, Integer index, Integer maxShowRow) {
        StringBuffer strBuf = new StringBuffer();
        if (excelList == null || excelList.size() == 0) {return strBuf.toString();}

        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        String msg_column_exist_1 = "第 {0} 行: ({1}:{2})输入重复，与(第 {3} 行)重复！" + Common.SYS_ENDLINE_DEFAULT;

        //(一级部门,二级部门,三级部门) 唯一性判断
        int maxRow = 0;
        Map<String, Integer> objectMap = new HashMap<String, Integer>();
        for (int i = 0; i < excelList.size(); i++) {
            DeptExcelEntity excelEntity = excelList.get(i);
            String deptNampPath = this.findDeptNampPath(excelEntity);

            if (objectMap.get(deptNampPath) != null) {
                //"第 {0} 行: (部门名称:{2})输入重复，与(第 {3} 行)重复！"
                String str_error = MessageFormat.format(msg_column_exist_1,
                        (i+index_int),
                        "部门名称",
                        deptNampPath,
                        objectMap.get(deptNampPath).toString());
                strBuf.append(str_error);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else {
                objectMap.put(deptNampPath, Integer.valueOf(i + index_int));
            }
        }
        return strBuf.toString();
    }

    public String checkExistImportExcelByDatabase(
            List<DeptExcelEntity> excelList,
            String companyId,
            Integer index,
            Integer maxShowRow) {
        StringBuffer strBuf = new StringBuffer();
        if (excelList == null || excelList.size() == 0) {return strBuf.toString();}

        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //根据企业id-获取企业部门对象
        Department paterObj = departmentService.findDepartmentById(companyId);
        String pater_LongName = paterObj.getLongName();

        String msg_column_exist_1 = "第 {0} 行: ({1}:{2})-在({3})中已存在！" + Common.SYS_ENDLINE_DEFAULT;

        int maxRow = 0;
        Map<String, Integer> objectMap = new HashMap<String, Integer>();
        for (int i = 0; i < excelList.size(); i++) {
            DeptExcelEntity excelEntity = excelList.get(i);
            String deptNampPath = this.findDeptNampPath(excelEntity);

            //(一级部门,二级部门,三级部门) 查询(vmes_department)是否存在
            PageData findMap = new PageData();
            findMap.put("longName", pater_LongName + "-" + deptNampPath);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<Department> deptList = departmentService.findDepartmentList(findMap);

            if (deptList != null && deptList.size() > 0) {
                //"第&nbsp;{0}&nbsp;行:&nbsp;({1}:{2})在系统中已存在！<br/>"
                String str_error = MessageFormat.format(msg_column_exist_1,
                        (i+index_int),
                        "部门名称",
                        deptNampPath,
                        paterObj.getName());
                strBuf.append(str_error);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }
        }

        return strBuf.toString();
    }

    public void addImportExcelByList(List<DeptExcelEntity> excelList,
                                       String cuser,
                                       String companyId) {
        if (excelList == null || excelList.size() == 0) {return;}

        Department parent = departmentService.findDepartmentById(companyId);
        for (int i = 0; i < excelList.size(); i++) {
            DeptExcelEntity excelEntity = excelList.get(i);

            List<String> nameList = new ArrayList<String>();
            //一级部门
            if (excelEntity.getDeptName_1() != null && excelEntity.getDeptName_1().trim().length() > 0) {
                nameList.add(excelEntity.getDeptName_1().trim());
            }
            //二级部门
            if (excelEntity.getDeptName_2() != null && excelEntity.getDeptName_2().trim().length() > 0) {
                nameList.add(excelEntity.getDeptName_2().trim());
            }
            //三级部门
            if (excelEntity.getDeptName_3() != null && excelEntity.getDeptName_3().trim().length() > 0) {
                nameList.add(excelEntity.getDeptName_3().trim());
            }

            departmentService.addBusinessByNameList(cuser,
                    parent,
                    excelEntity,
                    nameList,
                    nameList.size());
        }
    }

}
