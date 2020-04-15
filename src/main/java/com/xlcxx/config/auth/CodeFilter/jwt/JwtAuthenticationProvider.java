package com.xlcxx.config.auth.CodeFilter.jwt;

import com.alibaba.fastjson.JSONObject;
import com.xlcxx.config.auth.services.UserDetailService;
import com.xlcxx.plodes.system.domian.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/14 9:06
 * version 2.0
 * 方法说明
 */
public class JwtAuthenticationProvider  implements AuthenticationProvider {

	private UserDetailService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

		JSONObject userinfo = (JSONObject) authenticationToken.getPrincipal();
		String username = userinfo.getString("username");
		String password = userinfo.getString("password");
		if (StringUtils.isAllEmpty(username,password)){
			throw  new InternalAuthenticationServiceException("没有改用户");
		}
		User user = new User(username,password);
		UserDetails userDetails = userDetailService.loadUserByUsernameAndPassword(user);
		if (userDetails == null)
			throw new InternalAuthenticationServiceException("没有该用户！");

		JwtAuthenticationToken authenticationResult = new JwtAuthenticationToken(userDetails, userDetails.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return JwtAuthenticationToken.class.isAssignableFrom(aClass);
	}

	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

}
