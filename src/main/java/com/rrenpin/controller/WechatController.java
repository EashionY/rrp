package com.rrenpin.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.util.AccessTokenUtil;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/wechat")
public class WechatController extends ExceptionController {

	private final String token = "rrp2017";
	
	/**
	 * ��֤��������ַ����Ч��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/verify.do")
	public void getOpenID(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(signature);
		list.add(timestamp);
		list.add(nonce);
		
		//����
		String sortString = sort(token,timestamp,nonce);
		//����
		String mytoken = SHA1(sortString);
		//У��ǩ��
		if(mytoken != null && mytoken != "" && mytoken.equals(signature)){
			//У��ͨ������echostr����΢�ŷ�����
			response.getWriter().println(echostr);
			System.out.println("ǩ��У��ͨ��");
		}else{
			System.out.println("ǩ��У��ʧ��");
		}
	}
	
	/**
	 * �ֵ������򷽷�
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String sort(String token, String timestamp, String nonce) {
	    String[] strArray = { token, timestamp, nonce };
	    Arrays.sort(strArray);
	 
	    StringBuilder sbuilder = new StringBuilder();
	    for (String str : strArray) {
	        sbuilder.append(str);
	    }
	 
	    return sbuilder.toString();
	}
	
	/**
	 * ��ϣ�㷨����
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript){
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	@RequestMapping("/getToken.do")
	@ResponseBody
	public JsonResult getToken(){
		String token = AccessTokenUtil.getFromCache();
		return new JsonResult(token);
	}
}
