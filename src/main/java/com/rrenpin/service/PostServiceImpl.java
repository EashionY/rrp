package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.PostMapper;
import com.rrenpin.entity.Post;
import com.rrenpin.exception.DataBaseException;
import com.rrenpin.exception.PostException;
@Service("postService")
public class PostServiceImpl implements PostService {

	@Resource
	private PostMapper postMapper;
	
	public void pushJob(HttpServletRequest req, int companyId, String name, String salary, String region,
			String workExp, String degree, String workType, String benefits, String duty, String requirement)
			throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		Post post = new Post();
		post.setCompanyId(companyId);
		post.setPostName(name);
		post.setSalary(salary);
		post.setRegion(region);
		post.setWorkExp(workExp);
		post.setDegree(degree);
		post.setWorkType(workType);
		post.setBenefits(benefits);
		post.setDuty(duty);
		post.setRequirement(requirement);
		int i;
		try {
			i = postMapper.insertSelective(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new PostException("ְλ����ʧ��");
		}
	}

	public Post editJob(HttpServletRequest req, int id, String name, String salary, String region, String workExp,
			String degree, String workType, String benefits, String duty, String requirement)
			throws UnsupportedEncodingException {
		Post post = postMapper.selectByPrimaryKey(id);
		if(post==null){
			throw new PostException("�޶�Ӧ��ְλ");
		}
		post.setPostName(name);
		post.setSalary(salary);
		post.setRegion(region);
		post.setWorkExp(workExp);
		post.setDegree(degree);
		post.setWorkType(workType);
		post.setBenefits(benefits);
		post.setDuty(duty);
		post.setRequirement(requirement);
		int i;
		try {
			i = postMapper.updateByPrimaryKeySelective(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new PostException("ְλ�༭ʧ��");
		}
		return postMapper.selectByPrimaryKey(id);
	}

	public String openJob(int id) {
		Post post = postMapper.selectByPrimaryKey(id);
		if(post==null){
			throw new PostException("�޶�Ӧ��ְλ");
		}
		post.setPostStatus("1");
		int i;
		try {
			i = postMapper.updateByPrimaryKeySelective(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new PostException("����ʧ��");
		}
		return "1";
	}

	public String closeJob(int id) {
		Post post = postMapper.selectByPrimaryKey(id);
		if(post==null){
			throw new PostException("�޶�Ӧ��ְλ");
		}
		post.setPostStatus("0");
		int i;
		try {
			i = postMapper.updateByPrimaryKeySelective(post);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		if(i!=1){
			throw new PostException("����ʧ��");
		}
		return "0";
	}

	public List<Post> listPostJob(int companyId, String status, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Post> result;
		try {
			result = postMapper.listPostJob(companyId, status, offset, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return result;
	}

	public List<Map<String, Object>> searchPostCompany(String keyword, int page, int pageSize) {
		keyword = "%"+keyword+"%";
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> result;
		try {
			result = postMapper.searchPostCompany(keyword, offset, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return result;
	}

	public List<Map<String, Object>> popularJob() {
		List<Map<String, Object>> result;
		try {
			result = postMapper.popularJob();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ӷ�������ʱ");
		}
		return result;
	}

	
}