package com.xlcxx.config.auth.damain;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/11 8:56
 * version 2.0
 * 方法说明
 */
public class SmsCodeProperties {


	private int length = 6;
	private int expireIn = 60;
	//private String createUrl = "/sms/code";
	// 处理使用短信验证码认证 URL
	private String loginProcessingUrl = "/mobile/login";


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}


	public String getLoginProcessingUrl() {
		return loginProcessingUrl;
	}

	public void setLoginProcessingUrl(String loginProcessingUrl) {
		this.loginProcessingUrl = loginProcessingUrl;
	}
}
