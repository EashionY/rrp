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
import com.rrenpin.exception.NoCompanyFindException;
import com.rrenpin.exception.NoPostFindException;
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

	public List<Map<String, Object>> viewDeliveried(int resumeId, String deliveryStatus, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list = deliveryMapper.findByResumeId(resumeId, deliveryStatus, offset, pageSize);
		if(list==null){
			throw new DeliveryException("未找到相关的投递记录");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : list){
			//获取公司名
			Company company = companyMapper.selectByPrimaryKey((Integer)map.get("company_id"));
			map.put("companyName", company.getName());
			//获取职位名、工作地点、薪资
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
			map.put("postName", post.getPostName());
			map.put("region", post.getRegion());
			map.put("salary", post.getSalary());
			//获取简历名
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
			throw new DeliveryException("未找到相关的投递记录");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : list){
			//获得对应的简历
			Resume resume = resumeMapper.selectByPrimaryKey((Integer)map.get("resume_id"));
			map.put("empName", resume.getEmpName());
			map.put("salary", resume.getSalary());
			map.put("sex", resume.getSex());
			map.put("topDegree", resume.getTopDegree());
			map.put("age", Util.getAge(resume.getBirth()));
			//工作经验
			map.put("workExp", resume.getWorkExp());
			//期望职位
			map.put("job", resume.getJob());
			map.put("headImg", resume.getHeadImg());
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
			//应聘职位
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
			throw new DataBaseException("连接服务器超时");
		}
		if(i!=1){
			throw new DeliveryException("处理失败");
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
			throw new DataBaseException("连接服务器超时");
		}
		for(Map<String, Object> map : list){
			Post post = postMapper.selectByPrimaryKey((Integer)map.get("post_id"));
			//应聘职位
			map.put("postName", post.getPostName());
		}
		return list;
	}

	public List<Map<String, Object>> viewFeedback(int resumeId, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list;
		try {
			list = deliveryMapper.findByResumeId(resumeId, null, offset, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		for(Map<String,Object> map : list){
			int companyId = (Integer) map.get("company_id");
			int postId = (Integer)map.get("post_id");
			Company company = companyMapper.selectByPrimaryKey(companyId);
			if(company==null){
				throw new NoCompanyFindException("未找到对应公司");
			}
			map.put("companyName", company.getName());
			map.put("logo", company.getLogo());
			Post post = postMapper.selectByPrimaryKey(postId);
			if(post==null){
				throw new NoPostFindException("未找到对应职位");
			}
			map.put("postName", post.getPostName());
		}
		return list;
	}

	
}
