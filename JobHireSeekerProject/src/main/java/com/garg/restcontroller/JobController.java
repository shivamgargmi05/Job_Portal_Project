package com.garg.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garg.model.Job;
import com.garg.service.IJobService;

import springfox.documentation.annotations.ApiIgnore;

@RequestMapping
@RestController
@CrossOrigin(origins="http://localhost:3000/") 	// Annotation for permitting cross-origin requests on specific handler classes/handler methods coming from React UI app - Access Control Allow Origin Policy
public class JobController {

	@Autowired
	private IJobService service;
	
	@ApiIgnore			// by default swagger ui shows all http request mapping modes/types like GET/POST/DELETE/PUT/PATCH, to show only user-defined mapping url 
	@RequestMapping("/")
	public void redirectToSwaggerUI(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
	
	@GetMapping("/allPosts")
	@CrossOrigin
	//	public ResponseEntity<List<Job>> getAllJobs() {
	public List<Job> getAllJobs() {
		List<Job> l=service.fetchAllJobs();
		
		// response from server will be sent as list of java objects but will be received by client as list of json objects automatically conversion using HttpMessageConvertor DispatcherServlet component
		//	return new ResponseEntity<List<Job>>(l, HttpStatus.OK);
		return l;
	}
	
	// request object data from client will be sent as json but will be received by server as java object automatically conversion using HttpMessageConvertor DispatcherServlet component
	@PostMapping("/post")
	@CrossOrigin
	//	public ResponseEntity<Job> registerNewJob(@RequestBody Job job) {
	public Job registerNewJob(@RequestBody Job job) {
		Job savedJob=service.saveNewJob(job);
		
		//	return new ResponseEntity<Job>(savedJob, HttpStatus.CREATED);
		return savedJob;
	}
	
	@GetMapping("posts/{query}")
	@CrossOrigin
	//	public ResponseEntity<List<Job>> searchJobs(@PathVariable String query) {
	public List<Job> searchJobs(@PathVariable String query) {
		List<Job> l=service.searchJobsByText(query);
		
		//	return new ResponseEntity<List<Job>>(l, HttpStatus.OK);
		return l;
	}
	
}
