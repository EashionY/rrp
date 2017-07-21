package com.rrenpin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rrenpin.dao.JobMapper;
import com.rrenpin.entity.Job;
@Service("jobService")
public class JobServiceImpl implements JobService {

	@Resource
	private JobMapper jobMapper;
	
	public List<Job> listJobs() {
		List<Job> jobs = jobMapper.listAllJob();
		return jobs;
	}

}
