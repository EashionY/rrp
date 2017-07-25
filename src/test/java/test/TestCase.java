package test;

import org.junit.Test;

public class TestCase {

	@Test
	public void test1(){
		String phone = "18728428022";
		String nickname = phone.replace(phone.substring(3, 7), "****");
		System.out.println(nickname);
	}
}
