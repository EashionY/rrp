package com.rrenpin.service;

import com.rrenpin.entity.Resume;

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
	 */
	public Resume addBasicInfo(int userId,String empName,String sex,String birth,String workexp,String status,
			String phone,String email,String empRegion);
	
}
