package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_user:系统用户表 实体类
 * @author 刘威 自动生成
 * @date 2018-07-26
 */
@TableName("vmes_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//员工ID
	@TableField("employ_id")
	private String employId;
	//部门ID
	@TableField("dept_id")
	private String deptId;
	//公司ID-组织架构
	@TableField("company_id")
	private String companyId;
	//账号(系统登录账号)
	@TableField("user_code")
	private String userCode;
	//密码(MD5加密)
	@TableField("password")
	private String password;
	//用户微信ID
	@TableField("open_id")
	private String openId;
	//手机号码
	@TableField("mobile")
	private String mobile;
	//邮箱地址
	@TableField("email")
	private String email;
	//姓名
	@TableField("user_name")
	private String userName;
	//界面样式风格
	@TableField("page_style")
	private String pageStyle;
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
	//预留字段
	@TableField("column_1")
	private String column1;
	//预留字段
	@TableField("column_2")
	private String column2;
	//预留字段
	@TableField("column_3")
	private String column3;
	//用户类型(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)数据字典
	@TableField("user_type")
	private String userType;
	//备注
	@TableField("remark")
	private String remark;



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public String getEmployId() {
		return employId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return openId;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}
	public String getPageStyle() {
		return pageStyle;
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
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
