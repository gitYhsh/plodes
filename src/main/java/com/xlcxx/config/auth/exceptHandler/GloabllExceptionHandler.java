package com.xlcxx.config.auth.exceptHandler;

import com.xlcxx.utils.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/14 11:30
 * version 2.0
 * 方法说明   全局错误处理
 */
@ControllerAdvice
public class GloabllExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ApiResult exceptionHandler(Exception e){
		e.printStackTrace();
		return ApiResult.error("服务器异常");
	}
}
