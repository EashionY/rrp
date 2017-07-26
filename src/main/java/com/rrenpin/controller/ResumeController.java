package com.rrenpin.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.Resume;
import com.rrenpin.service.ResumeService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/resume")
public class ResumeController extends ExceptionController {
	
	private static final int SUCCESS = 0;

	@Resource
	private ResumeService resumeService;
	
	@RequestMapping("/addBasicInfo.do")
	@ResponseBody
	public JsonResult addBasicInfo(HttpServletRequest request, int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) throws UnsupportedEncodingException{
		Resume resume = resumeService.addBasicInfo(request, userId, empName, sex, birth, workexp, status, phone, email, empRegion);
		return new JsonResult(SUCCESS,resume,"添加简历基本信息成功");
	}
	
	@RequestMapping("/modifyBasicInfo.do")
	@ResponseBody
	public JsonResult modifyBasicInfo(HttpServletRequest request, int id, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyBasicInfo(request, id, empName, sex, birth, workexp, status, phone, email, empRegion);
		return new JsonResult(SUCCESS,resume,"修改简历基本信息成功");
	}
	
	@RequestMapping("/modifyJobIntention.do")
	@ResponseBody
	public JsonResult modifyJobIntention(HttpServletRequest request, int id, String salary, String job, String workType, String workArea) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyJobIntention(request, id, salary, job, workType, workArea);
		return new JsonResult(SUCCESS,resume,"修改求职意向成功");
	}
	
	@RequestMapping("/modifyWorkExp.do")
	@ResponseBody
	public JsonResult modifyWorkExp(HttpServletRequest request, int id, String company1, String work1, String work1Time, String work1Description,
			String company2, String work2, String work2Time, String work2Description, String company3, String work3,
			String work3Time, String work3Description) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyWorkExp(request, id, company1, work1, work1Time, work1Description, company2, work2,
				work2Time, work2Description, company3, work3, work3Time, work3Description);
		return new JsonResult(SUCCESS,resume,"修改工作经验成功");
	}
	
	@RequestMapping("/modifyProjectExp.do")
	@ResponseBody
	public JsonResult modifyProjectExp(HttpServletRequest request, int id, String project1, String project1Time, String project1Description,
			String duty1, String project2, String project2Time, String project2Description, String duty2,
			String project3, String project3Time, String project3Description, String duty3) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyProjectExp(request, id, project1, project1Time, project1Description, duty1,
				project2, project2Time, project2Description, duty2, project3, project3Time, project3Description, duty3);
		return new JsonResult(SUCCESS,resume,"修改项目经验成功");
	}
	
	@RequestMapping("/modifySkill.do")
	@ResponseBody
	public JsonResult modifySkill(HttpServletRequest request, int id, String skill) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifySkill(request, id, skill);
		return new JsonResult(SUCCESS,resume,"修改技能评价成功");
	}
	
	@RequestMapping("/modifyEducationExp.do")
	@ResponseBody
	public JsonResult modifyEducationExp(HttpServletRequest request, int id, String school1, String major1, String education1, String school1Time,
			String school2, String major2, String education2, String school2Time) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyEducationExp(request, id, school1, major1, education1, school1Time, school2, major2, education2, school2Time);
		return new JsonResult(SUCCESS,resume,"修改教育经历成功");
	}
	
	@RequestMapping("/modifySelfEvaluation.do")
	@ResponseBody
	public JsonResult modifySelfEvaluation(HttpServletRequest request, int id, String selfEvaluation) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifySelfEvaluation(request, id, selfEvaluation);
		return new JsonResult(SUCCESS,resume,"修改自我描述成功");
	}
	
	@RequestMapping("/modifyHeadImg.do")
	@ResponseBody
	public JsonResult modifyHeadImg( int id,int userId,HttpServletRequest request) throws UnsupportedEncodingException{
		Resume resume = resumeService.modifyHeadImg(id, userId, request);
		return new JsonResult(SUCCESS,resume,"修改头像成功");
	}
	
	@RequestMapping("/findByUserId.do")
	@ResponseBody
	public JsonResult findByUserId(int userId){
		Resume resume = resumeService.findByUserId(userId);
		return new JsonResult(resume);
	}
	
	@RequestMapping("/modifyResumeName.do")
	@ResponseBody
	public JsonResult modifyResumeName(int id,String resumeName){
		Resume resume = resumeService.modifyResumeName(id, resumeName);
		return new JsonResult(SUCCESS,resume,"修改简历名成功");
	}
}
