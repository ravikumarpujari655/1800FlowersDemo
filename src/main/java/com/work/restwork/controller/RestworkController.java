package com.work.restwork.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.work.restwork.entities.Post;
import com.work.restwork.service.RestService;

@RestController
@RequestMapping(value="/api/task")
public class RestworkController {
	
	@Autowired
	private RestService restservice;

	private  final RestTemplate restTemplate;
	private String url = "https://jsonplaceholder.typicode.com/posts";
		
	public RestworkController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	public ArrayList<Post> getPostsPlainJSON() {
		Post posts[] = this.restTemplate.getForObject(url, Post[].class);
        return new ArrayList<Post>(Arrays.asList(posts));
    }
	
	@GetMapping(value="/getdistinctuseridandcount")
	@ResponseBody
	public Map<Integer,Integer> getDistinctUserIdAndCount() {
		
    	return restservice.getDistinctUserIdAndCount();
    	   
    }
	
	@GetMapping(value="/getmodifiedresponse")
	public ArrayList<Post> getModifiedResponse() {
		
		return restservice.getModifiedResponse();
        
    }

}
