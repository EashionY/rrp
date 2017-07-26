package com.rrenpin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.User;
import com.rrenpin.service.UserService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends ExceptionController{

	private static final int SUCCESS = 0;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult login(String phone,String password){
//		System.out.println("phone:"+phone+",psd:"+password);
		User user = userService.login(phone, password);
		return new JsonResult(SUCCESS,user,"��¼�ɹ�");
	}
	
	@RequestMapping("/modifyUserInfo.do")
	@ResponseBody
	public JsonResult modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro){
		userService.modifyUserInfo(userId, nickname, sex, job, degree, selfIntro);
		return new JsonResult(SUCCESS,"","���������޸ĳɹ�");
	}
	
	@RequestMapping("/modifyPsd.do")
	@ResponseBody
	public JsonResult modifyPsd(int userId,String oldPsd,String newPsd){
		userService.modifyPsd(userId, oldPsd, newPsd);
		return new JsonResult(SUCCESS,"","�����޸ĳɹ�");
	}
	
	@RequestMapping("/findUserInfo.do")
	@ResponseBody
	public JsonResult findUserInfo(int userId){
		User user = userService.findUserInfo(userId);
		return new JsonResult(user);
	}
	
	@RequestMapping("/sendRegCode.do")
	@ResponseBody
	public JsonResult sendRegCode(HttpServletRequest request,String phone){
		boolean result = userService.sendRegCode(request, phone);
		return new JsonResult(SUCCESS,result,"��֤�뷢�ͳɹ�");
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(HttpServletRequest request,String phone,String password,String code){
		User user = userService.regist(request, phone, password, code);
		return new JsonResult(SUCCESS,user,"ע��ɹ�");
	}
	
	@RequestMapping("/sendPsdCode.do")
	@ResponseBody
	public JsonResult sendPsdCode(HttpServletRequest request,String phone){
		boolean result = userService.sendPsdCode(request, phone);
		return new JsonResult(SUCCESS,result,"��֤�뷢�ͳɹ�");
	}
	
	@RequestMapping("/verifyCode.do")
	@ResponseBody
	public JsonResult verifyCode(HttpServletRequest request,String code){
		boolean result = userService.verifyCode(request, code);
		return new JsonResult(SUCCESS,result,"��֤ͨ��");
	}
	
	@RequestMapping("/forgetPsd.do")
	@ResponseBody
	public JsonResult forgetPsd(String phone,String newPsd){
		userService.forgetPsd(phone, newPsd);
		return new JsonResult(SUCCESS,"","�һ�����ɹ�");
	}
	
	@RequestMapping("/modifyHeadImg.do")
	@ResponseBody
	public JsonResult modifyHeadImg(HttpServletRequest request,int userId){
		User user = userService.modifyHeadImg(request, userId);
		return new JsonResult(SUCCESS,user,"ͷ���޸ĳɹ�");
	}
}
