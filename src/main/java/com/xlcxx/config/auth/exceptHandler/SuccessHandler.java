package com.xlcxx.config.auth.exceptHandler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlcxx.config.auth.damain.AuthonUserDetails;
import com.xlcxx.config.auth.utils.JwtUtil;
import com.xlcxx.plodes.baseServices.RedisService;
import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 14:30
 * version 2.0
 * 方法说明
 */
@Configuration
public class SuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(SuccessHandler.class);

	@Autowired
	private RedisService redisService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		Object principal = authentication.getPrincipal();
		httpServletResponse.setContentType(Constant.JSONUTF8);
		if(principal instanceof AuthonUserDetails) {
			AuthonUserDetails userDetails = (AuthonUserDetails) principal;
			User user = new User();
			user.setUsername(userDetails.getUsername());

			String token = JwtUtil.createJWT(user);
			logger.error("生成得token 是: "+token);
			String result = DigestUtils.md5Hex(token);
			logger.error("加密得token 是: "+result);
			redisService.set("token:"+result,token);
			httpServletResponse.setHeader("access_token",result);
			httpServletResponse.getWriter().write(JSONObject.toJSONString(ApiResult.ok("认证成功")));
		}else{
			httpServletResponse.getWriter().write(JSONObject.toJSONString(ApiResult.ok("匿名用户")));
		}
	}
}
