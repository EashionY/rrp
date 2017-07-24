package com.rrenpin.service;

import javax.servlet.http.HttpServletRequest;

import com.rrenpin.entity.Resume;

public interface ResumeService {

	/**
	 * ���Ӽ����Ļ�����Ϣ
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
	 */
	public Resume addBasicInfo(int userId,String empName,String sex,String birth,String workexp,String status,
			String phone,String email,String empRegion);
	
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
	 */
	public Resume modifyBasicInfo(int id,String empName,String sex,String birth,String workexp,String status,
			String phone,String email,String empRegion);
	
	/**
	 * �޸ļ�������ְ����
	 * @param id
	 * @param salary
	 * @param job
	 * @param workType
	 * @param workArea
	 * @return
	 */
	public Resume modifyJobIntention(int id,String salary,String job,String workType,String workArea);
	
	/**
	 * �޸ļ����Ĺ�������
	 * @param id
	 * @param company1
	 * @param work1
	 * @param work1Time
	 * @param work1Description
	 * @param company2
	 * @param work2
	 * @param work2Time
	 * @param work2Description
	 * @param company3
	 * @param work3
	 * @param work3Time
	 * @param work3Description
	 * @return
	 */
	public Resume modifyWorkExp(int id,String company1,String work1,String work1Time,String work1Description,
			String company2,String work2,String work2Time,String work2Description,
			String company3,String work3,String work3Time,String work3Description);
	
	/**
	 * �޸ļ�������Ŀ����
	 * @param id
	 * @param project1
	 * @param project1Time
	 * @param project1Description
	 * @param duty1
	 * @param project2
	 * @param project2Time
	 * @param project2Description
	 * @param duty2
	 * @param project3
	 * @param project3Time
	 * @param project3Description
	 * @param duty3
	 * @return
	 */
	public Resume modifyProjectExp(int id,String project1,String project1Time,String project1Description,String duty1,
			String project2,String project2Time,String project2Description,String duty2,
			String project3,String project3Time,String project3Description,String duty3);
	
	/**
	 * �޸ļ�������
	 * @param id
	 * @param skill
	 * @return
	 */
	public Resume modifySkill(int id,String skill);
	
	/**
	 * �޸Ľ�������
	 * @param id
	 * @param school1
	 * @param major1
	 * @param education1
	 * @param school1Time
	 * @param school2
	 * @param major2
	 * @param education2
	 * @param school2Time
	 * @return
	 */
	public Resume modifyEducationExp(int id,String school1,String major1,String education1,String school1Time,
			String school2,String major2,String education2,String school2Time);
	
	/**
	 * �޸���������
	 * @param id
	 * @param selfEvaluation
	 * @return
	 */
	public Resume modifySelfEvaluation(int id,String selfEvaluation);
	
	/**
	 * �޸ļ���ͷ��
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	public Resume modifyHeadImg(int id,int userId,HttpServletRequest request);
}