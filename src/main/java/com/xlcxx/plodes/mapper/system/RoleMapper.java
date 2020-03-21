package com.xlcxx.plodes.mapper.system;

import com.xlcxx.config.database.MyMapper;
import com.xlcxx.plodes.system.domian.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

	/**查询人的角色名称**/
	List<Role> selectUserRoleByUsername(String username);

}