package com.rrenpin.service;

import java.text.ParseException;
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

	public List<Map<String, Object>> viewDeliveried(int resumeId, String deliveryStatus) {
		List<Delivery> list = deliveryMapper.findByResumeId(resumeId, deliveryStatus);
		if(list==null){
			throw new DeliveryException("未找到相关的投递记录");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Delivery delivery : list){
			Map<String,Object> map = new HashMap<String, Object>();
			//投递时间
			map.put("deliveyTime", delivery.getDeliveryTime());
			//查看时间
			map.put("checkTime", delivery.getCheckTime());
			//有意时间
			map.put("intendTime", delivery.getIntendTime());
			//邀面时间
			map.put("inviteTime", delivery.getInviteTime());
			//不合适时间
			map.put("unfitTime", delivery.getUnfitTime());
			//投递状态
			map.put("deliveryStatus", delivery.getDeliveryStatus());
			//获取公司名
			Company company = companyMapper.selectByPrimaryKey(delivery.getCompanyId());
			map.put("companyName", company.getName());
			//获取职位名、工作地点、薪资
			Post post = postMapper.selectByPrimaryKey(delivery.getPostId());
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

	public List<Map<String, Object>> viewNewResume(int companyId, String deliveryStatus) throws ParseException {
		List<Delivery> list = deliveryMapper.findByCompanyId(companyId, deliveryStatus);
		if(list==null){
			throw new DeliveryException("未找到相关的投递记录");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Delivery delivery : list){
			Map<String,Object> map = new HashMap<String, Object>();
			//投递时间
			map.put("deliveyTime", delivery.getDeliveryTime());
			//查看时间
			map.put("checkTime", delivery.getCheckTime());
			//有意时间
			map.put("intendTime", delivery.getIntendTime());
			//邀面时间
			map.put("inviteTime", delivery.getInviteTime());
			//不合适时间
			map.put("unfitTime", delivery.getUnfitTime());
			//投递状态
			map.put("deliveryStatus", delivery.getDeliveryStatus());
			//获得对应的简历
			Resume resume = resumeMapper.selectByPrimaryKey(delivery.getResumeId());
			map.put("empName", resume.getEmpName());
			map.put("salary", resume.getSalary());
			map.put("sex", resume.getSex());
			map.put("topDegree", resume.getTopDegree());
			map.put("age", Util.getAge(resume.getBirth()));
			//工作经验
			map.put("workExp", resume.getWorkExp());
			//期望职位
			map.put("job", resume.getJob());
			Post post = postMapper.selectByPrimaryKey(delivery.getPostId());
			//应聘职位
			map.put("postName", post.getPostName());
			map.put("userId", resume.getUserId());
			map.put("deliveryId", delivery.getDeliveryId());
			result.add(map);
		}
		return result;
	}

}
