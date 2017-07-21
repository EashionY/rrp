package com.rrenpin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.entity.Job;
import com.rrenpin.service.JobService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/job")
public class JobController extends ExceptionController{

	@Resource
	private JobService jobService;
	
	@RequestMapping("/listAllJobs")
	@ResponseBody
	public JsonResult listAllJobs(HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Job> jobs = jobService.listJobs();
		return new JsonResult(jobs);
	}
	
}
