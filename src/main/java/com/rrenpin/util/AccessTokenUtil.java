package com.rrenpin.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * ��ȡ΢�Ź���ƽ̨��Access_Token
 * @author Eashion
 *
 */
@Component
public class AccessTokenUtil {
	//���ںŵ�appID
	private static final String APPID = "wx20debe4f32aa83ea";
	//���ںŵĿ���������
	private static final String APPSECRET = "2d1ea9c08972048329a01c5ceb289c4b";
	
	private static String ACCESS_TOKEN = null;
	/**
	 * ��ȡaccess_token��ÿ��һ��Сʱ��ȡһ��
	 * @param appId
	 * @param appScret
	 */
	@Scheduled(fixedRate = 3600000)
	public void getAccessToken(){
		// ����΢�ŷ�����
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" 
		           + APPID + "&secret="+ APPSECRET;
		try {
			String result = HttpUtil.sendGet(url, "UTF-8");
			   JSONObject json = JSONObject.fromObject(result);
			ACCESS_TOKEN = json.getString("access_token");
			System.out.println(ACCESS_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ӻ����л�ȡtoken
	 * @return
	 */
	public static String getFromCache(){
		return ACCESS_TOKEN;
	}
	
	/**
	 * ǿ�ƻ�ȡtoken
	 * @return
	 */
	public static synchronized String getNew(){
		AccessTokenUtil util = new AccessTokenUtil();
		util.getAccessToken();
		return ACCESS_TOKEN;
	}
}
