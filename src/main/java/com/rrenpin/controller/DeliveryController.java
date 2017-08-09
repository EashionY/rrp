package com.rrenpin.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.service.DeliveryService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/delivery")
public class DeliveryController extends ExceptionController{

	private static final int SUCCESS = 0;
	
	@Resource
	private DeliveryService deliveryService;
	
	@RequestMapping("/deliveryResume.do")
	@ResponseBody
	public JsonResult deliveryResume(int resumeId,int companyId,int postId){
		deliveryService.deliveryResume(resumeId, companyId, postId);
		return new JsonResult(SUCCESS,"","投递成功");
	}
	
	@RequestMapping("/viewDeliveried.do")
	@ResponseBody
	public JsonResult viewDeliveried(int resumeId, String deliveryStatus, int page, int pageSize){
		List<Map<String,Object>> result = deliveryService.viewDeliveried(resumeId, deliveryStatus, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/viewNewResume.do")
	@ResponseBody
	public JsonResult viewNewResume(int companyId, String deliveryStatus, int page, int pageSize) throws ParseException {
		List<Map<String,Object>> result = deliveryService.viewNewResume(companyId, deliveryStatus, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/dealResume.do")
	@ResponseBody
	public JsonResult dealResume(int deliveryId, String deliveryStatus){
		deliveryService.dealResume(deliveryId, deliveryStatus);
		return new JsonResult(SUCCESS,"","处理成功");
	}
	
	@RequestMapping("/searchDelivery.do")
	@ResponseBody
	public JsonResult searchDelivery(int companyId, String deliveryStatus, String keyword, int page,int pageSize){
		List<Map<String,Object>> result = deliveryService.searchDelivery(companyId, deliveryStatus, keyword, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/viewFeedback.do")
	@ResponseBody
	public JsonResult viewFeedback(int resumeId, int page, int pageSize){
		List<Map<String,Object>> result = deliveryService.viewFeedback(resumeId, page, pageSize);
		return new JsonResult(result);
	}
}