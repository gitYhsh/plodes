package com.xlcxx.config.quartz.utils;

import com.xlcxx.config.quartz.domain.Job;
import com.xlcxx.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 */
public class ScheduleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        Job scheduleJob = (Job) context.getMergedJobDataMap().get(Constant.JOB_PARAM_KEY);

//        // 获取spring bean
//        JobLogService scheduleJobLogService = (JobLogService) SpringContextUtils.getBean("JobLogService");
//
//        JobLog log = new JobLog();
//        log.setJobId(scheduleJob.getJobId());
//        log.setBeanName(scheduleJob.getBeanName());
//        log.setMethodName(scheduleJob.getMethodName());
//        log.setParams(scheduleJob.getParams());
//        log.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            logger.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
                    scheduleJob.getParams());
            Future<?> future = service.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            // 任务状态 0：成功 1：失败
            logger.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            long times = System.currentTimeMillis() - startTime;
            // 任务状态 0：成功 1：失败
            logger.error("任务执行失败，任务ID：{} 总共耗时：{} 毫秒" ,scheduleJob.getJobId(), times);

            logger.error("任务执行失败，异常原因：{}" ,e.getMessage());
        } finally {
            //执行日志增加
        }
    }
}
