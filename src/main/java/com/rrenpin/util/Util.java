package com.rrenpin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class Util {

	/**
	 * 对字符串进行MD5加密
	 * @param src
	 * @return
	 */
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
	
	/** 
     * 将源字符串使用MD5加密为字节数组 
     * @param source 
     * @return 
     */  
    public static byte[] encode2bytes(String source) {  
        byte[] result = null;  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.reset();  
            md.update(source.getBytes("UTF-8"));  
            result = md.digest();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return result;  
    } 
    
	/** 
     * 将源字符串使用MD5加密为32位16进制数 
     * @param source 
     * @return 
     */  
    public static String encode2hex(String source) {  
        byte[] data = encode2bytes(source);  
  
        StringBuffer hexString = new StringBuffer();  
        for (int i = 0; i < data.length; i++) {  
            String hex = Integer.toHexString(0xff & data[i]);  
              
            if (hex.length() == 1) {  
                hexString.append('0');  
            }  
              
            hexString.append(hex);  
        }  
          
        return hexString.toString();  
    }  
	
}
