package com.rrenpin.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		Map<String,Object> resume = resumeService.addBasicInfo(request, userId, empName, sex, birth, workexp, status, phone, email, empRegion);
		return new JsonResult(SUCCESS,resume,"添加简历基本信息成功");
	}
	
	@RequestMapping("/modifyBasicInfo.do")
	@ResponseBody
	public JsonResult modifyBasicInfo(HttpServletRequest request, int id, int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) throws UnsupportedEncodingException{
		Map<String, Object> resume = resumeService.modifyBasicInfo(request, id, userId, empName, sex, birth, workexp, status, phone, email, empRegion);
		return new JsonResult(SUCCESS,resume,"修改简历基本信息成功");
	}
	
	@RequestMapping("/modifyJobIntention.do")
	@ResponseBody
	public JsonResult modifyJobIntention(HttpServletRequest request, int id, int userId, String salary, String job, String workType, String workArea) throws UnsupportedEncodingException{
		Map<String, Object> resume = resumeService.modifyJobIntention(request, id, userId, salary, job, workType, workArea);
		return new JsonResult(SUCCESS,resume,"修改求职意向成功");
	}
	
	@RequestMapping("/modifySelfEvaluation.do")
	@ResponseBody
	public JsonResult modifySelfEvaluation(HttpServletRequest request, int id, int userId, String selfEvaluation) throws UnsupportedEncodingException{
		Map<String, Object> resume = resumeService.modifySelfEvaluation(request, id, userId, selfEvaluation);
		return new JsonResult(SUCCESS,resume,"修改自我描述成功");
	}
	
	@RequestMapping("/modifyHeadImg.do")
	@ResponseBody
	public JsonResult modifyHeadImg( int id,int userId,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> resume = resumeService.modifyHeadImg(id, userId, request);
		return new JsonResult(SUCCESS,resume,"修改头像成功");
	}
	
	@RequestMapping("/findByUserId.do")
	@ResponseBody
	public JsonResult findByUserId(int userId){
		Map<String, Object> result = resumeService.findByUserId(userId);
		return new JsonResult(result);
	}
	
	@RequestMapping("/modifyResumeName.do")
	@ResponseBody
	public JsonResult modifyResumeName(int id,int userId,String resumeName){
		Map<String, Object> resume = resumeService.modifyResumeName(id, userId, resumeName);
		return new JsonResult(SUCCESS,resume,"修改简历名成功");
	}
	
	@RequestMapping("/addWorkExp.do")
	@ResponseBody
	public JsonResult addWorkExp(HttpServletRequest req,int resumeId,int userId,String company,String work,String workTime,String workDescription) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.addWorkExp(req, resumeId, userId, company, work, workTime, workDescription);
		return new JsonResult(SUCCESS,resume,"工作经验添加成功");
	}
	
	@RequestMapping("/modifyWorkExp.do")
	@ResponseBody
	public JsonResult modifyWorkExp(HttpServletRequest req, int workexpId, int userId, String company, String work, String workTime,
			String workDescription) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.modifyWorkExp(req, workexpId, userId, company, work, workTime, workDescription);
		return new JsonResult(SUCCESS,resume,"工作经验修改成功");
	}
	
	@RequestMapping("/deleteWorkExp.do")
	@ResponseBody
	public JsonResult deleteWorkExp(int workexpId,int userId){
		Map<String,Object> resume = resumeService.deleteWorkExp(workexpId, userId);
		return new JsonResult(SUCCESS,resume,"工作经验删除成功");
	}
	
	@RequestMapping("/addProjectExp.do")
	@ResponseBody
	public JsonResult addProjectExp(HttpServletRequest req, int resumeId, int userId, String project,
			String projectTime, String projectDescription, String duty) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.addProjectExp(req, resumeId, userId, project, projectTime, projectDescription, duty);
		return new JsonResult(SUCCESS,resume,"项目经验添加成功");
	}
	
	@RequestMapping("/modifyProjectExp.do")
	@ResponseBody
	public JsonResult modifyProjectExp(HttpServletRequest req, int projectexpId, int userId, String project,
			String projectTime, String projectDescription, String duty) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.modifyProjectExp(req, projectexpId, userId, project, projectTime, projectDescription, duty);
		return new JsonResult(SUCCESS,resume,"项目经验修改成功");
	}
	
	@RequestMapping("/deleteProjectExp.do")
	@ResponseBody
	public JsonResult deleteProjectExp(int projectexpId, int userId){
		Map<String,Object> resume = resumeService.deleteProjectExp(projectexpId, userId);
		return new JsonResult(SUCCESS,resume,"项目经验删除成功");
	}
	
	@RequestMapping("/addSkill.do")
	@ResponseBody
	public JsonResult addSkill(HttpServletRequest req, int userId, int resumeId, String skillName,
			String skillLevel) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.addSkill(req, userId, resumeId, skillName, skillLevel);
		return new JsonResult(SUCCESS,resume,"技能评价添加成功");
	}
	
	@RequestMapping("/modifySkill.do")
	@ResponseBody
	public JsonResult modifySkill(HttpServletRequest req, int userId, int skillId, String skillName,
			String skillLevel) throws UnsupportedEncodingException{
		Map<String,Object> resume = resumeService.modifySkill(req, userId, skillId, skillName, skillLevel);
		return new JsonResult(SUCCESS,resume,"技能评价修改成功");
	}
	
	@RequestMapping("/deleteSkill.do")
	@ResponseBody
	public JsonResult deleteSkill(int skillId,int userId){
		Map<String,Object> resume = resumeService.deleteSkill(skillId, userId);
		return new JsonResult(SUCCESS,resume,"技能评价删除成功");
	}
	
	@RequestMapping("/addEducationExp.do")
	@ResponseBody
	public JsonResult addEducationExp(HttpServletRequest req, int userId, int resumeId, String school,
			String major, String education, String schoolTime) throws UnsupportedEncodingException {
		Map<String,Object> resume = resumeService.addEducationExp(req, userId, resumeId, school, major, education, schoolTime);
		return new JsonResult(SUCCESS,resume,"教育经历添加成功");
	}
	
	@RequestMapping("/modifyEducationExp.do")
	@ResponseBody
	public JsonResult modifyEducationExp(HttpServletRequest req, int userId, int educationexpId, String school,
			String major, String education, String schoolTime) throws UnsupportedEncodingException {
		Map<String,Object> resume = resumeService.modifyEducationExp(req, userId, educationexpId, school, major, education, schoolTime);
		return new JsonResult(SUCCESS,resume,"教育经历修改成功");
	}
	
	@RequestMapping("/deleteEducationExp.do")
	@ResponseBody
	public JsonResult deleteEducationExp(int educationexpId, int userId) {
		Map<String,Object> resume = resumeService.deleteEducationExp(educationexpId, userId);
		return new JsonResult(SUCCESS,resume,"教育经历删除成功");
	}
}
