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
	//��֤��ĳ���
	private static final int NUM = 6;
	
	@Resource
	private UserMapper userMapper;
	
	public User login(String phone, String password) {
		if(phone==null || phone.trim().isEmpty()){
			throw new LoginException("�ֻ��Ų���Ϊ��");
		}
		if(password==null || phone.trim().isEmpty()){
			throw new LoginException("���벻��Ϊ��");
		}
		//����ֻ���
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new LoginException("�ֻ��Ż��������");
		}
		//�������
		String md5Password = Util.md5(password);
		if(user.getPassword().equals(md5Password)){
			return user;
		}else{
			throw new LoginException("�ֻ��Ż��������");
		}
	}

	public User regist(HttpServletRequest request, String phone, String password, String code) {
		HttpSession session = request.getSession();
		String regCode = (String) session.getAttribute("regCode");
		System.out.println(regCode);
		if(regCode==null){
			throw new CodeErrorException("��֤�볬ʱ�������·���");
		}else if(!code.equals(regCode)){
			throw new CodeErrorException("��֤�����");
		}
		User user;
		try {
			user = userMapper.findByPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(user!=null){
			throw new PhoneException("���ֻ���ע��");
		}
		user = new User();
		user.setPhone(phone);
		user.setPassword(Util.md5(password));
		//Ĭ���ǳ�
		String nickname = phone.replace(phone.substring(3, 7), "****");
		user.setNickname(nickname);
		//Ĭ��ͷ��
		String headImg = "";
		user.setHeadImg(headImg);
		int i;
		try {
			i = userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i != 1){
			throw new RegistException("ע��ʧ��");
		}
		try {
			user = userMapper.findByPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
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
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new ModifyUserInfoException("�޸�ʧ��");
		}
	}

	public void modifyPsd(int userId, String oldPsd, String newPsd) {
		User user = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			throw new NoUserFindException("userId��Ч");
		}
		if(!user.getPassword().equals(Util.md5(oldPsd))){
			throw new PasswordException("�������");
		}
		user.setPassword(Util.md5(newPsd));
		int i;
		try {
			i = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new PasswordException("�޸�����ʧ��");
		}
	}

	public User findUserInfo(int userId) {
		User user;
		try {
			user = userMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(user==null){
			throw new NoUserFindException("δ�ҵ���Ӧ�û�");
		}
		return user;
	}

	public boolean sendRegCode(HttpServletRequest request,String phone) {
		User user = userMapper.findByPhone(phone);
		if(user!=null){
			throw new PhoneException("���ֻ�����ע��");
		}
		//���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //ע���뷢�Ͷ���ģ��id
        String templateCode = "SMS_68205025";
        //ǩ��
        String signName = "ע����֤";
        boolean success = AliSms.sendCode(phone, code, templateCode, signName);
        if(success){
        	HttpSession session = request.getSession();	
        	session.setAttribute("regCode", code);
        	return success;
        }else{
        	throw new SendCodeException("��֤�뷢��ʧ��");
        }
	}

	public void forgetPsd(String phone, String newPsd) {
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new NoUserFindException("���ֻ�����δע��");
		}
		user.setPassword(Util.md5(newPsd));
		int i;
		try {
			i = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i != 1){
			throw new PasswordException("�һ�����ʧ��");
		}
	}

	public boolean sendPsdCode(HttpServletRequest request, String phone) {
		User user = userMapper.findByPhone(phone);
		if(user==null){
			throw new NoUserFindException("���ֻ�����δע��");
		}
		//���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //ע���뷢�Ͷ���ģ��id
        String templateCode = "SMS_68205023";
        //ǩ��
        String signName = "�����֤";
        boolean success = AliSms.sendCode(phone, code, templateCode, signName);
        if(success){
        	HttpSession session = request.getSession();	
        	session.setAttribute("psdCode", code);
        	return success;
        }else{
        	throw new SendCodeException("��֤�뷢��ʧ��");
        }
	}

	public boolean verifyCode(HttpServletRequest request, String code) {
		HttpSession session = request.getSession();
		String psdCode = (String) session.getAttribute("psdCode");
		if(psdCode==null){
			throw new CodeErrorException("��֤�볬ʱ�������·���");
		}else if(!code.equals(psdCode)){
			throw new CodeErrorException("��֤�����");
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
				throw new DataBaseException("���ӷ�������ʱ");
			}
			if(i != 1){
				throw new ModifyUserInfoException("�޸�ͷ��ʧ��");
			}
			return user;
		}else{
			throw new ImgUploadException("ͼƬ�ϴ�ʧ��");
		}
	}
	
	

}
