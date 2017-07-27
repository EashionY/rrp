package com.rrenpin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rrenpin.service.CompanyService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/company")
public class CompanyController extends ExceptionController {

	private static final int SUCCESS = 0;
	
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/sendEmail.do")
	@ResponseBody
	public JsonResult sendEmail(HttpServletRequest request,int userId,String email){
		companyService.sendEmail(request, userId, email);
		return new JsonResult(SUCCESS,"","成功发送邮件");
	}
	
	@RequestMapping("/verifyEmail.do")
	public ModelAndView verifyEmail(HttpServletRequest request,String email,String authCode){
		ModelAndView mav = new ModelAndView();
		try {
			companyService.verifyEmail(request, email, authCode);
			mav.setViewName("verify_success");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			mav.setViewName("verify_failure");
		}
		return mav;
//		return new JsonResult(SUCCESS,"","邮箱已激活，请登录");
	}
}
