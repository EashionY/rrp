package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.Post;

public interface PostService {

	/**
	 * ����ְλ
	 * @param req
	 * @param companyId ��˾id
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
	 * �༭ְλ
	 * @param req
	 * @param id ְλid
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
	 * ����ְλ
	 * @param id
	 * @return ��ǰ״̬��
	 */
	public String openJob(int id);
	
	/**
	 * �ر�ְλ
	 * @param id
	 * @return ��ǰ״̬��
	 */
	public String closeJob(int id);
	
	/**
	 * ��˾ְλ������
	 * @param companyId
	 * @param status ְλ״̬
	 * @return 
	 */
	public List<Map<String, Object>> listPostJob(int companyId,String status,int page,int pageSize);
	
	/**
	 * ����ְλ/��˾
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> searchPostCompany(String keyword, String region, String workExp, String degree,
			String scale, String salary, String industry, String financing, int page, int pageSize);
	
	/**
	 * 10������ְλ
	 * @return
	 */
	public List<Map<String,Object>> popularJob();
	
	/**
	 * �鿴���������ְλ
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> latestPostJob(int page,int pageSize);
	
	/**
	 * ְλ����
	 * @param postId
	 * @return
	 */
	public List<Map<String, Object>> jobDetail(int postId); 
	
	/**
	 * ����Ȥ��ְλ
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> interestedJob(int userId,int page,int pageSize);
	
	/**
	 * (��˾)ɾ��������ְλ
	 * @param postId
	 */
	public void deleteJob(int postId);
}
