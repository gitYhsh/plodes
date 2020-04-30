package com.xlcxx.config.auth.CodeFilter.sms;

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
import java.util.Map;
import java.util.Set;


public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> url = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private RedisService redisService;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        url.add(securityProperties.getSms().getLoginProcessingUrl());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean match = false;
        for (String u : url) {
            if (pathMatcher.match(u, httpServletRequest.getServletPath())) {
                match = true;
            }
        }
        if (match) {
            try {
                validateSmsCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        String smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        String mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
        if (StringUtils.isBlank(smsCodeInRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        String key = Constant.SESSION_KEY_SMS_CODE+smsCodeInRequest;
        Map<String,String> map  = redisService.hmGetAll(key);
        String code = map.get("code");
        if (StringUtils.isEmpty(code)) {
            throw new ValidateCodeException("验证码不存在或者已过期，请重新发送！");
        }
        if (!StringUtils.equalsIgnoreCase(code, smsCodeInRequest)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        String mobileCode  = map.get("mobile");
//        if (codeInSession.isExpire()) {
//            sessionStrategy.removeAttribute(servletWebRequest, Constant.SESSION_KEY_SMS_CODE + mobile);
//            throw new ValidateCodeException("验证码已过期，请重新发送！");
//        }
        if (!StringUtils.equalsIgnoreCase(mobile, mobileCode)) {
            throw new ValidateCodeException("手机号和验证码不匹配！");
        }
        //sessionStrategy.removeAttribute(servletWebRequest, Constant.SESSION_KEY_SMS_CODE + mobile);
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