package com.xlcxx.config.auth.session;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.Constant;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/20 14:02
 * version 2.0
 * 方法说明 sesssion  过期 失效
 */
public class ExpiredSession implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
		sessionInformationExpiredEvent.getResponse().setContentType(Constant.JSONUTF8);
		sessionInformationExpiredEvent.getResponse().getWriter().write(JSONObject.toJSONString(ApiResult.error("登陆失效")));
	}
}
