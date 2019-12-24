package com.yvan.tencentCloudSms;

import java.io.IOException;

import com.yvan.druid.DruidAutoConfiguration;
import org.json.JSONException;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *  腾讯云，短信发送工具
 */
@Slf4j
public class TencentSmsSend {
	public static Logger logger = LoggerFactory.getLogger(TencentSmsSend.class);
	// 短信应用SDK AppID
	static int appid = 1400153877; // 1400开头
	
	// 短信应用SDK AppKey
	static String appkey = "8b76f74d9d801e45dfa8c14febdb30bd";
	
	// 需要发送短信的手机号码
	//	static String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
	
	// 短信模板ID，需要在短信应用中申请
	//	static int templateId = 7839; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
	//templateId7839对应的内容是"您的验证码是: {1}"
	// 签名
//	static String smsSign = "腾讯云"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
	static String smsSign = ""; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
	
	/**
	 * @param templateId  模板id,通过枚举传入
	 * @param phoneNumber 电话号码
	 * @param params  传入的参数，为数组，对应模板的内容
	 */
	public static void send(int templateId,String phoneNumber,String[] params) {
		try {
		    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		    SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
		        templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
			logger.info("短信發送結果：{}",result);
		}  catch (HTTPException e) {
		    // HTTP响应码错误
			logger.error("{}",e);
		}  catch (JSONException e) {
		    // json解析错误
			logger.error("{}",e);
		}  catch (IOException e) {
		    // 网络IO错误
			logger.error("{}",e);
		}  
	}
	
	public static void main(String[] args) {
		send(TencentSmsTemplateIdEnum.verification.getTemplateId(), "18627065815", new String[]{"1234"} );
	}
}

