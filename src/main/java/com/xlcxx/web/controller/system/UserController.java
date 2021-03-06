package com.xlcxx.web.controller.system;

import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.plodes.system.services.UserServices;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.QueryRequest;
import com.xlcxx.web.controller.baseController.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:54
 * version 2.0
 * 方法说明  测试权限登陆
 */
@RestController
@Api(tags = "11")
public class UserController extends BaseController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "user/getAlluser")
	@ApiOperation(value = "根据用户名获取菜单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
	})
	public Map<String,Object> getAlluser(QueryRequest queryRequest){
		return this.selectByPageNumSize(queryRequest,()->userServices.getAllUser("yhsh"));
	}

	@GetMapping(value = "user/getZHUuser")
	@PreAuthorize("hasRole('ROLE_ZHUGUAN')")
	public Map<String,Object> getZHUuser(QueryRequest queryRequest){
		return this.selectByPageNumSize(queryRequest,()->userServices.getAllUser("yhsh"));
	}

	@GetMapping(value = "user/getCurentuser")
	public ApiResult getCurentuser(){
		return ApiResult.ok(this.getCurrentUser());
	}

	@GetMapping(value = "user/addAlluser")
	public void addAlluser(User user){
		String  username = passwordEncoder.encode(user.getPassword());
		System.out.println(username);
	}


}
