package com.rrenpin.service;

import java.io.File;
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
import com.rrenpin.entity.EducationExp;
import com.rrenpin.entity.ProjectExp;
import com.rrenpin.entity.Resume;
import com.rrenpin.entity.Skills;
import com.rrenpin.entity.WorkExp;
import com.rrenpin.exception.DataBaseException;
import com.rrenpin.exception.ImgUploadException;
import com.rrenpin.exception.ResumeException;
import com.rrenpin.util.Image;
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
			String phone, String email, String empRegion, String topDegree) throws UnsupportedEncodingException {
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
		resume.setTopDegree(topDegree);
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
			String phone, String email, String empRegion, String topDegree) throws UnsupportedEncodingException {
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
		resume.setTopDegree(topDegree);
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

	public Map<String, Object> modifyHeadImg(int id, int userId, String headImg){
		//去除数据头
		String[] strBase64 = headImg.split(",");
		String imgBase64 = strBase64[1];
		//windows系统本地路径
		String path = "D:\\rrpUpload\\"+userId;
		//linux系统路径（路径改动之后需要相应的更改server.xml中的context标签）
//	    String path = "";
		String filename = "resumeHeadImg.png";
		//上传logo图片
		boolean success = Image.base64ToImage(imgBase64, path, filename);
		if(!success){
			throw new ImgUploadException("头像上传失败");
		}
		Resume resume = new Resume();
		resume.setId(id);
		resume.setHeadImg("images/"+userId+File.separator+filename);
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
			throw new DataBaseException("无对应的简历");
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
			i = workExpMapper.insert(workExp);
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
			i = projectExpMapper.insert(projectExp);
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

	public Map<String, Object> addEducationExp(HttpServletRequest req, int userId, int resumeId, String school,
			String major, String education, String schoolTime) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		EducationExp educationExp = new EducationExp();
		educationExp.setUserId(userId);
		educationExp.setResumeId(resumeId);
		educationExp.setSchool(school);
		educationExp.setMajor(major);
		educationExp.setEducation(education);
		educationExp.setSchoolTime(schoolTime);
		int i;
		try {
			i = educationExpMapper.insert(educationExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("教育经历添加失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> modifyEducationExp(HttpServletRequest req, int userId, int educationexpId, String school,
			String major, String education, String schoolTime) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		EducationExp educationExp = educationExpMapper.selectByPrimaryKey(educationexpId);
		if(educationExp==null){
			throw new ResumeException("无对应的教育经历");
		}
		educationExp.setSchool(school);
		educationExp.setSchoolTime(schoolTime);
		educationExp.setEducation(education);
		educationExp.setMajor(major);
		int i;
		try {
			i = educationExpMapper.updateByPrimaryKeySelective(educationExp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("教育经历修改失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public Map<String, Object> deleteEducationExp(int educationexpId, int userId) {
		int i;
		try {
			i = educationExpMapper.deleteByPrimaryKey(educationexpId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("教育经历删除失败");
		}
		Map<String,Object> result = findByUserId(userId);
		return result;
	}

	public List<Map<String, Object>> searchResume(String keyword,int page,int pageSize) {
		keyword = "%"+keyword+"%";
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> list;
		try {
			list = resumeMapper.searchResume(keyword, offset, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
//		for(Map<String,Object> map : list){
//			int userId = (Integer) map.get("user_id");
//			List<Map<String,Object>> workExprience = workExpMapper.findByUserId(userId);
//			map.put("workExprience", workExprience);
//			List<Map<String,Object>> projectExp = projectExpMapper.findByUserId(userId);
//			map.put("projectExp", projectExp);
//			List<Map<String,Object>> skills = skillsMapper.findByUserId(userId);
//			map.put("skills", skills);
//			List<Map<String,Object>> educationExp = educationExpMapper.findByUserId(userId);
//			map.put("educationExp", educationExp);
//		}
		return list;
	}	
	
}
