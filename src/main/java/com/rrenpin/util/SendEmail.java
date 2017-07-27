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

	//��������ַ
	public static final String HOST = "smtp.live.com";
	//Э��
	public static final String PROTOCOL = "smtp";
	//�˿�
	public static final int PORT = 25;
	//�����˵�email
	public static final String FROM = "silver_kira@live.com";
	//������email����
	public static final String PWD = "#yx11235.";
	
	/**
	 * ��ȡsession
	 * @return
	 */
	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.store.protocol", PROTOCOL);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable",true);
		
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
	 * �����ʼ�
	 * @param toEmail
	 * @param content
	 * @throws MessagingException 
	 */
	public static void send(String toEmail,String content) throws MessagingException{
		Session session = getSession();
		System.out.println("send:"+content);
		//����message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM));
		//���÷�����
		InternetAddress[] address = {new InternetAddress(toEmail)};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject("����Ƹ  ��ҵ��֤����");
		msg.setContent(content, "text/html;charset=utf-8");
		//������Ϣ
		Transport.send(msg);
	}
}
