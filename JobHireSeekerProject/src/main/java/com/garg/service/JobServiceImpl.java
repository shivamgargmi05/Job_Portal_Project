package com.garg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garg.model.Job;
import com.garg.repository.IJobRepository;
import com.garg.repository.IJobSearchRepository;

@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	private IJobRepository repository;	// SpringData will create InMemoryProxy implementation class object & inject to it

	@Autowired
	private IJobSearchRepository searchRepository;
	
	@Override
	public List<Job> fetchAllJobs() {
		// TODO Auto-generated method stub
		
		return repository.findAll();
	}

	@Override
	public Job saveNewJob(Job job) {
		// TODO Auto-generated method stub
	
		return repository.save(job);
	}

	@Override
	public List<Job> searchJobsByText(String text) {
		// TODO Auto-generated method stub
		
		return searchRepository.fetchAllJobsByText(text);
	}
	
}
