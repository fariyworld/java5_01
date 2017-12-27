package com.offcn.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.offcn.utils.StringUtil;

public class TestHtmlMail {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//初始化Spring环境
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-mail.xml");
		
		//获取mailSender邮件发送类
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) context.getBean("mailSender");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom("1525752742@qq.com");
			helper.setTo("1103090668@qq.com");
			helper.setSubject("圣诞快乐");
			
			String text = StringUtil.getStrByHtml("D:/JavaWebSoftWare/chart/new_file.html");
			
			helper.setText(text,true);
			
			ClassPathResource resource = new ClassPathResource("/1.png");
			
			helper.addInline("Coupon", resource);
			
			helper.addAttachment("1.png", resource);
			
			mailSender.send(message);
			
			System.out.println("带附件的Html邮件发送OK");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("带附件的Html邮件发送失败");
		}
		
	}
}
