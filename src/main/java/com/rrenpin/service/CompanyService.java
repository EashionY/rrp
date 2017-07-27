package com.rrenpin.service;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService {

	/**
	 * 发送邮件
	 * @param userId
	 * @param email
	 */
	public void sendEmail(HttpServletRequest request,int userId,String email);
	
	/**
	 * 验证邮箱
	 * @param authCode
	 */
	public void verifyEmail(HttpServletRequest request,String email,String authCode);
	
}
