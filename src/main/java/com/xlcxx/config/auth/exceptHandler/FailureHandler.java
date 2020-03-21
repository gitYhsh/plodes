package com.xlcxx.config.auth.exceptHandler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlcxx.utils.ApiResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 14:33
 * version 2.0
 * 方法说明
 */
@Configuration
public class FailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	                                    AuthenticationException exception) throws IOException {
		String message;
		if (exception instanceof UsernameNotFoundException) {
			message = "用户不存在！";
		} else if (exception instanceof BadCredentialsException) {
			message = "用户名或密码错误！";
		} else if (exception instanceof LockedException) {
			message = "用户已被锁定！";
		} else if (exception instanceof DisabledException) {
			message = "用户不可用！";
		} else if (exception instanceof AccountExpiredException) {
			message = "账户已过期！";
		} else if (exception instanceof CredentialsExpiredException) {
			message = "用户密码已过期！";
		} else if (exception instanceof ValidateCodeException ) {
			message = exception.getMessage();
		} else {
			message = "认证失败，请联系网站管理员！";
		}
		httpServletResponse.setContentType("application/json;charset=utf-8");
		httpServletResponse.getWriter().write(JSONObject.toJSONString(ApiResult.error(message)));
	}
}
