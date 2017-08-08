package com.rrenpin.service;

import java.text.ParseException;
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
	 * (用户)查看已投递的简历的状态
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	public List<Map<String,Object>> viewDeliveried(int resumeId,String deliveryStatus);
	
	/**
	 * (企业)查看收到的简历的状态
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 * @throws ParseException 
	 */
	public List<Map<String,Object>> viewNewResume(int companyId,String deliveryStatus,int page,int pageSize) throws ParseException;
	
	/**
	 * 操作简历投递
	 * @param deliveryId
	 * @param deliveryStatus
	 */
	public void dealResume(int deliveryId,String deliveryStatus);
}
