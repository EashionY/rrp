package com.rrenpin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.service.CompanyService;
import com.rrenpin.util.JsonResult;
@Controller
@RequestMapping("/backstage")
public class BackstageController extends ExceptionController {

	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/listAllCompany.do")
	@ResponseBody
	public JsonResult listAllCompany(String status,int page,int pageSize){
		List<Map<String,Object>> result = companyService.listAllCompany(status, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/dealCompany.do")
	@ResponseBody
	public JsonResult dealCompany(int companyId,String status){
		String result = companyService.dealCompany(companyId, status);
		return new JsonResult(result);
	}
	
	
}
