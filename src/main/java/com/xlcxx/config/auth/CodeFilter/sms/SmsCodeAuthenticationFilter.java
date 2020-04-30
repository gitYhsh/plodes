package com.xlcxx.config.auth.CodeFilter.sms;

import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.utils.PlodesUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeAuthenticationFilter(SecurityProperties securityProperties) {
        super(new AntPathRequestMatcher(securityProperties.getSms().getLoginProcessingUrl(), HttpMethod.POST.toString()));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        /**判断认证方法是不是ajax 或者是post**/
        if (PlodesUtils.isAjaxRequest(request) && request.getMethod().equals(HttpMethod.POST.toString())) {
            throw new AuthenticationServiceException("认证方法不支持: " + request.getMethod());
        }
        String mobile = request.getParameter("mobile");
        if (StringUtils.isEmpty(mobile)){
            throw new AuthenticationServiceException("认证参数为空 ");
        }
        mobile = mobile.trim();
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
