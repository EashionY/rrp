package com.rrenpin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.Post;
import com.rrenpin.service.PostService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/post")
public class PostController extends ExceptionController{

	private static final Integer SUCCESS = 0;
	
	@Resource
	private PostService postService;
	
	@RequestMapping("/pushJob.do")
	@ResponseBody
	public JsonResult pushJob(HttpServletRequest req, int companyId, String name, String salary, String region,
			String workExp, String degree, String workType, String benefits, String duty, String requirement) throws UnsupportedEncodingException{
		postService.pushJob(req, companyId, name, salary, region, workExp, degree, workType, benefits, duty, requirement);
		return new JsonResult(SUCCESS,"","职位发布成功");
	}
	
	@RequestMapping("/editJob.do")
	@ResponseBody
	public JsonResult editJob(HttpServletRequest req, int id, String name, String salary, String region, String workExp,
			String degree, String workType, String benefits, String duty, String requirement)
			throws UnsupportedEncodingException {
		Post post = postService.editJob(req, id, name, salary, region, workExp, degree, workType, benefits, duty, requirement);
		return new JsonResult(SUCCESS,post,"职位编辑成功");
	}
	
	@RequestMapping("/openJob.do")
	@ResponseBody
	public JsonResult openJob(int id){
		String status = postService.openJob(id);
		return new JsonResult(SUCCESS,status,"开启成功");
	}
	
	@RequestMapping("/closeJob.do")
	@ResponseBody
	public JsonResult closeJob(int id){
		String status = postService.closeJob(id);
		return new JsonResult(SUCCESS,status,"关闭成功");
	}
	
	@RequestMapping("/listPostJob.do")
	@ResponseBody
	public JsonResult listPostJob(int companyId,String status,int page,int pageSize){
		List<Map<String, Object>> result = postService.listPostJob(companyId, status, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/searchPostCompany.do")
	@ResponseBody
	public JsonResult searchPostCompany(String keyword, String region, String workExp, String degree,
			String scale, String salary, String industry, String financing, int page, int pageSize){
		List<Map<String,Object>> result = postService.searchPostCompany(keyword, region, workExp, degree, scale, salary, industry, financing, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/popularJob.do")
	@ResponseBody
	public JsonResult popularJob(){
		List<Map<String,Object>> result = postService.popularJob();
		return new JsonResult(result);
	}
	
	@RequestMapping("/latestPostJob.do")
	@ResponseBody
	public JsonResult latestPostJob(int page,int pageSize){
		List<Map<String,Object>> result = postService.latestPostJob(page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/jobDetail.do")
	@ResponseBody
	public JsonResult jobDetail(int postId){
		List<Map<String,Object>> result = postService.jobDetail(postId);
		return new JsonResult(result);
	}
	
	@RequestMapping("/interestJob.do")
	@ResponseBody
	public JsonResult interestJob(int userId,int page,int pageSize){
		List<Map<String,Object>> result = postService.interestedJob(userId, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/deleteJob.do")
	@ResponseBody
	public JsonResult deleteJob(int postId){
		postService.deleteJob(postId);
		return new JsonResult(SUCCESS,"","删除职位成功");
	}
	
}
