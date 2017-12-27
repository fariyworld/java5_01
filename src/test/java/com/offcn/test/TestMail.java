package com.offcn.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * 
 * @TODO: 测试java mail组件
 * @Administrator: 黄土高坡的宝宝
 * @Date: 2017年12月25日下午2:00:21
 * @Version: V1.0.0
 */
public class TestMail {

	/**
	 * 
	 * @TODO: 测试发送邮箱并保存到本地
	 */
	@Test
	public void test1() {

		Properties props = new Properties();

		Session mailSession = Session.getInstance(props);

		mailSession.setDebug(true);

		MimeMessage message = new MimeMessage(mailSession);

		try {

			message.setFrom(new InternetAddress("1525752742@qq.com", "刘小红", "utf-8"));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress("haha@qq.com", "潘婷", "utf-8"));

			message.setSubject("Test邮件", "utf-8");

			message.setContent("邮件正文", "text/html;charset=utf-8");

			message.setSentDate(new Date());

			message.saveChanges();

			OutputStream os = new FileOutputStream("D:/JavaWebSoftWare/chart/testEmail.eml");

			message.writeTo(os);

			os.flush();

			os.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("send message success And save email success");

	}

}
