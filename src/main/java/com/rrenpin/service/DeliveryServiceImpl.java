package com.rrenpin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.CompanyMapper;
import com.rrenpin.dao.DeliveryMapper;
import com.rrenpin.dao.PostMapper;
import com.rrenpin.dao.ResumeMapper;
import com.rrenpin.entity.Company;
import com.rrenpin.entity.Delivery;
import com.rrenpin.entity.Post;
import com.rrenpin.entity.Resume;
import com.rrenpin.exception.DataBaseException;
import com.rrenpin.exception.DeliveryException;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

	@Resource
	private DeliveryMapper deliveryMapper;
	
	@Resource
	private CompanyMapper companyMapper;
	
	@Resource
	private PostMapper postMapper;
	
	@Resource
	private ResumeMapper resumeMapper;
	
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

	public List<Map<String, Object>> viewDeliveried(int resumeId, String deliveryStatus) {
		List<Delivery> list = deliveryMapper.findByResumeId(resumeId, deliveryStatus);
		if(list==null){
			throw new DeliveryException("δ�ҵ���ص�Ͷ�ݼ�¼");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Delivery delivery : list){
			Map<String,Object> map = new HashMap<String, Object>();
			//Ͷ��ʱ��
			map.put("deliveyTime", delivery.getDeliveryTime());
			//�鿴ʱ��
			map.put("checkTime", delivery.getCheckTime());
			//����ʱ��
			map.put("intendTime", delivery.getIntendTime());
			//����ʱ��
			map.put("inviteTime", delivery.getInviteTime());
			//������ʱ��
			map.put("unfitTime", delivery.getUnfitTime());
			//Ͷ��״̬
			map.put("deliveryStatus", delivery.getDeliveryStatus());
			//��ȡ��˾��
			Company company = companyMapper.selectByPrimaryKey(delivery.getCompanyId());
			map.put("companyName", company.getName());
			//��ȡְλ���������ص㡢н��
			Post post = postMapper.selectByPrimaryKey(delivery.getPostId());
			map.put("postName", post.getPostName());
			map.put("region", post.getRegion());
			map.put("salary", post.getSalary());
			//��ȡ������
			Resume resume = resumeMapper.selectByPrimaryKey(resumeId);
			map.put("resumeName", resume.getResumeName());
			result.add(map);
		}
		return result;
	}

}
