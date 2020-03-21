package com.xlcxx.plodes.system.services;

import com.xlcxx.plodes.baseServices.IServices;
import com.xlcxx.plodes.system.domian.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:38
 * version 2.0
 * 方法说明
 */
public interface UserServices extends IServices<User> {

	/**获取用户列表**/
	@Cacheable(value="users", key="#p0")
	List<User> getAllUser(String username);

	/**根据名称username获取用户**/
	User  findUserByUsername(String username);
}
