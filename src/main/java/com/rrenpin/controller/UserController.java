package com.rrenpin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.User;
import com.rrenpin.service.UserService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends ExceptionController{

	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult login(String phone,String password){
//		System.out.println("phone:"+phone+",psd:"+password);
		User user = userService.login(phone, password);
		return new JsonResult(0,user,"登录成功");
	}
	
	@RequestMapping("/modifyUserInfo.do")
	@ResponseBody
	public JsonResult modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro){
		userService.modifyUserInfo(userId, nickname, sex, job, degree, selfIntro);
		return new JsonResult(0,"","个人资料修改成功");
	}
	
	@RequestMapping("/modifyPsd.do")
	@ResponseBody
	public JsonResult modifyPsd(int userId,String oldPsd,String newPsd){
		userService.modifyPsd(userId, oldPsd, newPsd);
		return new JsonResult(0,"","密码修改成功");
	}
	
	
}
