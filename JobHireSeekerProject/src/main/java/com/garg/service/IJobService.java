package com.garg.service;

import java.util.List;

import com.garg.model.Job;

public interface IJobService {

	public List<Job> fetchAllJobs();
	
	public Job saveNewJob(Job job);
	
	public List<Job> searchJobsByText(String text);
	
}
