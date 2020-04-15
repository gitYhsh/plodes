package com.xlcxx.config.auth.damain;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/20 13:39
 * version 2.0
 * 方法说明
 */
@ConfigurationProperties(prefix = "xlcxx.security")
public class SecurityProperties {

	/**userAnd password login**/
	private String loginProcessingUrl;

	/**免认证,免token 得url**/
	private String anonResourcesUrl;


	//最大并发量(目前不适用)
	private int  maxLoginNum;


	private SmsCodeProperties sms = new SmsCodeProperties();

	public String getAnonResourcesUrl() {
		return anonResourcesUrl;
	}

	public void setAnonResourcesUrl(String anonResourcesUrl) {
		this.anonResourcesUrl = anonResourcesUrl;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}
	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}

	public int getMaxLoginNum() {
		return maxLoginNum;
	}

	public void setMaxLoginNum(int maxLoginNum) {
		this.maxLoginNum = maxLoginNum;
	}

	public String getLoginProcessingUrl() {
		return loginProcessingUrl;
	}

	public void setLoginProcessingUrl(String loginProcessingUrl) {
		this.loginProcessingUrl = loginProcessingUrl;
	}



}
