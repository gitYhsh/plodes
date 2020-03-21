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

	private String loginUrl;


	// 记住我超时时间
	private int rememberMeTimeout;

	private String loginProcessingUrl;
	//最大并发量
	private int  maxLoginNum;


	public int getMaxLoginNum() {
		return maxLoginNum;
	}

	public void setMaxLoginNum(int maxLoginNum) {
		this.maxLoginNum = maxLoginNum;
	}

	public int getRememberMeTimeout() {
		return rememberMeTimeout;
	}

	public void setRememberMeTimeout(int rememberMeTimeout) {
		this.rememberMeTimeout = rememberMeTimeout;
	}

	public String getLoginProcessingUrl() {
		return loginProcessingUrl;
	}

	public void setLoginProcessingUrl(String loginProcessingUrl) {
		this.loginProcessingUrl = loginProcessingUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
