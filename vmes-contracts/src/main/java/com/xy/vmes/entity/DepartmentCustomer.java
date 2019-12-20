package com.xy.vmes.entity;

import java.util.Date;

public class DepartmentCustomer {
    private String id;
    private String name;
    private String name_en;
    private Date cdate;
    private String cuser;

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
    public String getName_en() {
        return name_en;
    }
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
    public Date getCdate() {
        return cdate;
    }
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    public String getCuser() {
        return cuser;
    }
    public void setCuser(String cuser) {
        this.cuser = cuser;
    }
}
