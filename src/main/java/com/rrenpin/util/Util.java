package com.rrenpin.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Util {

	//���ַ�������MD5����
	public static String md5(String src){
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			byte[] output=md.digest(src.getBytes());
			//return new String(output);
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new Md5Exception("����ʧ��", e);
		}
	}
	
}
