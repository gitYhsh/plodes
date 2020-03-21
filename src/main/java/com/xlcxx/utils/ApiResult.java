package com.xlcxx.utils;


import java.util.HashMap;
/**
 * Description: plodes
 * Created by yhsh on 2020/3/21 14:08
 * version 2.0
 * 方法说明
 */


public class ApiResult extends HashMap<String, Object> {

	private static final long serialVersionUID = -8713837118340960775L;

	// 成功
	private static final Integer SUCCESS = 200;
	// 警告
	// 异常 失败
	private static final Integer FAIL = 500;

	public ApiResult() {
		put("code", SUCCESS);
		put("msg", "操作成功");
	}

	public static ApiResult error(Object msg) {
		ApiResult responseBo = new ApiResult();
		responseBo.put("code", FAIL);
		responseBo.put("msg", msg);
		return responseBo;
	}

	public static ApiResult ok(Object msg) {
		ApiResult responseBo = new ApiResult();
		responseBo.put("code", SUCCESS);
		responseBo.put("msg", msg);
		return responseBo;
	}


}
