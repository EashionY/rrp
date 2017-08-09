package com.rrenpin.service;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.rrenpin.util.Util;

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

	public List<Map<String, Object>> viewDeliveried(int resumeId, String deliveryStatus, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list = deliveryMapper.findByResumeId(resumeId, deliveryStatus, offset, pageSize);
		if(list==null){
			throw new DeliveryException("δ�ҵ���ص�Ͷ�ݼ�¼");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : list){
			//��ȡ��˾��
			Company company = companyMapper.selectByPrimaryKey((Integer)map.get("company_id"));
			map.put("companyName", company.getName());
			//��ȡְλ���������ص㡢н��
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
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

	public List<Map<String, Object>> viewNewResume(int companyId, String deliveryStatus,int page,int pageSize) throws ParseException {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list = deliveryMapper.findByCompanyId(companyId, deliveryStatus,offset,pageSize);
		if(list==null){
			throw new DeliveryException("δ�ҵ���ص�Ͷ�ݼ�¼");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : list){
			//��ö�Ӧ�ļ���
			Resume resume = resumeMapper.selectByPrimaryKey((Integer)map.get("resume_id"));
			map.put("empName", resume.getEmpName());
			map.put("salary", resume.getSalary());
			map.put("sex", resume.getSex());
			map.put("topDegree", resume.getTopDegree());
			map.put("age", Util.getAge(resume.getBirth()));
			//��������
			map.put("workExp", resume.getWorkExp());
			//����ְλ
			map.put("job", resume.getJob());
			map.put("headImg", resume.getHeadImg());
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
			//ӦƸְλ
			map.put("postName", post.getPostName());
			map.put("userId", resume.getUserId());
			result.add(map);
		}
		return result;
	}

	public void dealResume(int deliveryId, String deliveryStatus) {
		int i;
		try {
			i = deliveryMapper.dealResume(deliveryId, deliveryStatus);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new DeliveryException("����ʧ��");
		}
	}

	public List<Map<String, Object>> searchDelivery(int companyId, String deliveryStatus, String keyword, int page,
			int pageSize) {
		keyword = "%"+keyword+"%";
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list;
		try {
			list = deliveryMapper.searchDelivery(companyId, deliveryStatus, keyword, offset, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		for(Map<String, Object> map : list){
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
			//ӦƸְλ
			map.put("postName", post.getPostName());
		}
		return list;
	}

	
}
