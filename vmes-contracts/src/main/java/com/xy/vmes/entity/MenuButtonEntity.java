package com.xy.vmes.entity;


public class MenuButtonEntity {
    //按钮id
    private String id;
    //按钮名称
    private String name;
    //英文名称
    private String nameEn;
    //按钮顺序
    private Integer serialNumber;
    //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
    private String isdisable;
    //当前节点-是否绑定角色(1:绑定 0:未绑定)
    private String isBindRole;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNameEn() {
        return nameEn;
    }
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getIsdisable() {
        return isdisable;
    }
    public void setIsdisable(String isdisable) {
        this.isdisable = isdisable;
    }
    public String getIsBindRole() {
        return isBindRole;
    }
    public void setIsBindRole(String isBindRole) {
        this.isBindRole = isBindRole;
    }
}
