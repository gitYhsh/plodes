package com.xlcxx.config.auth.services;

import com.xlcxx.config.auth.damain.AuthonUserDetails;
import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.plodes.system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 16:02
 * version 2.0
 * 方法说明
 */
@Configuration
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserServices userDetailService;

	/**用户名和密码登陆
	 * 如果用户名和密码不匹配则返回错误信息
	 * **/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userlogin = new User();
		userlogin.setUsername(username);
		User user = userDetailService.checkUserByLogin(userlogin);
		if (user!=null){
			Boolean notLocked = false;
			if (user.getStatus().equals("1")){ notLocked = true; }
			/**获取用户的角色**/
			String  permissions = "";
			AuthonUserDetails userDetails = new AuthonUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
					AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
			userDetails.setNickname(user.getNickname());
			userDetails.setUserid(user.getUserId());
			userDetails.setLousername(user.getUsername());
			return userDetails;
		}else{
			throw new UsernameNotFoundException("");
		}
	}
	/**手机号登陆，如果用户不存在，则默认创建一个用户**/
	public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
		User userlogin = new User();
		userlogin.setMobile(mobile);
		User user = userDetailService.checkUserByLogin(userlogin);
		if (user!=null){
			Boolean notLocked = false;
			if (user.getStatus().equals("1")){ notLocked = true; }
			/**获取用户的角色**/
			AuthonUserDetails userDetails = new AuthonUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
					AuthorityUtils.commaSeparatedStringToAuthorityList(""));
			userDetails.setNickname(user.getNickname());
			userDetails.setUserid(user.getUserId());
			return userDetails;
		}else{
			throw new UsernameNotFoundException("");
		}
	}
	/**手机号登陆，如果用户不存在，则默认创建一个用户**/
	public UserDetails loadUserByUsernameAndPassword(User userLogin) throws UsernameNotFoundException {
		User user = userDetailService.checkUserByLogin(userLogin);
		if (user!=null){
			Boolean notLocked = false;
			if (user.getStatus().equals("1")){ notLocked = true; }
			/**获取用户的角色**/
			AuthonUserDetails userDetails = new AuthonUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
					AuthorityUtils.commaSeparatedStringToAuthorityList(""));
			userDetails.setNickname(user.getNickname());
			userDetails.setUserid(user.getUserId());
			return userDetails;
		}else{
			throw new UsernameNotFoundException("");
		}
	}
}
