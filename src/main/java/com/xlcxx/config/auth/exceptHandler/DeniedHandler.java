package com.xlcxx.config.auth.exceptHandler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.Constant;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 14:16
 * version 2.0
 * 方法说明
 */
public class DeniedHandler implements AccessDeniedHandler {
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		httpServletResponse.setContentType(Constant.JSONUTF8);
		httpServletResponse.getWriter().write(JSONObject.toJSONString(ApiResult.error("没有权限!")));
	}
}
