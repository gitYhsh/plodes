package com.xlcxx.web.controller.system;

import com.xlcxx.plodes.baseServices.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 11:44
 * version 2.0
 * 方法说明
 */
@RestController
public class RedisTest {

	@Autowired
	private RedisService redisService;

	@GetMapping(value = "redis/demo")
	public Object getDemo(){
		redisService.set("nijao","测试信息");
		return redisService.get("nijao");
	}

}
