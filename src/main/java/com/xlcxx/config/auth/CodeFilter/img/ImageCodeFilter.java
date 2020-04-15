package com.xlcxx.config.auth.CodeFilter.img;

import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.exceptHandler.ValidateCodeException;
import com.xlcxx.plodes.baseServices.RedisService;
import com.xlcxx.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private RedisService redisService;

    private Set<String> url = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        url.add(securityProperties.getLoginProcessingUrl());
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean match = false;
        for (String u : url) {
            if (pathMatcher.match(u, httpServletRequest.getServletPath())) {
                match = true;break;
            }
        }
        if (match) {
            try {
                validateCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        //ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, Constant.SESSION_KEY_IMAGE_CODE);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        String key = Constant.SESSION_KEY_IMAGE_CODE+codeInRequest;
        String token = redisService.get(key);
        if (!StringUtils.equalsIgnoreCase(token, codeInRequest)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        if (StringUtils.isEmpty(token)) {
            throw new ValidateCodeException("验证码已过期，请重新刷新！");
        }
//        if (codeInSession.isExpire()) {
//            //sessionStrategy.removeAttribute(servletWebRequest, Constant.SESSION_KEY_IMAGE_CODE);
//            throw new ValidateCodeException("验证码已过期，请重新发送！");
//        }

       // sessionStrategy.removeAttribute(servletWebRequest, Constant.SESSION_KEY_IMAGE_CODE);

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
