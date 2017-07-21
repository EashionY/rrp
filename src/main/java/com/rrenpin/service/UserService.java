package com.rrenpin.service;

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
	 * @param phone
	 * @param password
	 * @param code
	 * @return User
	 */
	public User regist(String phone,String password,String code);
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param nickname
	 * @param sex
	 * @param job
	 * @param degree
	 * @param selfIntro
	 */
	public void modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro);
	
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPsd
	 * @param newPsd
	 */
	public void modifyPsd(int userId,String oldPsd,String newPsd);
}
