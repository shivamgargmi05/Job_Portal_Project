package com.garg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.garg.model.Job;

public interface IJobRepository extends MongoRepository<Job, String> {

	// primary key _id of String type
	
}
