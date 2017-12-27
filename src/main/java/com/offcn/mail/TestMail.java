package com.offcn.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class TestMail {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//初始化Spring环境
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-mail.xml");
		
		//获取mailSender邮件发送类
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) context.getBean("mailSender");
		
		//创建邮件发送实体对象
		
		//创建简单文本邮件对象
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1525752742@qq.com");
		message.setTo("1103090668@qq.com");
		message.setSubject("圣诞快乐");
		message.setText("四川女人");
		//发送邮件
		mailSender.send(message);
		System.out.println("简单文本邮件发送成功");
	}
}
