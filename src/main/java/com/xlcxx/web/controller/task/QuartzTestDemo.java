package com.xlcxx.web.controller.task;

import com.xlcxx.config.quartz.domain.Job;
import com.xlcxx.config.quartz.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/21 9:29
 * version 2.0
 * 方法说明
 */
@Controller
public class QuartzTestDemo {

	@Autowired
	private Scheduler sdecheduler;

//	@PostConstruct
//	public void init() {
//
//		Job scheduleJob = new Job("QuartzTest","test","1111","0/10 * * * * ?","1"
//		,"测试定时器",new Date()
//
//		);
//		// 如果不存在，则创建
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(sdecheduler, scheduleJob.getJobId());
//			if (cronTrigger == null) {
//				ScheduleUtils.createScheduleJob(sdecheduler, scheduleJob);
//			} else {
//				ScheduleUtils.updateScheduleJob(sdecheduler, scheduleJob);
//			}
//	}


}
