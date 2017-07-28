package com.rrenpin.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rrenpin.dao.UserMapper;
import com.rrenpin.entity.User;
import com.rrenpin.util.AliSms;
import com.rrenpin.util.Image;
import com.rrenpin.util.Util;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	//验证码的长度
	private static final int NUM = 6;
	
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

	public User regist(HttpServletRequest request, String phone, String password, String code) {
		HttpSession session = request.getSession();
		String regCode = (String) session.getAttribute("regCode");
		System.out.println(regCode);
		if(regCode==null){
			throw new CodeErrorException("验证码超时，请重新发送");
		}else if(!code.equals(regCode)){
			throw new CodeErrorException("验证码错误");
		}
		User user;
		try {
			user = userMapper.findByPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(user!=null){
			throw new PhoneException("该手机已注册");
		}
		user = new User();
		user.setPhone(phone);
		user.setPassword(Util.md5(password));
		//默认昵称
		String nickname = phone.replace(phone.substring(3, 7), "****");
		user.setNickname(nickname);
		//默认头像
		String headImg = "";
		user.setHeadImg(headImg);
		int i;
		try {
			i = userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new RegistException("注册失败");
		}
		try {
			user = userMapper.findByPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		return user;
	}

	public void modifyUserInfo(HttpServletRequest request, int userId, String nickname, String sex, String job, String degree, String selfIntro) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
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

	public User findUserInfo(int userId) {
		User user;
		try {
			user = userMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(user==null){
			throw new NoUserFindException("未找到对应用户");
		}
		return user;
	}

	public boolean sendRegCode(HttpServletRequest request,String phone) {
		User user = userMapper.findByPhone(phone);
		if(user!=null){
			throw new PhoneException("该手机号已注册");
		}
		//随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //注册码发送短信模板id
        String templateCode = "SMS_68205025";
        //签名
        String signName = "注册验证";
        boolean success = AliSms.sendCode(phone, code, templateCode, signName);
        if(success){
        	HttpSession session = request.getSession();	
        	session.setAttribute("regCode", code);
        	return success;
        }else{
        	throw new SendCodeException("验证码发送失败");
        }
	}

	public void forgetPsd(String phone, String newPsd) {
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new NoUserFindException("该手机号尚未注册");
		}
		user.setPassword(Util.md5(newPsd));
		int i;
		try {
			i = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new PasswordException("找回密码失败");
		}
	}

	public boolean sendPsdCode(HttpServletRequest request, String phone) {
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new NoUserFindException("该手机号尚未注册");
		}
		//随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //注册码发送短信模板id
        String templateCode = "SMS_68205023";
        //签名
        String signName = "变更验证";
        boolean success = AliSms.sendCode(phone, code, templateCode, signName);
        if(success){
        	HttpSession session = request.getSession();	
        	session.setAttribute("psdCode", code);
        	return success;
        }else{
        	throw new SendCodeException("验证码发送失败");
        }
	}

	public boolean verifyCode(HttpServletRequest request, String code) {
		HttpSession session = request.getSession();
		String psdCode = (String) session.getAttribute("psdCode");
		if(psdCode==null){
			throw new CodeErrorException("验证码超时，请重新发送");
		}else if(!code.equals(psdCode)){
			throw new CodeErrorException("验证码错误");
		}
		return code.equals(psdCode);
	}

	public User modifyHeadImg(HttpServletRequest request, String base64, int userId) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();
		System.out.println(path);
		path += File.separator+userId+File.separator+"userHeadImg.png";
		System.out.println(path);
		boolean tf = Image.base64ToImage(base64, path);
		if(tf){
			int i;
			User user;
			try {
				user = userMapper.selectByPrimaryKey(userId);
				user.setHeadImg(userId+File.separator+"userHeadImg.png");
				i = userMapper.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("连接服务器超时");
			}
			if(i != 1){
				throw new ModifyUserInfoException("修改头像失败");
			}
			return user;
		}else{
			throw new ImgUploadException("图片上传失败");
		}
	}
	
	

}
