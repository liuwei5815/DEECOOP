package com.yvan.tencentCloudSms;

/**
 * @author Administrator
 *
 *腾讯短信模板枚举
 */
public enum TencentSmsTemplateIdEnum {
	verification(217789,"登录验证码","登录验证码为：{1}。如非本人操作，请忽略本短信。"),
	refundSuccess(255778,"退款提醒","您的：{1} 正在退款，会在一周内原路退至您的支付账号。若超时请联系钧策咨询官方客服核实。详情可咨询 [ {2} ]"),
	withdrawal(255780,"提现提醒","尊敬的：{1}，您已成功提现{2}元。详情可咨询 [ {3} ]"),
	agentApply(255531,"代理商申请","您申请的{1}代理商，审核状态为{2}"),
	noticeExpert(255532,"用户提问","提问人:{1}，刚刚向您提了一个问题。"),
	tuGoods(255774,"购买成功线下","您已成功购买：{1}，请您提前安排好工作行程，方便安心参与课程，钧策咨询全体同仁欢迎您的到来！详情可咨询 [ {2} ]"),
	tcGoods(255769,"购买成功线上","您已成功购买：{1} 课程，可以开始畅通无阻的学习了！"),
	onGoods(255768,"购买商品书籍","您已成功购买：{1}，会在1-7个工作日内发货。详情可咨询 [ {2} ]"),
	expertApply(255764,"专家申请审核成功","尊敬的{1}先生或女士，恭喜您已经成功入驻成为钧策咨询线上平台答疑专家。"),
	expert(255762,"专家申请提交成功提醒","尊敬的{1}先生或女士，钧策咨询已收到您提交的专家申请，之后工作人员会在三日内联系你核实相关信息，确认后就会进行入驻事宜。这期间，请您保证手机畅通，以防错过确认信息。详情可咨询[ {2} ]"),
	information(255756,"注册验证码","您此次注册的验证码为{1}，欢迎您入驻钧策咨询线上平台，工作人员不会向您索取，请勿透漏。");
	
	private int templateId;
	private String desc;
	private String templateContent;
	
	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	private TencentSmsTemplateIdEnum(int templateId, String desc, String templateContent) {
		this.templateId = templateId;
		this.desc = desc;
		this.templateContent = templateContent;
	}


}
