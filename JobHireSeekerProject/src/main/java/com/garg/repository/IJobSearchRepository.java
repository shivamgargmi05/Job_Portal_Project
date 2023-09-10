package com.garg.repository;

import java.util.List;

import com.garg.model.Job;

public interface IJobSearchRepository {

	public List<Job> fetchAllJobsByText(String text);
	
}
