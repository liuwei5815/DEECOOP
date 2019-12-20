package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_department:系统部门表 实体类
 * @author 陈刚 自动生成
 * @date 2018-08-01
 */
@TableName("vmes_department")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//上级id-根root
	@TableField("pid")
	private String pid;
	//组织编码
	@TableField("code")
	private String code;
	//组织名称
	@TableField("name")
	private String name;
	//英文名称
	@TableField("name_en")
	private String nameEn;
	//是否叶子节点(1:叶子节点0:非叶子节点) 数据字典:sys_isleaf
	@TableField("isleaf")
	private String isleaf;
	//部门级别(最大支持5层)
	@TableField("layer")
	private Integer layer;
	//部门排列序号
	@TableField("serial_number")
	private Integer serialNumber;
	//组织类型(1:公司 2:部门) 数据字典:sys_organize_type
	@TableField("organize_type")
	private String organizeType;
	//公司类型 数据字典:sys_company_type
	@TableField("company_type")
	private String companyType;
	//部门类型 数据字典:sys_department_type
	@TableField("dept_type")
	private String deptType;
	//部门长名称(中间使用-链接)
	@TableField("long_name")
	private String longName;
	//部门长编码(中间使用-链接)
	@TableField("long_code")
	private String longCode;
	//公司简称
	@TableField("company_shortname")
	private String companyShortname;
	//有效期(yyyy-mm-dd)
	@TableField("company_validity_date")
	private Date companyValidityDate;
	//系统用户数
	@TableField("company_user_count")
	private Integer companyUserCount;
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
	//根root节点ID
	@TableField("id_0")
	private String id0;
	//一级节点ID
	@TableField("id_1")
	private String id1;
	//二级节点ID
	@TableField("id_2")
	private String id2;
	//三级节点ID
	@TableField("id_3")
	private String id3;
	//四级节点ID
	@TableField("id_4")
	private String id4;
	//五级节点ID
	@TableField("id_5")
	private String id5;
	//备注
	@TableField("remark")
	private String remark;
	//地区
	@TableField("area")
	private String area;


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

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
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setOrganizeType(String organizeType) {
		this.organizeType = organizeType;
	}
	public String getOrganizeType() {
		return organizeType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongCode(String longCode) {
		this.longCode = longCode;
	}
	public String getLongCode() {
		return longCode;
	}
	public void setCompanyShortname(String companyShortname) {
		this.companyShortname = companyShortname;
	}
	public String getCompanyShortname() {
		return companyShortname;
	}
	public void setCompanyValidityDate(Date companyValidityDate) {
		this.companyValidityDate = companyValidityDate;
	}
	public Date getCompanyValidityDate() {
		return companyValidityDate;
	}
	public void setCompanyUserCount(Integer companyUserCount) {
		this.companyUserCount = companyUserCount;
	}
	public Integer getCompanyUserCount() {
		return companyUserCount;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
