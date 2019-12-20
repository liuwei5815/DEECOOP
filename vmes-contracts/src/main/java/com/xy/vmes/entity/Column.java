package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_column:模块栏位表 实体类
 * @author 陈刚 自动生成
 * @date 2018-08-24
 */
@TableName("vmes_column")
public class Column implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//模块编码
	@TableField("model_code")
	private String modelCode;
	//列表Key_英文名
	@TableField("title_key")
	private String titleKey;
	//列表名称
	@TableField("title_name")
	private String titleName;
	//显示顺序
	@TableField("serial_number")
	private Integer serialNumber;
	//是否禁用(0:禁用 1:启用)
	@TableField("isdisable")
	private String isdisable;
	//是否隐藏(0:隐藏 1:显示)
	@TableField("ishide")
	private String ishide;
	//是否允许编辑(0:不允许 1:允许)
	@TableField("isedit")
	private String isedit;
	//是否必填(0:非必填 1:必填)
	@TableField("ismust")
	private String ismust;
	//创建时间
	@TableField("cdate")
	private Date cdate;
	//创建人账号
	@TableField("cuser")
	private String cuser;



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}
	public String getTitleKey() {
		return titleKey;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}
	public String getIsdisable() {
		return isdisable;
	}
	public void setIshide(String ishide) {
		this.ishide = ishide;
	}
	public String getIshide() {
		return ishide;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	public String getIsedit() {
		return isedit;
	}
	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}
	public String getIsmust() {
		return ismust;
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

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
	public Column clone() {
		Column object = null;
		try{
			object = (Column)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return object;
	}

}
