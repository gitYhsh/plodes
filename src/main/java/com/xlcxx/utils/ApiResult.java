package com.xlcxx.utils;

import java.io.Serializable;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/20 17:01
 * version 2.0
 * 方法说明
 */
public class ApiResult implements Serializable {


	private static final long serialVersionUID = -2169445464306157587L;

	private static final Integer FAIL = 500;

	private static final Integer SUCCESS = 200;

	private int code ;
	private String msg;
	private Object data;

	private ApiResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public static ApiResult error(String  msg,Object data) {
		ApiResult apiResult = new ApiResult(FAIL,msg,data);
		return apiResult;
	}

	public static ApiResult ok(String  msg,Object data) {
		ApiResult apiResult = new ApiResult(SUCCESS,msg,data);
		return apiResult;
	}

	@Override
	public String toString() {
		return "ApiResult{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
