package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	 * 测试发送邮件
	 * @throws MessagingException 
	 */
	@Test
	public void testSendEmail() throws MessagingException{
		String toEmail = "592393402@qq.com";
		String content = "测试邮件发送功能!";
		SendEmail.send(toEmail, content);
		System.out.println("发送成功");
	}
	
	@Test
	public void test2(){
		String toEmail = "592393402";
		String str = Util.encode2hex(toEmail);
		System.out.println(str);
	}
	
	public Map<String,String> getSqlTypeAndName(String sql){
		String[] filedstr = sql.split(" ");
		String[] fileds = filedstr[1].split(",");
		Map<String,String> types = new HashMap<String,String>();
		for(int i=0;i<fileds.length;i++){
			String type = fileds[i].split("@")[0];
			String name = fileds[i].split("@")[1];
			types.put(type, name);
			System.out.println(types.get(type));
		}
		Set<Entry<String, String>> entry = types.entrySet();
		for(Entry<String, String> e:entry){
			System.out.println(e.getKey()+"=>"+e.getValue());
		}
		return types;
	}
	
	@Test
	public void test3(){
		String sql = "SELECT 'double'@buildId@1716@'sum','int'@sensorCode@1717@'sum','double'@almRID@1718@'avg','int'@ID@1715  FROM my240_286 ORDERBY ID GROUPBY ID";
		Map<String,String> result = getSqlTypeAndName(sql);
		System.out.println(result);
	}
	
	@Test
	public void test4(){
		String os = System.getProperty("os.name");
		System.out.println(os);
		System.out.println(os.toLowerCase().startsWith("win"));
	}
}
