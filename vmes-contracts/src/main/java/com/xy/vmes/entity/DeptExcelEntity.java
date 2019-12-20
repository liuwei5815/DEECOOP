package com.xy.vmes.entity;

/**
 * 组织管理-Excel导入实体类
 * 与Excel导入模板(第一行隐藏)名称对应
 * /vmes-file/fileUpload/excelTemplet/组织管理.xlsx
 */
public class DeptExcelEntity {
    //企业id
    private String id1;
    //组织类型
    private String deptTypeName;
    //部门类型 数据字典id
    private String deptType;

    //一级部门
    private String deptName_1;
    private String id2;

    //二级部门
    private String deptName_2;
    private String id3;

    //三级部门
    private String deptName_3;
    private String id4;

    //备注
    private String remark;


    public String getId1() {
        return id1;
    }
    public void setId1(String id1) {
        this.id1 = id1;
    }
    public String getDeptTypeName() {
        return deptTypeName;
    }
    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }
    public String getDeptType() {
        return deptType;
    }
    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }
    public String getDeptName_1() {
        return deptName_1;
    }
    public void setDeptName_1(String deptName_1) {
        this.deptName_1 = deptName_1;
    }
    public String getId2() {
        return id2;
    }
    public void setId2(String id2) {
        this.id2 = id2;
    }
    public String getDeptName_2() {
        return deptName_2;
    }
    public void setDeptName_2(String deptName_2) {
        this.deptName_2 = deptName_2;
    }
    public String getId3() {
        return id3;
    }
    public void setId3(String id3) {
        this.id3 = id3;
    }
    public String getDeptName_3() {
        return deptName_3;
    }
    public void setDeptName_3(String deptName_3) {
        this.deptName_3 = deptName_3;
    }
    public String getId4() {
        return id4;
    }
    public void setId4(String id4) {
        this.id4 = id4;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
