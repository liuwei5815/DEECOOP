package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_employee:员工管理 实体类
 * @author 刘威 自动生成
 * @date 2018-08-02
 */
@TableName("vmes_employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//系统用户ID
	@TableField("user_id")
	private String userId;
	//公司ID-组织架构
	@TableField("company_id")
	private String companyId;
	//员工编号
	@TableField("code")
	private String code;
	//员工类型 数据字典:vmes_dictionary
	@TableField("type")
	private String type;
	//员工姓名
	@TableField("name")
	private String name;
	//员工英文名
	@TableField("name_en")
	private String nameEn;
	//员工照片
	@TableField("photo")
	private String photo;
	//员工图片
	@TableField("icon")
	private String icon;
	//手机号码
	@TableField("mobile")
	private String mobile;
	//邮箱地址
	@TableField("email")
	private String email;
	//性别(1:男0:女) 
	@TableField("sex")
	private String sex;
	//出生日期(yyyy-mm-dd)
	@TableField("birthday")
	private Date birthday;
	//离职日期(yyyy-mm-dd)
	@TableField("leave_date")
	private Date leaveDate;
	//入职日期(yyyy-mm-dd)
	@TableField("entry_date")
	private Date entryDate;
	//籍贯
	@TableField("native_place")
	private String nativePlace;
	//政治面貌 数据字典:vmes_dictionary
	@TableField("political")
	private String political;
	//身份证号
	@TableField("identity_number")
	private String identityNumber;
	//婚姻状况(1:已婚 0:未婚) 
	@TableField("marital")
	private String marital;
	//是否禁用(0:已禁用 1:启用)
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
	//预留字段
	@TableField("column_1")
	private String column1;
	//预留字段
	@TableField("column_2")
	private String column2;
	//预留字段
	@TableField("column_3")
	private String column3;
	//备注
	@TableField("remark")
	private String remark;
	//是否开通用户(0:不开通 1:开通 is null 不开通)
	@TableField("isOpenUser")
	private String isOpenUser;
	//合同到期日期(yyyy-mm-dd)
	@TableField("contract_date")
	private Date contractDate;

	//工作位置
	@TableField("position")
	private String position;
	//宿舍
	@TableField("dormitory")
	private String dormitory;


	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
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
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getPolitical() {
		return political;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public String getMarital() {
		return marital;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsOpenUser() {
		return isOpenUser;
	}
	public void setIsOpenUser(String isOpenUser) {
		this.isOpenUser = isOpenUser;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
