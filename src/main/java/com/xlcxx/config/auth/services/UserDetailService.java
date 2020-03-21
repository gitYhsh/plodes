package com.xlcxx.config.auth.services;

import com.xlcxx.config.auth.damain.AuthonUserDetails;
import com.xlcxx.plodes.system.domian.Role;
import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.plodes.system.services.RoleServices;
import com.xlcxx.plodes.system.services.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private RoleServices roleServices;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userDetailService.findUserByUsername(s);
		if (user!=null){
			Boolean notLocked = false;
			if (user.getStatus().equals("1")){ notLocked = true; }
			/**获取用户的角色**/
			List<Role> roles=  roleServices.getUserRoleByUsername(user.getUsername());
			List<String> roleEname = new ArrayList<>();
			roles.forEach(role ->  roleEname.add(role.getRoleEname()));
			String  permissions = StringUtils.join(roleEname,",");
			AuthonUserDetails userDetails = new AuthonUserDetails(user.getUsername(), user.getPassword(), true, true, true, notLocked,
					AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

			userDetails.setDeptid(user.getDeptId()+"");
			userDetails.setNickname(user.getNickname());
			userDetails.setUserid(user.getUserId());

			return userDetails;
		}else{
			throw new UsernameNotFoundException("");
		}
	}
}
