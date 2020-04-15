package com.xlcxx.config.auth.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/14 11:49
 * version 2.0
 * 方法说明  公共类
 */
public class PlodesUtils {

	/**
	 * 简单判断是否为手机号
	 *
	 * @param phoneNo 手机号
	 * @return boolean
	 */
	public static boolean isPhoneNo(String phoneNo) {
		String regex = "[1]\\d{10}";
		if (StringUtils.isBlank(phoneNo))
			return false;
		else
			return phoneNo.matches(regex);
	}

	/**
	 * 判断是否为 AJAX 请求
	 *
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
	}

	/**
	 * 用户名密码加密解密
	 * **/
	public static String passwordEncode(String password){
		BCryptPasswordEncoder ble = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
		String passwordEncode = ble.encode(password);
		return passwordEncode;
	}

	public static Boolean isPassworMatche(String password,String encode){
		BCryptPasswordEncoder ble = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
		return  ble.matches(password,encode);
	}
}
