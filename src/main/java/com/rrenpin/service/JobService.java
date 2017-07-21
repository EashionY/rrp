package com.rrenpin.service;

import java.util.List;

import com.rrenpin.entity.Job;

public interface JobService {

	/**
	 * 列出所有工作岗位
	 * @return
	 */
	public List<Job> listJobs();
	
	
}
