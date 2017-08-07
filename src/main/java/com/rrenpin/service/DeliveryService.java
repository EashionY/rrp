package com.rrenpin.service;

import java.text.ParseException;
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
	 * (�û�)�鿴��Ͷ�ݵļ�����״̬
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	public List<Map<String,Object>> viewDeliveried(int resumeId,String deliveryStatus);
	
	/**
	 * (��ҵ)�鿴�յ��ļ�����״̬
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 * @throws ParseException 
	 */
	public List<Map<String,Object>> viewNewResume(int companyId,String deliveryStatus) throws ParseException;
	
	/**
	 * ��������Ͷ��
	 * @param resumeId
	 * @param companyId
	 */
	public void dealResume(int resumeId,int companyId,String deliveryStatus);
}
