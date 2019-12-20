package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_coderule:系统编码规则表 实体类
 * @author 陈刚 自动生成
 * @date 2018-07-26
 */
@TableName("vmes_coderule")
public class Coderule implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//公司ID-组织架构
	@TableField("company_id")
	private String companyId;
	//业务名称 (表名)
	@TableField("table_name")
	private String tableName;
	//当前使用Code (前补零)
	@TableField("code")
	private String code;
	//业务编码
	@TableField("business_code")
	private String businessCode;
	//计划信息表produce_workflow
	@TableField("type")
	private String type;
	//创建时间
	@TableField("cdate")
	private Date cdate;
	//修改时间
	@TableField("udate")
	private Date udate;
	//mysql乐观锁
	@TableField("version")
	private Integer version;



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getVersion() {
		return version;
	}

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
