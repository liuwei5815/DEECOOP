package com.xy.vmes.entity;

public class DeptPostEntity {
    private String id;
    private String pid;
    private String isdisable;
    private String name;
    private String deptName;
    private String postName;
    private Integer layer;

    private Integer serialNumber;
    //"dept" 部门 "post" 岗位
    private String type;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getPostName() {
        return postName;
    }
    public void setPostName(String postName) {
        this.postName = postName;
    }
    public Integer getLayer() {
        return layer;
    }
    public void setLayer(Integer layer) {
        this.layer = layer;
    }
    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIsdisable() {
        return isdisable;
    }
    public void setIsdisable(String isdisable) {
        this.isdisable = isdisable;
    }
}
