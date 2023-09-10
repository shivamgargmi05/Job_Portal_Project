package com.garg.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;	// Mongodb uses JSON for external representation of data but internally uses BSON(Binary SON) to store data.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import com.garg.model.Job;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/* 1. Mongodb Index Feature - 
to speedup/fast searching process of data by keywords using indexing concept
	
Create Search Index by Visual Editor option, choose your db/db1, collection name/job & index search name(default), 
refine your index later, check your search index status active/in-progress & test it.
Dynamic - It will index all the json fields data 
Static - Will index only required fields as per user need 

2. Mongodb Aggregation Feature - 
Aggregation means - apply filters one by one i.e., pipeline by choosing stages & criteria filter
1. search index filter - $search
	{
		index: 'default',	// index search name
		text: {
			query: 'java',
			path: ['profile', 'desc', 'techs']
		}
	}
	
2. Sort acc to experience order - $sort - 1 for asc, -1 for desc
	{
		exp : 1
	}

3. Limit no. of documents - $limit - 5

AS of now, we have used Mongodb index, aggregation feature from mongodb cloud atlas/Mongodb GUI client compass 
but how to implement these features in java application - Take export to language auto code generation mongodb feature
*/

@Repository
public class JobSearchRepositoryImpl implements IJobSearchRepository {

	@Autowired
	private MongoClient mongoClient;
	// MongoClient mongoClient=new MongoClient( new MongoClientURI("") );	// mongodb cloud atlas db server url/mongodb local server url
	
	@Autowired
	private MongoConverter mongoConverter;	// to convert mongodb json document object to java entity/Job object 
	
	@Override
	public List<Job> fetchAllJobsByText(String text) {
		// TODO Auto-generated method stub
		
		// or get values from application.properties file
		MongoDatabase database=mongoClient.getDatabase("db1");					// mongodb db name
		MongoCollection<Document> collection=database.getCollection("Job");		// mongodb Table/Collection name
		
		// for mongodb search index & aggregation feature, not used AggregateIterable
		Iterable<Document> result=collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("index", "default")	// search index name is default 
			            .append("text", 
			    new Document("query", text)	// search acc to text in mentioned fields
			                .append("path", Arrays.asList("profile", "desc", "techs")))), 
			    new Document("$sort", 
			    new Document("exp", 1L)), // sort acc to experience in ascending order
			    new Document("$limit", 5L)));	// limit no. of outputs
		
		// to convert mongodb json document to Java entity/job object
		final List<Job> l=new ArrayList<>(); 
		result.forEach( document -> {
					Job job=mongoConverter.read(Job.class, document); 
				
					l.add(job);
				}
			);
		
		return l;
	}

}
