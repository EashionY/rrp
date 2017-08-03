package com.rrenpin.service;

import java.util.List;
import java.util.Map;

public interface DeliveryService {

	/**
	 * 投递简历
	 * @param resumeId
	 * @param companyId
	 * @param postId
	 */
	public void deliveryResume(int resumeId,int companyId,int postId);
	
	/**
	 * (用户)查看已投递的简历
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	public List<Map<String,Object>> viewDeliveried(int resumeId,String deliveryStatus);
	
}
