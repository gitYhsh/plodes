package com.xlcxx.web.controller.system;

import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.plodes.system.services.UserServices;
import com.xlcxx.utils.QueryRequest;
import com.xlcxx.web.controller.baseController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:54
 * version 2.0
 * 方法说明
 */
@RestController
public class UserController extends BaseController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "user/getAlluser")
	public Map<String,Object> getAlluser(QueryRequest queryRequest){
		return this.selectByPageNumSize(queryRequest,()->userServices.getAllUser("yhsh"));
	}
	@GetMapping(value = "user/addAlluser")
	public void addAlluser(User user){

		String  username = passwordEncoder.encode(user.getPassword());
		System.out.println(username);
	}


}
