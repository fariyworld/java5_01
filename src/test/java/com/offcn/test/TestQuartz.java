package com.offcn.test;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("job1", "java").build();

		Date satrt = new Date(System.currentTimeMillis() + 5 * 1000);

		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "offcn")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(5).withIntervalInSeconds(5))
				.startAt(satrt).build();

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		Date date = scheduler.scheduleJob(jobDetail, simpleTrigger);

		System.out.println("启动调度器：" + date.toLocaleString());

		scheduler.start();

		Thread.sleep(40L * 1000);

		scheduler.shutdown();

		System.out.println("关闭调度器");
	}


}
