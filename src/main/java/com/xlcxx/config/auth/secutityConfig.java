package com.xlcxx.config.auth;

import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.exceptHandler.DeniedHandler;
import com.xlcxx.config.auth.exceptHandler.FailureHandler;
import com.xlcxx.config.auth.exceptHandler.SuccessHandler;
import com.xlcxx.config.auth.imageCode.img.ImageCodeFilter;
import com.xlcxx.config.auth.services.UserDetailService;
import com.xlcxx.config.auth.session.ExpiredSession;
import com.xlcxx.config.auth.session.InvalidSession;
import com.xlcxx.config.auth.session.LogoutSuccess;
import com.xlcxx.config.auth.session.Logout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 14:13
 * version 2.0
 * 方法说明
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class secutityConfig  extends WebSecurityConfigurerAdapter {

	/**用户信息**/
	@Autowired
	private UserDetailService authonUserDetails;

	/**持久化数据库**/
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SuccessHandler successHandler;
	@Autowired
	private  FailureHandler failureHandler;

	@Autowired
	private SecurityProperties securityProperties;


	/**用户密码加密**/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		jdbcTokenRepository.setCreateTableOnStartup(false);
		return jdbcTokenRepository;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
		imageCodeFilter.setAuthenticationFailureHandler(failureHandler);
		imageCodeFilter.setSecurityProperties(securityProperties);
		imageCodeFilter.afterPropertiesSet();

		http.exceptionHandling().accessDeniedHandler(new DeniedHandler())
		.and()
				.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin() // 表单方式
				.loginPage(securityProperties.getLoginUrl()) // 未认证跳转 URL
				.loginProcessingUrl(securityProperties.getLoginProcessingUrl())
				.successHandler(successHandler) // 处理登录成功
				.failureHandler(failureHandler) // 处理登录失败;
		.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository()) //持久化token
				.tokenValiditySeconds(securityProperties.getRememberMeTimeout())//过期时间
				.userDetailsService(authonUserDetails)// 处理自动登录逻辑
		.and()
				.sessionManagement() // 配置 session管理器
				.invalidSessionStrategy(new InvalidSession()) // 处理 session失效
				.maximumSessions(securityProperties.getMaxLoginNum()) // 最大并发登录数量
				.expiredSessionStrategy(new ExpiredSession()) // 处理并发登录被踢出
				.sessionRegistry(sessionRegistry()) // 配置 session注册中心
		.and().and()
				.logout() // 配置登出
				.addLogoutHandler(logoutHandler())
				.logoutSuccessHandler(new LogoutSuccess())// 配置登出处理器
				//.logoutUrl(securityProperties.getLogoutUrl()) // 处理登出 url
				//.logoutSuccessUrl("/login") // 登出后跳转到 /
				.deleteCookies("JSESSIONID") // 删除 JSESSIONID
		.and()
				.authorizeRequests() // 授权配置
				// 免认证静态资源路径
				.antMatchers("/login","/image/code").permitAll() // 配置免认证路径
				.anyRequest()  // 所有请求
				.authenticated() // 都需要认证
		.and()
				.csrf().disable();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	// 配置登出处理器
	@Bean
	public LogoutHandler logoutHandler(){
		Logout xxLogoutHandler = new Logout();
		xxLogoutHandler.setSessionRegistry(sessionRegistry());
		return xxLogoutHandler;
	}
}
