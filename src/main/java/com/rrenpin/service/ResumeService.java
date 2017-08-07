package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface ResumeService {

	/**
	 * ��Ӽ����Ļ�����Ϣ
	 * @param userId
	 * @param empName
	 * @param sex
	 * @param birth
	 * @param workexp
	 * @param status
	 * @param phone
	 * @param email
	 * @param empRegion
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> addBasicInfo(HttpServletRequest request,int userId,String empName,String sex,String birth,String workexp,String status,
			String phone,String email,String empRegion,String topDegree) throws UnsupportedEncodingException;
	
	/**
	 * �޸ļ����Ļ�����Ϣ
	 * @param id ����id
	 * @param empName
	 * @param sex
	 * @param birth
	 * @param workexp
	 * @param status
	 * @param phone
	 * @param email
	 * @param empRegion
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> modifyBasicInfo(HttpServletRequest request,int id,int userId,String empName,String sex,String birth,String workexp,String status,
			String phone,String email,String empRegion,String topDegree) throws UnsupportedEncodingException;
	
	/**
	 * �޸ļ�������ְ����
	 * @param id
	 * @param salary
	 * @param job
	 * @param workType
	 * @param workArea
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> modifyJobIntention(HttpServletRequest request,int id,int userId,String salary,String job,String workType,String workArea) throws UnsupportedEncodingException;
	
	/**
	 * �޸���������
	 * @param id
	 * @param selfEvaluation
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> modifySelfEvaluation(HttpServletRequest request,int id,int userId,String selfEvaluation) throws UnsupportedEncodingException;
	
	/**
	 * �޸ļ���ͷ��
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	public Map<String, Object> modifyHeadImg(int id,int userId,String headImg);
	
	/**
	 * ͨ��userId���Ҽ���
	 * @param userId
	 * @return
	 */
	public Map<String,Object> findByUserId(int userId);
	
	/**
	 * �޸ļ�����
	 * @param id
	 * @param resumeName
	 * @return
	 */
	public Map<String, Object> modifyResumeName(int id,int userId,String resumeName);
	
	/**
	 * ��ӹ�������
	 * @param req 
	 * @param resumeId
	 * @param userId
	 * @param company
	 * @param work
	 * @param workTime
	 * @param workDescription
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> addWorkExp(HttpServletRequest req,int resumeId,int userId,String company,String work,String workTime,String workDescription) throws UnsupportedEncodingException;
	
	/**
	 * �޸Ĺ�������
	 * @param workexpId
	 * @param userId
	 * @param company
	 * @param work
	 * @param workTime
	 * @param workDescription
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> modifyWorkExp(HttpServletRequest req,int workexpId,int userId,String company,String work,String workTime,String workDescription) throws UnsupportedEncodingException;
	
	/**
	 * ɾ����������
	 * @param workexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteWorkExp(int workexpId,int userId);
	
	/**
	 * �����Ŀ����
	 * @param req
	 * @param resumeId
	 * @param userId
	 * @param project
	 * @param projectTime
	 * @param projectDescription
	 * @param duty
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> addProjectExp(HttpServletRequest req,int resumeId,int userId,String project,String projectTime,String projectDescription,String duty) throws UnsupportedEncodingException;
	
	/**
	 * �޸���Ŀ����
	 * @param req
	 * @param projectexpId
	 * @param userId
	 * @param project
	 * @param projectTime
	 * @param projectDescription
	 * @param duty
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> modifyProjectExp(HttpServletRequest req,int projectexpId,int userId,String project,String projectTime,String projectDescription,String duty) throws UnsupportedEncodingException;
	
	/**
	 * ɾ����Ŀ����
	 * @param projectexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteProjectExp(int projectexpId,int userId);
	
	/**
	 * ��Ӽ�������
	 * @param req
	 * @param userId
	 * @param resumeId
	 * @param skillName
	 * @param skillLevel
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> addSkill(HttpServletRequest req,int userId,int resumeId,String skillName,String skillLevel) throws UnsupportedEncodingException;
	
	/**
	 * �޸ļ�������
	 * @param req
	 * @param userId
	 * @param skillId
	 * @param skillName
	 * @param skillLevel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String,Object> modifySkill(HttpServletRequest req,int userId,int skillId,String skillName,String skillLevel) throws UnsupportedEncodingException;
	
	/**
	 * ɾ����������
	 * @param skillId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteSkill(int skillId,int userId);
	
	/**
	 * ��ӽ�������
	 * @param req
	 * @param userId
	 * @param resumeId
	 * @param school
	 * @param major
	 * @param education
	 * @param schoolTime
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String,Object> addEducationExp(HttpServletRequest req,int userId,int resumeId,String school,String major,String education,String schoolTime) throws UnsupportedEncodingException;
	
	/**
	 * �޸Ľ�������
	 * @param req
	 * @param userId
	 * @param educationexpId
	 * @param school
	 * @param major
	 * @param education
	 * @param schoolTime
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String,Object> modifyEducationExp(HttpServletRequest req,int userId,int educationexpId,String school,String major,String education,String schoolTime) throws UnsupportedEncodingException;
	
	/**
	 * ɾ����������
	 * @param educationexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteEducationExp(int educationexpId,int userId);
	
	/**
	 * �����������˲��Ƽ���
	 * @param keyword
	 * @return
	 */
	public List<Map<String,Object>> searchResume(String keyword,int page,int pageSize);
	
	
}
