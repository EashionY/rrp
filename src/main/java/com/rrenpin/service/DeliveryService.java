package com.rrenpin.service;

import java.util.List;
import java.util.Map;

public interface DeliveryService {

	/**
	 * Ͷ�ݼ���
	 * @param resumeId
	 * @param companyId
	 * @param postId
	 */
	public void deliveryResume(int resumeId,int companyId,int postId);
	
	/**
	 * (�û�)�鿴��Ͷ�ݵļ���
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	public List<Map<String,Object>> viewDeliveried(int resumeId,String deliveryStatus);
	
}
