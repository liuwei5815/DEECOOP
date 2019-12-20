package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_product:产品表 实体类
 * @author 陈刚 自动生成
 * @date 2018-09-21
 */
@TableName("vmes_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//公司ID-组织架构
	@TableField("company_id")
	private String companyId;
	//二维码-(图片相对路径)
	@TableField("qrcode")
	private String qrcode;
	//产品图片
	@TableField("photo")
	private String photo;
	//产品编码
	@TableField("code")
	private String code;

	//企业货品编码
	@TableField("source_code")
	private String sourceCode;
	//产品名称
	@TableField("name")
	private String name;
	//英文名称
	@TableField("name_en")
	private String nameEn;
	//规格型号
	@TableField("spec")
	private String spec;
	//产品属性(字典表-vmes_dictionary.id)
	@TableField("genre")
	private String genre;

	//结算单位(字典表-vmes_dictionary.id)
	@TableField("last_unit")
	private String lastUnit;
	//结算比例
	@TableField("last_ratio")
	private BigDecimal lastRatio;
	//单价
	@TableField("price")
	private BigDecimal price;
	//库存数量
	@TableField("stock_count")
	private BigDecimal stockCount;
	//安全库存数量
	@TableField("safety_count")
	private BigDecimal safetyCount;

	//类型(字典表-vmes_dictionary.id)
	@TableField("type")
	private String type;
	//保存期(yyyy-mm-dd)
	@TableField("validity_date")
	private Date validityDate;
	//备注
	@TableField("remark")
	private String remark;
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
	//乐观锁
	@TableField("version")
	private Integer version;
	//保质期(天)
	@TableField("validity_days")
	private BigDecimal validityDays;

	//锁定库存数量
	@TableField("lock_count")
	private BigDecimal lockCount;

	//最后一次入库时间
	@TableField("last_in_date")
	private Date lastInDate;
	//最后一次出库时间
	@TableField("last_out_date")
	private Date lastOutDate;
	//最后一次仓库变更时间
	@TableField("last_update_date")
	private Date lastUpdateDate;
	//图号
	@TableField("picture_code")
	private String pictureCode;

	//自定义属性 (货品自定义属性字符串:'_'分隔的字符串)
	@TableField("property")
	private String property;

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
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getQrcode() {
		return qrcode;
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
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getSpec() {
		return spec;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getGenre() {
		return genre;
	}
	public void setLastUnit(String lastUnit) {
		this.lastUnit = lastUnit;
	}
	public String getLastUnit() {
		return lastUnit;
	}
	public void setLastRatio(BigDecimal lastRatio) {
		this.lastRatio = lastRatio;
	}
	public BigDecimal getLastRatio() {
		return lastRatio;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	public Date getValidityDate() {
		return validityDate;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
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
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhoto() {
		return photo;
	}
	public BigDecimal getStockCount() {
		return stockCount;
	}
	public void setStockCount(BigDecimal stockCount) {
		this.stockCount = stockCount;
	}
	public BigDecimal getSafetyCount() {
		return safetyCount;
	}
	public void setSafetyCount(BigDecimal safetyCount) {
		this.safetyCount = safetyCount;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public BigDecimal getLockCount() {
		return lockCount;
	}
	public void setLockCount(BigDecimal lockCount) {
		this.lockCount = lockCount;
	}
	public void setValidityDays(BigDecimal validityDays) {
		this.validityDays = validityDays;
	}
	public BigDecimal getValidityDays() {
		return validityDays;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public Date getLastInDate() {
		return lastInDate;
	}
	public void setLastInDate(Date lastInDate) {
		this.lastInDate = lastInDate;
	}
	public Date getLastOutDate() {
		return lastOutDate;
	}
	public void setLastOutDate(Date lastOutDate) {
		this.lastOutDate = lastOutDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getPictureCode() {
		return pictureCode;
	}
	public void setPictureCode(String pictureCode) {
		this.pictureCode = pictureCode;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
