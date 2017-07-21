package com.rrenpin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.Resume;
import com.rrenpin.service.ResumeService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/resume")
public class ResumeController extends ExceptionController {

	@Resource
	private ResumeService resumeService;
	
	@RequestMapping("/addBasicInfo")
	@ResponseBody
	public JsonResult addBasicInfo(int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion){
		Resume resume = resumeService.addBasicInfo(userId, empName, sex, birth, workexp, status, phone, email, empRegion);
		return new JsonResult(0,resume,"添加简历基本信息成功");
	}
	
	
}
