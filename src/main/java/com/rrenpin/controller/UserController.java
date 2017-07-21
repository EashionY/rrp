package com.rrenpin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String phone,String password,HttpServletResponse resp){
//		System.out.println("phone:"+phone+",psd:"+password);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		User user = userService.login(phone, password);
		return new JsonResult(user);
	}
	
	@RequestMapping("/modifyUserInfo")
	@ResponseBody
	public JsonResult modifyUserInfo(int userId,String nickname,String sex,String job,String degree,String selfIntro,HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		userService.modifyUserInfo(userId, nickname, sex, job, degree, selfIntro);
		return new JsonResult("");
	}
	
	
	
}
