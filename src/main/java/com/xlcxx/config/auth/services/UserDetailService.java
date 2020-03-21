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

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userDetailService.findUserByUsername(s);
		if (user!=null){
			Boolean notLocked = false;
			if (user.getStatus().equals("1")){ notLocked = true; }
			String  permissions = "role:list";
			AuthonUserDetails userDetails = new AuthonUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
					AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
			return userDetails;
		}else{
			throw new UsernameNotFoundException("");
		}

	}
}
