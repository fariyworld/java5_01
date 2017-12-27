package com.offcn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings({ "unused", "resource" })
public class TestSpringQuartz {

	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-quartz.xml");
	
	}
	
	@Test
	public void test1(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-quartz.xml");
		
		try {
			Thread.sleep(60L*1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
