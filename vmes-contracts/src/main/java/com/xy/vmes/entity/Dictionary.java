package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：数据字典 实体类
 * @author 刘威 自动生成
 * @date 2018-07-31
 */
@TableName("vmes_dictionary")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//上级id-根root
	@TableField("pid")
	private String pid;
	//字典编码
	@TableField("code")
	private String code;
	//字典名称
	@TableField("name")
	private String name;
	//字典排列序号
	@TableField("serial_number")
	private Integer serialNumber;
	//字典级别(最大支持5层)-头2层系统默认
	@TableField("layer")
	private Integer layer;
	//预留字段
	@TableField("column_1")
	private String column1;
	//预留字段
	@TableField("column_2")
	private String column2;
	//预留字段
	@TableField("column_3")
	private String column3;
	//根root节点ID-默认层(智能云管家)
	@TableField("id_0")
	private String id0;
	//(company:公司 system:系统)
	@TableField("id_1")
	private String id1;
	//一级节点ID
	@TableField("id_2")
	private String id2;
	//二级节点ID
	@TableField("id_3")
	private String id3;
	//三级节点ID
	@TableField("id_4")
	private String id4;
	//四级节点ID
	@TableField("id_5")
	private String id5;
	//五级节点ID
	@TableField("id_6")
	private String id6;
	//是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
	@TableField("isdisable")
	private String isdisable;
	//创建时间
	@TableField("cdate")
	private Date cdate;
	//创建人账号
	@TableField("cuser")
	private String cuser;
	//修改时间
	@TableField("udate")
	private Date udate;
	//修改人账号
	@TableField("uuser")
	private String uuser;
	//是否是全局数据字典  0：否  1：是
	//0：否 --业务数据-企业管理员创建的
	//1：是 --全员可以创建-(超级管理员-企业管理员-普通用户)
	@TableField("isglobal")
	private String isglobal;
	//所属公司
	@TableField("company_id")
	private String companyId;
	//英文名称
	@TableField("name_en")
	private String nameEn;
	//备注
	@TableField("remark")
	private String remark;



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPid() {
		return pid;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn3() {
		return column3;
	}
	public void setId0(String id0) {
		this.id0 = id0;
	}
	public String getId0() {
		return id0;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getId1() {
		return id1;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public String getId2() {
		return id2;
	}
	public void setId3(String id3) {
		this.id3 = id3;
	}
	public String getId3() {
		return id3;
	}
	public void setId4(String id4) {
		this.id4 = id4;
	}
	public String getId4() {
		return id4;
	}
	public void setId5(String id5) {
		this.id5 = id5;
	}
	public String getId5() {
		return id5;
	}
	public void setId6(String id6) {
		this.id6 = id6;
	}
	public String getId6() {
		return id6;
	}
	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}
	public String getIsdisable() {
		return isdisable;
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
	public void setIsglobal(String isglobal) {
		this.isglobal = isglobal;
	}
	public String getIsglobal() {
		return isglobal;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
