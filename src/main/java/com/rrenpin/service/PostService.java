package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
	public List<Post> listPostJob(int companyId,String status);
}
