package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/**
 * 说明：操作日志 实体类
 * @author 刘威 自动生成
 * @date 2018-08-28
 */
@TableName("vmes_loginfo")
public class LogInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @TableField("id")
    private String id;
    //日志类型 operate:操作日志 state:状态日志
    @TableField("type")
    private String type;
    //业务单ID
    @TableField("business_id")
    private String businessId;
    //业务类别(1:生产计划 2:产品 3:部门 4:部门子计划 5:派工单 6:子派工单)
    @TableField("business_type")
    private String businessType;
    //操作来源(app:终端操作 web:web端操作 sys:系统产生)
    @TableField("source")
    private String source;
    //模块名称
    @TableField("model_name")
    private String modelName;
    //业务操作类型 (add,update,delete)
    @TableField("operate")
    private String operate;
    //备注
    @TableField("remark")
    private String remark;
    //创建时间
    @TableField("cdate")
    private Date cdate;
    //创建人账号
    @TableField("cuser")
    private String cuser;
    //(删除,修改)-业务id字符串
    @TableField("operate_value")
    private String operateValue;
    //修改时间
    @TableField("udate")
    private Date udate;
    //修改人账号
    @TableField("uuser")
    private String uuser;
    //请求URL
    @TableField("operate_url")
    private String operateUrl;
    //企业ID
    @TableField("company_id")
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getModelName() {
        return modelName;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }
    public String getOperate() {
        return operate;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
    public Date getCdate() {
        return cdate;
    }
    public void setCuser(String cuser) {
        this.cuser = cuser;
    }
    public String getCuser() {
        return cuser;
    }
    public void setOperateValue(String operateValue) {
        this.operateValue = operateValue;
    }
    public String getOperateValue() {
        return operateValue;
    }
    public void setUdate(Date udate) {
        this.udate = udate;
    }
    public Date getUdate() {
        return udate;
    }
    public void setUuser(String uuser) {
        this.uuser = uuser;
    }
    public String getUuser() {
        return uuser;
    }
    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }
    public String getOperateUrl() {
        return operateUrl;
    }

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
