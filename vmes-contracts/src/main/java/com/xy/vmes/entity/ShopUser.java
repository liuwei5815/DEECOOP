package com.xy.vmes.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/** 
 * 说明：vmes_shop_user:商城平台用户管理 实体类
 * @author 刘威 自动生成
 * @date 2019-12-23
 */
@TableName("vmes_shop_user")
public class ShopUser implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableField("id")
	private String id;
	//用户账号
	@TableField("username")
	private String username;
	//昵称
	@TableField("nike_name")
	private String nikeName;
	//用户密码
	@TableField("password")
	private String password;
	//支付密码
	@TableField("paypassword")
	private String paypassword;
	//手机号码
	@TableField("mobile")
	private String mobile;
	//邮箱
	@TableField("email")
	private String email;
	//性别::0:男，1:女
	@TableField("sex")
	private Integer sex;
	//身份证号
	@TableField("card")
	private String card;
	//用户余额
	@TableField("amount")
	private BigDecimal amount;
	//月收入
	@TableField("monthly_income")
	private BigDecimal monthlyIncome;
	//用户头像
	@TableField("user_image")
	private String userImage;
	//出生年月
	@TableField("birthday")
	private Date birthday;
	//注册方式
	@TableField("create_type")
	private Integer createType;
	//居住地址
	@TableField("address")
	private String address;
	//详细地址
	@TableField("detail_address")
	private String detailAddress;
	//会员等级
	@TableField("member_ugrade")
	private Integer memberUgrade;
	//用户类型 1：普通用户，2：活动会员,3：月卡会员 4:季卡会员 5：年卡会员
	@TableField("member_type")
	private String memberType;
	//最后登陆日期
	@TableField("last_login_date")
	private Date lastLoginDate;
	//公司ID-组织架构
	@TableField("company_id")
	private String companyId;
	//微信头像
	@TableField("wechat_img")
	private String wechatImg;
	//微信昵称
	@TableField("wechat_nickname")
	private String wechatNickname;
	//websocket的会话id
	@TableField("wx_openid")
	private String wxOpenid;
	//微信登录唯一编号
	@TableField("wx_unionid")
	private String wxUnionid;
	//是否启用
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



	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}
	public String getPaypassword() {
		return paypassword;
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
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getSex() {
		return sex;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCard() {
		return card;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	public Integer getCreateType() {
		return createType;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setMemberUgrade(Integer memberUgrade) {
		this.memberUgrade = memberUgrade;
	}
	public Integer getMemberUgrade() {
		return memberUgrade;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setWechatImg(String wechatImg) {
		this.wechatImg = wechatImg;
	}
	public String getWechatImg() {
		return wechatImg;
	}
	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}
	public String getWechatNickname() {
		return wechatNickname;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxUnionid(String wxUnionid) {
		this.wxUnionid = wxUnionid;
	}
	public String getWxUnionid() {
		return wxUnionid;
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

/*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


}
