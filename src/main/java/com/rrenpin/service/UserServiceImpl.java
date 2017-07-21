package com.rrenpin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rrenpin.dao.UserMapper;
import com.rrenpin.entity.User;
import com.rrenpin.util.Util;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	public User login(String phone, String password) {
		if(phone==null || phone.trim().isEmpty()){
			throw new LoginException("手机号不能为空");
		}
		if(password==null || phone.trim().isEmpty()){
			throw new LoginException("密码不能为空");
		}
		//检测手机号
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new LoginException("手机号或密码错误");
		}
		//检测密码
		String md5Password = Util.md5(password);
		if(user.getPassword().equals(md5Password)){
			return user;
		}else{
			throw new LoginException("手机号或密码错误");
		}
	}

	public User regist(String phone, String password, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyUserInfo(int userId, String nickname, String sex, String job, String degree, String selfIntro) {
		User user = new User();
		user.setId(userId);
		user.setNickname(nickname);
		user.setSex(sex);
		user.setJob(job);
		user.setDegree(degree);
		user.setSelfIntro(selfIntro);
		int i;
		try {
			i = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i!=1){
			throw new ModifyUserInfoException("修改失败");
		}
	}

	public void modifyPsd(int userId, String oldPsd, String newPsd) {
		User user = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			throw new NoUserFindException("userId无效");
		}
		if(!user.getPassword().equals(Util.md5(oldPsd))){
			throw new PasswordException("密码错误");
		}
		user.setPassword(Util.md5(newPsd));
		int i;
		try {
			i = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			throw new DataBaseException("连接服务器超时");
		}
		if(i!=1){
			throw new PasswordException("修改密码失败");
		}
	}
	
	

}
