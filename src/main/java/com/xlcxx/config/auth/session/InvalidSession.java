package com.xlcxx.config.auth.session;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.Constant;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/20 13:53
 * version 2.0
 * 方法说明   session 失效(采用token  不适用session)
 */
public class InvalidSession implements InvalidSessionStrategy {
	@Override
	public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
		httpServletResponse.setContentType(Constant.JSONUTF8);
		httpServletResponse.getWriter().write(JSONObject.toJSONString(ApiResult.error("登陆认证已经失效，请重新登陆")));
	}
}
