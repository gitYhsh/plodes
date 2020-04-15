package com.xlcxx.config.auth.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/20 14:08
 * version 2.0
 * 方法说明
 */
public class Logout implements LogoutHandler {

	private SessionRegistry sessionRegistry;

	@Override
	public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
		String sessionId = httpServletRequest.getRequestedSessionId();
		if (sessionId != null)
			sessionRegistry.removeSessionInformation(sessionId);
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

}
