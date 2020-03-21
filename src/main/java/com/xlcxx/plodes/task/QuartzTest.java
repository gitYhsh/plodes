package com.xlcxx.plodes.task;

import com.xlcxx.config.quartz.annotation.CronTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/21 9:23
 * version 2.0
 * 方法说明   定时器测试
 */
@CronTag("QuartzTest")
public class QuartzTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void test(String params) {
		log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
	}


}
