package com.xlcxx.plodes.system.services;

import com.xlcxx.plodes.baseServices.IServices;
import com.xlcxx.plodes.system.domian.Role;

import java.util.List;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/21 14:35
 * version 2.0
 * 方法说明
 */
public interface RoleServices extends IServices<Role> {

	/**获取用户的角色信息**/
	List<Role> getUserRoleByUsername(String username);

}
