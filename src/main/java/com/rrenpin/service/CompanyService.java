package com.rrenpin.service;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService {

	/**
	 * �����ʼ�
	 * @param userId
	 * @param email
	 */
	public void sendEmail(HttpServletRequest request,int userId,String email);
	
	/**
	 * ��֤����
	 * @param authCode
	 */
	public void verifyEmail(HttpServletRequest request,String email,String authCode);
	
}
