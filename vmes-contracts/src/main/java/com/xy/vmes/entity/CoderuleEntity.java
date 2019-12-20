package com.xy.vmes.entity;

public class CoderuleEntity {
    //业务名称(表名)
    String tableName;
    //公司ID-组织架构ID
    private String companyID;
    //计划信息表produce_workflow
    //1:生产计划  2：产品  3：部门  4：部门子计划  5：派工单  6：子派工单
    String type;
    //第一个编码名称
    String firstName;
    //指定位数
    Integer length;
    //分隔符
    String separator;
    //填充字符
    String filling;
    //前缀字符
    String prefix;
    //日期格式
    String dateFormat;

    //是否需要企业编号
    Boolean isNeedCompany;
    //是否需要前缀
    Boolean isNeedPrefix;

    //是否需要日期
    Boolean isNeedDate;
    //是否需要短年份 如:2019 得到:19
    Boolean isNeedShortYear;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Boolean getIsNeedCompany() {
        return isNeedCompany;
    }
    public void setIsNeedCompany(Boolean isNeedCompany) {
        this.isNeedCompany = isNeedCompany;
    }
    public Boolean getIsNeedPrefix() {
        return isNeedPrefix;
    }
    public void setIsNeedPrefix(Boolean isNeedPrefix) {
        this.isNeedPrefix = isNeedPrefix;
    }
    public Boolean getIsNeedDate() {
        return isNeedDate;
    }
    public void setIsNeedDate(Boolean isNeedDate) {
        this.isNeedDate = isNeedDate;
    }

    public Boolean getNeedShortYear() {
        return isNeedShortYear;
    }

    public void setNeedShortYear(Boolean needShortYear) {
        isNeedShortYear = needShortYear;
    }






}
