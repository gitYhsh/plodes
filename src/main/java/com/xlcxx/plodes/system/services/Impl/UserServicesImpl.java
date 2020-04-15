package com.xlcxx.plodes.system.services.Impl;

import com.xlcxx.config.auth.utils.PlodesUtils;
import com.xlcxx.plodes.baseServices.impl.BaseServices;
import com.xlcxx.plodes.mapper.system.UserMapper;
import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.plodes.system.services.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:39
 * version 2.0
 * 方法说明
 */
@Service
public class UserServicesImpl extends BaseServices<User> implements UserServices {

	private static Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getAllUser(String username) {
		try {
			return this.selectAll();
		}catch (Exception e){
			e.printStackTrace();
			logger.error("获取用户列表失败："+e.getMessage());
		}
		return new ArrayList<>();
	}
	@Override
	public User checkUserByLogin(User user) {
		try {
			Example example = new Example(User.class);
			Example.Criteria criteria = example.createCriteria();
			/**根据注册用户明判断**/
			String username = user.getUsername();
			if (!StringUtils.isEmpty(username)){
				criteria.andEqualTo("username",username);
			}
			/**根据手机号登陆**/
			//TODO(这里手机号绑定,防止一个手机号多个用户)
			String mobile= user.getMobile();
			if (!StringUtils.isEmpty(mobile)){
				criteria.andEqualTo("mobile",mobile);
				return userMapper.selectOneByExample(example);
			}
			User userGet = userMapper.selectOneByExample(example);
			if (userGet!=null){
				String password = user.getPassword();

				System.out.println("----------"+password);

				Boolean ble = PlodesUtils.isPassworMatche(password,userGet.getPassword());
				return ble?userGet:null;
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.error("获取用户失败:"+e.getMessage());
		}
		return null;
	}
}
