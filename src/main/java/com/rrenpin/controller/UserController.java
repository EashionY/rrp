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
		return new JsonResult(SUCCESS,user,"登录成功");
	}
	
	@RequestMapping("/modifyUserInfo.do")
	@ResponseBody
	public JsonResult modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro){
		userService.modifyUserInfo(userId, nickname, sex, job, degree, selfIntro);
		return new JsonResult(SUCCESS,"","个人资料修改成功");
	}
	
	@RequestMapping("/modifyPsd.do")
	@ResponseBody
	public JsonResult modifyPsd(int userId,String oldPsd,String newPsd){
		userService.modifyPsd(userId, oldPsd, newPsd);
		return new JsonResult(SUCCESS,"","密码修改成功");
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
		return new JsonResult(SUCCESS,result,"验证码发送成功");
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(HttpServletRequest request,String phone,String password,String code){
		User user = userService.regist(request, phone, password, code);
		return new JsonResult(SUCCESS,user,"注册成功");
	}
	
	
}
