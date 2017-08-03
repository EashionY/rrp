package com.rrenpin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.DeliveryMapper;
import com.rrenpin.entity.Delivery;
import com.rrenpin.exception.DataBaseException;
import com.rrenpin.exception.DeliveryException;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

	@Resource
	private DeliveryMapper deliveryMapper;
	
	public void deliveryResume(int resumeId, int companyId, int postId) {
		Delivery delivery = deliveryMapper.findDelivery(resumeId,companyId,postId);
		//���Ͷ�ݹ�ϵ��Ϊ�գ�������Ͷ��ʱ��С��30��
		if(delivery!=null && ((System.currentTimeMillis()-delivery.getDeliveryTime().getTime())/(24*60*60*1000))<=30){
			throw new DeliveryException("���μ���Ͷ��ʱ������30��");
		}else{
			delivery = new Delivery();
			delivery.setResumeId(resumeId);
			delivery.setCompanyId(companyId);
			delivery.setPostId(postId);
			int i;
			try {
				i = deliveryMapper.insertSelective(delivery);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("���ӷ�������ʱ");
			}
			if(i!=1){
				throw new DeliveryException("Ͷ��ʧ��");
			}
		}
	}

}
