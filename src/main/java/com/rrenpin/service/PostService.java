package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
	public List<Map<String, Object>> listPostJob(int companyId,String status,int page,int pageSize);
	
	/**
	 * 搜索职位/公司
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> searchPostCompany(String keyword, String region, String workExp, String degree,
			String scale, String salary, String industry, String financing, int page, int pageSize);
	
	/**
	 * 10个热门职位
	 * @return
	 */
	public List<Map<String,Object>> popularJob();
	
	/**
	 * 查看最近发布的职位
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> latestPostJob(int page,int pageSize);
	
	/**
	 * 职位详情
	 * @param postId
	 * @return
	 */
	public List<Map<String, Object>> jobDetail(int postId); 
	
	/**
	 * 感兴趣的职位
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> interestedJob(int userId,int page,int pageSize);
	
	/**
	 * (公司)删除发布的职位
	 * @param postId
	 */
	public void deleteJob(int postId);
}
