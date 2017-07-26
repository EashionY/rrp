package com.rrenpin.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.User;

public interface UserService {

	/**
	 * 用户登录
	 * @param phone
	 * @param password
	 * @return User
	 */
	public User login(String phone,String password);
	
	/**
	 * 用户注册
	 * @param request
	 * @param phone
	 * @param password
	 * @param code
	 * @return User
	 */
	public User regist(HttpServletRequest request,String phone,String password,String code);
	
	/**
	 * 修改用户信息
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
	 * 修改密码
	 * @param userId
	 * @param oldPsd
	 * @param newPsd
	 */
	public void modifyPsd(int userId,String oldPsd,String newPsd);
	
	/**
	 * 查看用户个人资料
	 * @param userId
	 * @return
	 */
	public User findUserInfo(int userId);
	
	/**
	 * 发送注册验证码
	 * @param request
	 * @param phone
	 * @return
	 */
	public boolean sendRegCode(HttpServletRequest request,String phone);
	
	/**
	 * 忘记密码
	 * @param phone
	 * @param newPsd
	 */
	public void forgetPsd(String phone,String newPsd);
	
	/**
	 * 发送改密验证码
	 * @param request
	 * @param phone
	 * @return
	 */
	public boolean sendPsdCode(HttpServletRequest request,String phone);
	
	/**
	 * 核实改密验证码
	 * @param request
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean verifyCode(HttpServletRequest request,String code);
	
	/**
	 * 修改用户头像
	 * @param request
	 * @param userId
	 * @return
	 */
	public User modifyHeadImg(HttpServletRequest request,int userId);
}
