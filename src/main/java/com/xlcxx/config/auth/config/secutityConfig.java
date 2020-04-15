package com.xlcxx.config.auth.config;

import com.xlcxx.config.auth.CodeFilter.img.ImageCodeFilter;
import com.xlcxx.config.auth.CodeFilter.jwt.JWTAuthorizationFilter;
import com.xlcxx.config.auth.CodeFilter.jwt.JwtAuthenticationFilter;
import com.xlcxx.config.auth.CodeFilter.sms.DefaultSmsSender;
import com.xlcxx.config.auth.CodeFilter.sms.SmsCodeFilter;
import com.xlcxx.config.auth.CodeFilter.sms.SmsCodeSender;
import com.xlcxx.config.auth.damain.SecurityProperties;
import com.xlcxx.config.auth.exceptHandler.CustomAuthenticationEntryPoint;
import com.xlcxx.config.auth.exceptHandler.DeniedHandler;
import com.xlcxx.config.auth.exceptHandler.FailureHandler;
import com.xlcxx.config.auth.exceptHandler.SuccessHandler;
import com.xlcxx.config.auth.services.UserDetailService;
import com.xlcxx.config.auth.session.LogoutSuccess;
import com.xlcxx.config.auth.session.Logout;

import com.xlcxx.config.auth.xss.XssFilter;
import com.xlcxx.plodes.baseServices.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 14:13
 * version 2.0
 * 方法说明  安全登陆框架
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class secutityConfig  extends WebSecurityConfigurerAdapter {

	/**用户信息**/
	@Autowired
	private UserDetailService authonUserDetails;

	@Autowired
	private SmsCodeConfig smsCodeConfig;

	@Autowired
	private JwtConfig jwtConfig;

	/**持久化数据库**/
//	@Autowired
//	private DataSource dataSource;

//	@Autowired
//	private SuccessHandler successHandler;
	@Autowired
	private  FailureHandler failureHandler;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private RedisService redisService;


	/**用户密码加密**/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//		jdbcTokenRepository.setDataSource(dataSource);
//		jdbcTokenRepository.setCreateTableOnStartup(false);
//		return jdbcTokenRepository;
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
		imageCodeFilter.setAuthenticationFailureHandler(failureHandler);
		imageCodeFilter.setSecurityProperties(securityProperties);
		imageCodeFilter.setRedisService(redisService);
		imageCodeFilter.afterPropertiesSet();

		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(failureHandler);
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.setRedisService(redisService);
		smsCodeFilter.afterPropertiesSet();


		JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager());
		jwtAuthorizationFilter.setSecurityProperties(securityProperties);
		jwtAuthorizationFilter.setRedisService(redisService);
		jwtAuthorizationFilter.setAuthenticationFailureHandler(failureHandler);
		jwtAuthorizationFilter.afterPropertiesSet();


		//http.headers().cacheControl();//禁用头部缓存
		http.exceptionHandling().accessDeniedHandler(new DeniedHandler())
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		.and()
				.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilter(jwtAuthorizationFilter)
//				.formLogin() // 表单方式
//				.loginPage("/login") // 未认证跳转 URL
//				.loginProcessingUrl(securityProperties.getLoginProcessingUrl()) //处理登陆
//				.successHandler(successHandler) // 处理登录成功
//				.failureHandler(failureHandler) // 处理登录失败;
		//.and()
//				.rememberMe()
//				.tokenRepository(persistentTokenRepository()) //持久化token
//				.tokenValiditySeconds(securityProperties.getRememberMeTimeout())//过期时间
				.userDetailsService(authonUserDetails)// 处理自动登录逻辑
//		.and()

				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session管理器
//				.invalidSessionStrategy(new InvalidSession()) // 处理 session失效
//				.maximumSessions(securityProperties.getMaxLoginNum()) // 最大并发登录数量
//				.expiredSessionStrategy(new ExpiredSession()) // 处理并发登录被踢出
//				.sessionRegistry(sessionRegistry()) // 配置 session注册中心
		//.and()
				.logout() // 配置登出
				.addLogoutHandler(logoutHandler())
				.logoutSuccessHandler(new LogoutSuccess())// 配置登出处理器
				//.logoutUrl(securityProperties.getLogoutUrl()) // 处理登出 url
				//.logoutSuccessUrl("/login") // 登出后跳转到 /
				//.deleteCookies("JSESSIONID") // 删除 JSESSIONID
		.and()
				//.anonymous().disable() //禁止匿名
				.authorizeRequests() // 授权配置
				// 免认证静态资源路径
				.antMatchers("/login","/sms/code","/image/code","/js/**").permitAll() // 配置免认证路径
				.anyRequest()  // 所有请求
				.authenticated() // 都需要认证
		.and().sessionManagement().disable() //禁用 session
				.cors()
		.and()
				.csrf().disable()
			.apply(smsCodeConfig) // 添加短信验证码认证流程;;
				.and().apply(jwtConfig); // jwt 账号密码认证;

	}
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	// 配置登出处理器(暂不处理，redis里面删除登陆token)
	@Bean
	public LogoutHandler logoutHandler(){
		Logout xxLogoutHandler = new Logout();
		xxLogoutHandler.setSessionRegistry(sessionRegistry());
		return xxLogoutHandler;
	}
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsSender();
	}

	/**
	 * XssFilter Bean
	 */
	@Bean
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		StrictHttpFirewall firewall = new StrictHttpFirewall();
		//去掉";"黑名单
		firewall.setAllowSemicolon(true);
		//加入自定义的防火墙
		web.httpFirewall(firewall);
		super.configure(web);
	}
}
