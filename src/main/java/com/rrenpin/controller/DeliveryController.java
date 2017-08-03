package com.rrenpin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rrenpin.service.DeliveryService;
import com.rrenpin.util.JsonResult;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	private static final int SUCCESS = 0;
	
	@Resource
	private DeliveryService deliveryService;
	
	@RequestMapping("/deliveryResume.do")
	@ResponseBody
	public JsonResult deliveryResume(int resumeId,int companyId,int postId){
		deliveryService.deliveryResume(resumeId, companyId, postId);
		return new JsonResult(SUCCESS,"","Í¶µÝ³É¹¦");
	}
	
	
}
