package com.xlcxx.plodes.system.services.Impl;

import com.xlcxx.plodes.baseServices.impl.BaseServices;
import com.xlcxx.plodes.mapper.system.RoleMapper;
import com.xlcxx.plodes.system.domian.Role;
import com.xlcxx.plodes.system.services.RoleServices;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/21 14:36
 * version 2.0
 * 方法说明
 */
@Service
public class RoleServicesImpl extends BaseServices<Role> implements RoleServices {

	private static final Logger logger = LoggerFactory.getLogger(RoleServicesImpl.class);

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getUserRoleByUsername(String username) {
		try {
			return roleMapper.selectUserRoleByUsername(username);
		}catch (Exception e){
			logger.error("获取用户角色失败:"+e.getMessage());
			return new ArrayList<>();
		}

	}
}
