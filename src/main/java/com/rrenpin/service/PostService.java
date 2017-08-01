package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.Post;

public interface PostService {

	/**
	 * 发布职位
	 * @param req
	 * @param companyId 公司id
	 * @param name
	 * @param salary
	 * @param region
	 * @param workExp
	 * @param degree
	 * @param workType
	 * @param benefits
	 * @param duty
	 * @param requirement
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public void pushJob(HttpServletRequest req,int companyId,String name,String salary,String region,String workExp,String degree,String workType,
			String benefits,String duty,String requirement) throws UnsupportedEncodingException;
	
	/**
	 * 编辑职位
	 * @param req
	 * @param id 职位id
	 * @param name
	 * @param salary
	 * @param region
	 * @param workExp
	 * @param degree
	 * @param workType
	 * @param benefits
	 * @param duty
	 * @param requirement
	 * @return post
	 * @throws UnsupportedEncodingException
	 */
	public Post editJob(HttpServletRequest req,int id,String name,String salary,String region,String workExp,String degree,String workType,
			String benefits,String duty,String requirement) throws UnsupportedEncodingException;
	
	/**
	 * 开启职位
	 * @param id
	 * @return 当前状态码
	 */
	public String openJob(int id);
	
	/**
	 * 关闭职位
	 * @param id
	 * @return 当前状态码
	 */
	public String closeJob(int id);
	
	/**
	 * 公司职位发布表
	 * @param companyId
	 * @param status 职位状态
	 * @return 
	 */
	public List<Post> listPostJob(int companyId,String status);
}
