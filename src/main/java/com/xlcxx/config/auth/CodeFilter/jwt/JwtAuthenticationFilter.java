package com.xlcxx.config.auth.CodeFilter.jwt;

import com.alibaba.fastjson.JSONObject;
import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.utils.PlodesUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/13 14:41
 * version 2.0
 * 方法说明  jwt token 过滤
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationFilter(SecurityProperties securityProperties) {
		super(new AntPathRequestMatcher(securityProperties.getLoginProcessingUrl(), HttpMethod.POST.toString()));
	}
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		/**判断认证方法是不是ajax 或者是post**/
		if (PlodesUtils.isAjaxRequest(request) && request.getMethod().equals(HttpMethod.POST.toString())) {
			throw new AuthenticationServiceException("认证方法不支持: " + request.getMethod());
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username",username);
		jsonObject.put("password",password);
		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(jsonObject);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private void setDetails(HttpServletRequest request,
	                        JwtAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
}
