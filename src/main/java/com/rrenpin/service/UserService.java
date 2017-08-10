package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.User;

public interface UserService {

	/**
	 * �û���¼
	 * @param phone
	 * @param password
	 * @return ���˼���˾��Ϣ
	 */
	public Map<String, Object> login(String phone,String password);
	
	/**
	 * �û�ע��
	 * @param request
	 * @param phone
	 * @param password
	 * @param code
	 * @return User
	 */
	public User regist(HttpServletRequest request,String phone,String password,String code);
	
	/**
	 * �޸��û���Ϣ
	 * @param userId
	 * @param nickname
	 * @param sex
	 * @param job
	 * @param degree
	 * @param selfIntro
	 * @throws UnsupportedEncodingException 
	 */
	public void modifyUserInfo(HttpServletRequest request,int userId,String nickname,String sex,String job,String degree,String selfIntro) throws UnsupportedEncodingException;
	
	/**
	 * �޸�����
	 * @param userId
	 * @param oldPsd
	 * @param newPsd
	 */
	public void modifyPsd(int userId,String oldPsd,String newPsd);
	
	/**
	 * �鿴�û���������
	 * @param userId
	 * @return
	 */
	public User findUserInfo(int userId);
	
	/**
	 * ����ע����֤��
	 * @param request
	 * @param phone
	 * @return
	 */
	public boolean sendRegCode(HttpServletRequest request,String phone);
	
	/**
	 * ��������
	 * @param phone
	 * @param newPsd
	 */
	public void forgetPsd(String phone,String newPsd);
	
	/**
	 * ���͸�����֤��
	 * @param request
	 * @param phone
	 * @return
	 */
	public boolean sendPsdCode(HttpServletRequest request,String phone);
	
	/**
	 * ��ʵ������֤��
	 * @param request
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean verifyCode(HttpServletRequest request,String code);
	
	/**
	 * �޸��û�ͷ��(����base64�ַ���)
	 * @param request
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public User modifyHeadImg(HttpServletRequest request,String base64,int userId) throws UnsupportedEncodingException;
	
	/**
	 * �û������ֻ���
	 * @param userId
	 * @param newPhone
	 * @param code
	 */
	public void modifyPhone(int userId,String newPhone,String code,HttpServletRequest request);
	
	/**
	 * ���͸����ֻ���֤��
	 * @param request
	 * @param newPhone
	 * @return
	 */
	public boolean sendPhoneCode(HttpServletRequest request,String newPhone);
	
}
