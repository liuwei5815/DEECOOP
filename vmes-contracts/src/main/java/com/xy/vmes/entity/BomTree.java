package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：操作日志 实体类
 * @author 刘威 自动生成
 * @date 2018-09-30
 */
@TableName("vmes_bom_tree")
public class BomTree implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//vmes_bom id
	@TableField("bom_id")
	private String bomId;
	//产品id
	@TableField("prod_id")
	private String prodId;
	//上级产品id
	@TableField("parent_prod_id")
	private String parentProdId;
	//级别
	@TableField("layer")
	private Integer layer;
	//用料比例
	@TableField("ratio")
	private BigDecimal ratio;
	//创建用户id
	@TableField("cuser")
	private String cuser;
	//创建时间
	@TableField("cdate")
	private Date cdate;
	//修改用户id
	@TableField("uuser")
	private String uuser;
	//修改时间
	@TableField("udate")
	private Date udate;
	//是否启用(0:已禁用 1:启用)
	@TableField("isdisable")
	private String isdisable;
	//备注
	@TableField("remark")
	private String remark;
	//路径
	@TableField("path_id")
	private String pathId;

	@TableField("isreplaceable")
	private String isreplaceable;


	public String getIsreplaceable() {
		return isreplaceable;
	}

	public void setIsreplaceable(String isreplaceable) {
		this.isreplaceable = isreplaceable;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setBomId(String bomId) {
		this.bomId = bomId;
	}
	public String getBomId() {
		return bomId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setParentProdId(String parentProdId) {
		this.parentProdId = parentProdId;
	}
	public String getParentProdId() {
		return parentProdId;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setCuser(String cuser) {
		this.cuser = cuser;
	}
	public String getCuser() {
		return cuser;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setUuser(String uuser) {
		this.uuser = uuser;
	}
	public String getUuser() {
		return uuser;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}
	public String getIsdisable() {
		return isdisable;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	public String getPathId() {
		return pathId;
	}

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
