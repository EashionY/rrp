package com.rrenpin.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	//服务器地址
	public static final String HOST = "smtp.163.com";
	//协议
	public static final String PROTOCOL = "smtp";
	//端口
	public static final int PORT = 25;
	//发件人的email
	public static final String FROM = "renrenpin1@163.com";
	//发件人email密码
	public static final String PWD = "rrp123456";
	
	/**
	 * 获取session
	 * @return
	 */
	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.transport.protocol", PROTOCOL);
//		props.put("mail.store.protocol", PROTOCOL);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable","true");
//		props.put("mail.debug", "true");
		
		Authenticator authenticator = new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(FROM, PWD);
			}
		};
		
		Session session = Session.getDefaultInstance(props, authenticator);
		
		return session;
	}
	
	/**
	 * 发送邮件
	 * @param toEmail
	 * @param content
	 * @throws MessagingException 
	 */
	public static void send(String toEmail,String content) throws MessagingException{
		Session session = getSession();
//		System.out.println("send:"+content);
		//创建message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM));
		//设置发件人
		InternetAddress[] address = {new InternetAddress(toEmail)};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject("企业邮箱验证");
		msg.setContent(content, "text/html;charset=utf-8");
		//发送消息
		Transport.send(msg);
	}
}
