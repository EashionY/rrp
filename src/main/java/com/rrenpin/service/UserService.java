package com.rrenpin.service;

import com.rrenpin.entity.User;

public interface UserService {

	/**
	 * �û���¼
	 * @param phone
	 * @param password
	 * @return User
	 */
	public User login(String phone,String password);
	
	/**
	 * �û�ע��
	 * @param phone
	 * @param password
	 * @param code
	 * @return User
	 */
	public User regist(String phone,String password,String code);
	
	/**
	 * �޸��û���Ϣ
	 * @param userId
	 * @param nickname
	 * @param sex
	 * @param job
	 * @param degree
	 * @param selfIntro
	 */
	public void modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro);
	
	/**
	 * �޸�����
	 * @param userId
	 * @param oldPsd
	 * @param newPsd
	 */
	public void modifyPsd(int userId,String oldPsd,String newPsd);
}
