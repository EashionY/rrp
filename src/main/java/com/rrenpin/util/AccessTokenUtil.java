package com.rrenpin.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * 获取微信公众平台的Access_Token
 * @author Eashion
 *
 */
@Component
public class AccessTokenUtil {
	//公众号的appID
	private static final String APPID = "wx20debe4f32aa83ea";
	//公众号的开发者密码
	private static final String APPSECRET = "2d1ea9c08972048329a01c5ceb289c4b";
	
	private static String ACCESS_TOKEN = null;
	/**
	 * 获取access_token，每隔一个小时获取一次
	 * @param appId
	 * @param appScret
	 */
	@Scheduled(fixedRate = 3600000)
	public void getAccessToken(){
		// 访问微信服务器
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
	 * 从缓存中获取token
	 * @return
	 */
	public static String getFromCache(){
		return ACCESS_TOKEN;
	}
	
	/**
	 * 强制获取token
	 * @return
	 */
	public static synchronized String getNew(){
		AccessTokenUtil util = new AccessTokenUtil();
		util.getAccessToken();
		return ACCESS_TOKEN;
	}
}
