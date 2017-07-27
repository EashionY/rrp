package test;

import javax.mail.MessagingException;

import org.junit.Test;

import com.rrenpin.util.SendEmail;
import com.rrenpin.util.Util;

public class TestCase {

	@Test
	public void test1(){
		String phone = "18728428022";
		String nickname = phone.replace(phone.substring(3, 7), "****");
		System.out.println(nickname);
	}
	
	/**
	 * ���Է����ʼ�
	 * @throws MessagingException 
	 */
	@Test
	public void testSendEmail() throws MessagingException{
		String toEmail = "592393402@qq.com";
		String content = "�����ʼ����͹���!";
		SendEmail.send(toEmail, content);
		System.out.println("���ͳɹ�");
	}
	
	@Test
	public void test2(){
		String toEmail = "592393402";
		String str = Util.encode2hex(toEmail);
		System.out.println(str);
	}
	
}
