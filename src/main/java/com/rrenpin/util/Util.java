package com.rrenpin.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Util {

	//对字符串进行MD5加密
	public static String md5(String src){
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			byte[] output=md.digest(src.getBytes());
			//return new String(output);
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new Md5Exception("加密失败", e);
		}
	}
	
}
