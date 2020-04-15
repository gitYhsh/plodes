package com.xlcxx.config.auth.CodeFilter.jwt;

import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.exceptHandler.ValidateCodeException;
import com.xlcxx.config.auth.utils.JwtUtil;
import com.xlcxx.config.auth.utils.PlodesUtils;
import com.xlcxx.plodes.baseServices.RedisService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/14 10:50
 * version 2.0
 * 方法说明
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private RedisService redisService;

	private SecurityProperties securityProperties;

	private AuthenticationFailureHandler authenticationFailureHandler;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String []configUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getAnonResourcesUrl(),",");
		/**判断认证方法是不是ajax 或者是post**/
		if (PlodesUtils.isAjaxRequest(request) ) {
			throw new AuthenticationServiceException("认证方法不支持: " + request.getMethod());
		}
		String access_token = request.getHeader("access_token");
		List<String> listUrl = Arrays.asList(configUrl);
		if (!listUrl.contains(request.getServletPath())) {
			try {
				validateTokenCode(new ServletWebRequest(request),access_token);
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
			// 如果请求头中有token，则进行解析，并且设置认证信息
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(access_token));
		}

		chain.doFilter(request, response);
	}
	private void validateTokenCode(ServletWebRequest servletWebRequest,String accessToken) throws ServletRequestBindingException {
		/**验证是不是存在token**/
		if (StringUtils.isEmpty(accessToken)) {
			throw new ValidateCodeException("服务器拒绝访问！");
		}
		/**验证token得有效性**/
		String jwtToken  = redisService.get("token:"+accessToken);
		if (StringUtils.isEmpty(jwtToken)) {
			throw new ValidateCodeException("token不存在，请重新获取！");
		}
		/**解析token得信息判断是不是过期**/
		Boolean isTrue = JwtUtil.isVerify(jwtToken);
		if (!isTrue){
			throw new ValidateCodeException("token已经过期，请重新获取！");
		}

	}
	// 这里从token中获取用户信息并新建一个token
	private UsernamePasswordAuthenticationToken getAuthentication(String jwtToken) {
		String jwtTokenwe  = redisService.get("token:"+jwtToken);
		Claims claims = JwtUtil.parseJWT(jwtTokenwe);
		String username = claims.get("username",String.class);
		if (username != null){
			return new UsernamePasswordAuthenticationToken(username, null,
					Collections.singleton(new SimpleGrantedAuthority("rple"))
			);
		}
		throw new RuntimeException("解析token 信息失败 username是："+username);
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

}
