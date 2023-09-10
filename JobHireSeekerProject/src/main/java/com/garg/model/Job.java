package com.garg.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="Job")	// Mongodb collection/table name like @Entity for SpringDataJPA
public class Job {

	// not retreiving _id for internal purpose only
	
	private String profile;
	private String desc;
	private int exp;
	private String[] techs;
	
}
