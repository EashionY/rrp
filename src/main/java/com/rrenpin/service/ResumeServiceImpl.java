package com.rrenpin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.ResumeMapper;
import com.rrenpin.entity.Resume;
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
	
	
}
