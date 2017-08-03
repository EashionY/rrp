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
		//如果投递关系不为空，且两次投递时间小于30天
		if(delivery!=null && ((System.currentTimeMillis()-delivery.getDeliveryTime().getTime())/(24*60*60*1000))<=30){
			throw new DeliveryException("两次简历投递时间须间隔30天");
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
				throw new DataBaseException("连接服务器超时");
			}
			if(i!=1){
				throw new DeliveryException("投递失败");
			}
		}
	}

}
