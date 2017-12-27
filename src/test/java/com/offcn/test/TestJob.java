package com.offcn.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	@SuppressWarnings("deprecation")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("这是个作业：" + new Date().toLocaleString());

	}

}
