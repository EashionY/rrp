package com.rrenpin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.ResumeMapper;
import com.rrenpin.entity.Resume;
import com.rrenpin.util.Upload;
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	@Resource
	private ResumeMapper resumeMapper;
	
	public Resume addBasicInfo(int userId, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) {
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
		resume = resumeMapper.findByUserId(userId);
		return resume;
	}

	public Resume modifyBasicInfo(int id, String empName, String sex, String birth, String workexp, String status,
			String phone, String email, String empRegion) {
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
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifyJobIntention(int id, String salary, String job, String workType, String workArea) {
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
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifyWorkExp(int id, String company1, String work1, String work1Time, String work1Description,
			String company2, String work2, String work2Time, String work2Description, String company3, String work3,
			String work3Time, String work3Description) {
		Resume resume = new Resume();
		resume.setId(id);
		resume.setCompany1(company1);
		resume.setWork1(work1);
		resume.setWork1Time(work1Time);
		resume.setWork1Description(work1Description);
		resume.setCompany2(company2);
		resume.setWork2(work2);
		resume.setWork2Time(work2Time);
		resume.setWork2Description(work2Description);
		resume.setCompany3(company3);
		resume.setWork3(work3);
		resume.setWork3Time(work3Time);
		resume.setWork3Description(work3Description);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改工作经验失败");
		}
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifyProjectExp(int id, String project1, String project1Time, String project1Description,
			String duty1, String project2, String project2Time, String project2Description, String duty2,
			String project3, String project3Time, String project3Description, String duty3) {
		Resume resume = new Resume();
		resume.setId(id);
		resume.setProject1(project1);
		resume.setProject1Time(project1Time);
		resume.setProject1Description(project1Description);
		resume.setDuty1(duty1);
		resume.setProject2(project2);
		resume.setProject2Time(project2Time);
		resume.setProject2Description(project2Description);
		resume.setDuty2(duty2);
		resume.setProject3(project3);
		resume.setProject3Time(project3Time);
		resume.setProject3Description(project3Description);
		resume.setDuty3(duty3);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改项目经验失败");
		}
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifySkill(int id, String skill) {
		Resume resume = new Resume();
		resume.setId(id);
		resume.setSkill(skill);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改技能评价失败");
		}
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifyEducationExp(int id, String school1, String major1, String education1, String school1Time,
			String school2, String major2, String education2, String school2Time) {
		Resume resume = new Resume();
		resume.setId(id);
		resume.setSchool1(school1);
		resume.setMajor1(major1);
		resume.setEducation1(education1);
		resume.setSchool1Time(school1Time);
		resume.setSchool2(school2);
		resume.setMajor2(major2);
		resume.setEducation2(education2);
		resume.setSchool2Time(school2Time);
		int i;
		try {
			i = resumeMapper.updateByPrimaryKeySelective(resume);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("连接服务器超时");
		}
		if(i != 1){
			throw new ResumeException("修改技能评价失败");
		}
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifySelfEvaluation(int id, String selfEvaluation) {
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
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}

	public Resume modifyHeadImg(int id, int userId, HttpServletRequest request){
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
		resume = resumeMapper.selectByPrimaryKey(id);
		return resume;
	}
	
	
}
