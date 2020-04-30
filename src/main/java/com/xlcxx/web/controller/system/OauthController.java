package com.xlcxx.web.controller.system;

import com.xkcoding.justauth.AuthRequestFactory;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: plodes
 * Created by yhsh on 2020/4/21 10:05
 * version 2.0
 * 方法说明
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

	@Autowired
	private  AuthRequestFactory factory;
	/**
	 * 登录类型
	 */
	@GetMapping
	public Map<String, String> loginType() {
		List<String> oauthList = factory.oauthList();
		return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录", oauth -> "/oauth/login/" + oauth.toLowerCase()));
	}

	/**
	 * 登录
	 * @param oauthType 第三方登录类型
	 * @param response  response
	 * @throws IOException
	 */
	@RequestMapping("/login/{oauthType}")
	public void renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
		AuthRequest authRequest = factory.get(oauthType);
		response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
	}

	/**
	 * 登录成功后的回调
	 *
	 * @param oauthType 第三方登录类型
	 * @param callback  携带返回的信息
	 * @return 登录成功后的信息
	 */
	@RequestMapping("/{oauthType}/callback")
	public AuthResponse login(@PathVariable String oauthType, AuthCallback callback) {
		AuthRequest authRequest = factory.get(oauthType);
		AuthResponse response = authRequest.login(callback);
		return response;
	}
}
