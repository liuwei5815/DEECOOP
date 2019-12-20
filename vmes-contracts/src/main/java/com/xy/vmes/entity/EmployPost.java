package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_employ_post:员工岗位关系表 实体类
 * @author 刘威 自动生成
 * @date 2018-08-01
 */
@TableName("vmes_employ_post")
public class EmployPost implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//员工ID
	@TableField("employ_id")
	private String employId;
	//岗位ID
	@TableField("post_id")
	private String postId;
	//是否兼岗(1:兼岗0:主岗) 数据字典:sys_isplurality
	@TableField("isplurality")
	private String isplurality;
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
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostId() {
		return postId;
	}
	public void setIsplurality(String isplurality) {
		this.isplurality = isplurality;
	}
	public String getIsplurality() {
		return isplurality;
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

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
