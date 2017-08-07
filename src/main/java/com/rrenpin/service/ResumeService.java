package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface ResumeService {

	/**
	 * 添加简历的基本信息
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
	 * 修改简历的基本信息
	 * @param id 简历id
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
	 * 修改简历的求职意向
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
	 * 修改自我描述
	 * @param id
	 * @param selfEvaluation
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Map<String, Object> modifySelfEvaluation(HttpServletRequest request,int id,int userId,String selfEvaluation) throws UnsupportedEncodingException;
	
	/**
	 * 修改简历头像
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	public Map<String, Object> modifyHeadImg(int id,int userId,String headImg);
	
	/**
	 * 通过userId查找简历
	 * @param userId
	 * @return
	 */
	public Map<String,Object> findByUserId(int userId);
	
	/**
	 * 修改简历名
	 * @param id
	 * @param resumeName
	 * @return
	 */
	public Map<String, Object> modifyResumeName(int id,int userId,String resumeName);
	
	/**
	 * 添加工作经验
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
	 * 修改工作经验
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
	 * 删除工作经验
	 * @param workexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteWorkExp(int workexpId,int userId);
	
	/**
	 * 添加项目经验
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
	 * 修改项目经验
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
	 * 删除项目经验
	 * @param projectexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteProjectExp(int projectexpId,int userId);
	
	/**
	 * 添加技能评价
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
	 * 修改技能评价
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
	 * 删除技能评价
	 * @param skillId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteSkill(int skillId,int userId);
	
	/**
	 * 添加教育经历
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
	 * 修改教育经历
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
	 * 删除教育经历
	 * @param educationexpId
	 * @param userId
	 * @return
	 */
	public Map<String,Object> deleteEducationExp(int educationexpId,int userId);
	
	/**
	 * 搜索简历（人才推荐）
	 * @param keyword
	 * @return
	 */
	public List<Map<String,Object>> searchResume(String keyword,int page,int pageSize);
	
	
}
