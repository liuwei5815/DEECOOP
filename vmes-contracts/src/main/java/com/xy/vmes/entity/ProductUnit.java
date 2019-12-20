package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：产品计价单位 实体类
 * @author 刘威 自动生成
 * @date 2018-11-15
 */
@TableName("vmes_product_unit")
public class ProductUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//产品(vmes_product.id)
	@TableField("product_id")
	private String productId;
	//计价单位(字典表-vmes_dictionary.id)
	@TableField("unit")
	private String unit;
	//编码
	@TableField("code")
	private String code;
	//名称
	@TableField("name")
	private String name;

	//计量单位转换计价单位
	@TableField("np_formula")
	private String npFormula;
	//计价单位转换计量单位
	@TableField("pn_formula")
	private String pnFormula;
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
	//备注
	@TableField("remark")
	private String remark;
	//货品单价
	@TableField("product_price")
	private BigDecimal productPrice;
	//是否默认 (1:默认 0:非默认)
	@TableField("isdefault")
	private String isdefault;

	//单位类型 (1:计量单位 0:计价单位)
	@TableField("type")
	private String type;

	//n2p:计量单位转换计价单位 是否需要四舍五入(Y:需要四舍五入 N:无需四舍五入)
	@TableField("n2p_is_scale")
	private String n2pIsScale;
	//n2p:计量单位转换计价单位 小数位数 (最小:0位 最大:4位)
	@TableField("n2p_decimal_count")
	private Integer n2pDecimalCount;

	//'p2n:计价单位转换计量单位 是否需要四舍五入(Y:需要四舍五入 N:无需四舍五入)
	@TableField("p2n_is_scale")
	private String p2nIsScale;
	//小数位数 (最小:0位 最大:4位)
	@TableField("p2n_decimal_count")
	private Integer p2nDecimalCount;


	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductId() {
		return productId;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return unit;
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
	public void setNpFormula(String npFormula) {
		this.npFormula = npFormula;
	}
	public String getNpFormula() {
		return npFormula;
	}
	public void setPnFormula(String pnFormula) {
		this.pnFormula = pnFormula;
	}
	public String getPnFormula() {
		return pnFormula;
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
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getN2pIsScale() {
		return n2pIsScale;
	}

	public void setN2pIsScale(String n2pIsScale) {
		this.n2pIsScale = n2pIsScale;
	}

	public Integer getN2pDecimalCount() {
		return n2pDecimalCount;
	}

	public void setN2pDecimalCount(Integer n2pDecimalCount) {
		this.n2pDecimalCount = n2pDecimalCount;
	}

	public String getP2nIsScale() {
		return p2nIsScale;
	}

	public void setP2nIsScale(String p2nIsScale) {
		this.p2nIsScale = p2nIsScale;
	}

	public Integer getP2nDecimalCount() {
		return p2nDecimalCount;
	}

	public void setP2nDecimalCount(Integer p2nDecimalCount) {
		this.p2nDecimalCount = p2nDecimalCount;
	}
/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
