package com.rrenpin.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.EducationExpMapper;
import com.rrenpin.dao.ProjectExpMapper;
import com.rrenpin.dao.ResumeMapper;
import com.rrenpin.dao.SkillsMapper;
import com.rrenpin.dao.WorkExpMapper;
import com.rrenpin.entity.ProjectExp;
import com.rrenpin.entity.Resume;
import com.rrenpin.entity.Skills;
import com.rrenpin.entity.WorkExp;
import com.rrenpin.util.Upload;
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	@Resource
	private ResumeMapper resumeMapper;
	
	@Resource
	private EducationExpMapper educationExpMapper;
	
	@Resource
	private ProjectExpMapper projectExpMapper;
	
	@Resource
	private SkillsMapper skillsMapper;
	
	@Resource
	private WorkExpMapper workExpMapper;
	
	public Map<String,Object> addBasicInfo(HttpServletRequest request, int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Resume resume = new Resume();
		//简历名默认为“我的简历”
		resume.setResumeName("我的简历");
		resume.setUserId(userId);
		resume.setEmpName(empName);
		resume.setSex(sex);
		resume.setBirth(birth);
		resume.setWorkExp(workexp);
		resume.setStatus(status);
		resume.setPhone(phone);
		resume.setEmail(email);
		resume.setEmpRegion(empRegion);
		int i;
		try {
			i = resumeMapper.insert(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("添加简历基本信息失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyBasicInfo(HttpServletRequest request, int id, int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Resume resume = new Resume();
		resume.setId(id);
		resume.setEmpName(empName);
		resume.setSex(sex);
		resume.setBirth(birth);
		resume.setWorkExp(workexp);
		resume.setStatus(status);
		resume.setPhone(phone);
		resume.setEmail(email);
		resume.setEmpRegion(empRegion);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改简历基本信息失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyJobIntention(HttpServletRequest request, int id, int userId, String salary, String job, String workType, String workArea) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Resume resume = new Resume();
		resume.setId(id);
		resume.setSalary(salary);
		resume.setJob(job);
		resume.setWorkType(workType);
		resume.setWorkArea(workArea);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改求职意向失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifySelfEvaluation(HttpServletRequest request, int id, int userId, String selfEvaluation) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Resume resume = new Resume();
		resume.setId(id);
		resume.setSelfEvaluation(selfEvaluation);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改自我描述失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyHeadImg(int id, int userId, HttpServletRequest request){
		String headImgPath;
		try {
			List<String> paths = Upload.uploadImg(request, ""+userId, "headImg");
			headImgPath = paths.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ImgUploadException("头像上传失败");
		}
		Resume resume = new Resume();
		resume.setId(id);
		resume.setHeadImg(headImgPath);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改头像失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> findByUserId(int userId) {
		Map<String, Object> result;
		try {
			result = resumeMapper.findByUserId(userId);
			List<Map<String,Object>> workExprience = workExpMapper.findByUserId(userId);
			result.put("workExprience", workExprience);
			List<Map<String,Object>> projectExp = projectExpMapper.findByUserId(userId);
			result.put("projectExp", projectExp);
			List<Map<String,Object>> skills = skillsMapper.findByUserId(userId);
			result.put("skills", skills);
			List<Map<String,Object>> educationExp = educationExpMapper.findByUserId(userId);
			result.put("educationExp", educationExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		return result;
	}

	public Map<String,Object> modifyResumeName(int id, int userId, String resumeName) {
		int i;
		try {
			Resume resume = resumeMapper.selectByPrimaryKey(id);
			resume.setResumeName(resumeName);
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改简历名失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> addWorkExp(HttpServletRequest req, int resumeId, int userId, String company, String work, String workTime,
			String workDescription) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		WorkExp workExp = new WorkExp();
		workExp.setResumeId(resumeId);
		workExp.setUserId(userId);
		workExp.setCompany(company);
		workExp.setWork(work);
		workExp.setWorkTime(workTime);
		workExp.setWorkDescription(workDescription);
		int i;
		try {
			i = workExpMapper.insertSelective(workExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("工作经验添加失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyWorkExp(HttpServletRequest req, int workexpId, int userId, String company, String work, String workTime,
			String workDescription) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		WorkExp workExp = workExpMapper.selectByPrimaryKey(workexpId);
		if(workExp==null){
			throw new ResumeException("无对应的工作经验");
		}
		workExp.setCompany(company);
		workExp.setWork(work);
		workExp.setWorkTime(workTime);
		workExp.setWorkDescription(workDescription);
		int i;
		try {
			i = workExpMapper.updateByPrimaryKeySelective(workExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("工作经验修改失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> deleteWorkExp(int workexpId, int userId) {
		int i;
		try {
			i = workExpMapper.deleteByPrimaryKey(workexpId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("工作经验删除失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> addProjectExp(HttpServletRequest req, int resumeId, int userId, String project,
			String projectTime, String projectDescription, String duty) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		ProjectExp projectExp = new ProjectExp();
		projectExp.setResumeId(resumeId);
		projectExp.setUserId(userId);
		projectExp.setProject(project);
		projectExp.setProjectTime(projectTime);
		projectExp.setProjectDescription(projectDescription);
		projectExp.setDuty(duty);
		int i;
		try {
			i = projectExpMapper.insertSelective(projectExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("项目经验添加失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyProjectExp(HttpServletRequest req, int projectexpId, int userId, String project,
			String projectTime, String projectDescription, String duty) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		ProjectExp projectExp = projectExpMapper.selectByPrimaryKey(projectexpId);
		if(projectExp==null){
			throw new ResumeException("无对应的项目经验");
		}
		projectExp.setProject(project);
		projectExp.setProjectTime(projectTime);
		projectExp.setProjectDescription(projectDescription);
		projectExp.setDuty(duty);
		int i;
		try {
			i = projectExpMapper.updateByPrimaryKeySelective(projectExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("项目经验修改失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> deleteProjectExp(int projectexpId, int userId) {
		int i;
		try {
			i = projectExpMapper.deleteByPrimaryKey(projectexpId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("项目经验删除失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> addSkill(HttpServletRequest req, int userId, int resumeId, String skillName,
			String skillLevel) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		Skills skill = new Skills();
		skill.setUserId(userId);
		skill.setResumeId(resumeId);
		skill.setSkillName(skillName);
		skill.setSkillLevel(skillLevel);
		int i;
		try {
			i = skillsMapper.insert(skill);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("技能评价添加失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifySkill(HttpServletRequest req, int userId, int skillId, String skillName,
			String skillLevel) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		Skills skill = skillsMapper.selectByPrimaryKey(skillId);
		if(skill==null){
			throw new ResumeException("无对应的技能评价");
		}
		skill.setSkillName(skillName);
		skill.setSkillLevel(skillLevel);
		int i;
		try {
			i = skillsMapper.updateByPrimaryKeySelective(skill);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("技能评价添加失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> deleteSkill(int skillId, int userId) {
		int i;
		try {
			i = skillsMapper.deleteByPrimaryKey(skillId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("技能评价删除失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}
	
	
}
