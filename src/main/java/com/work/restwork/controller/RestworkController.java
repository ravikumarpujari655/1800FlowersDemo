package com.work.restwork.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.restwork.entities.Post;
import com.work.restwork.service.RestService;

@RestController
@RequestMapping(value="/api/task")
public class RestworkController {
	
	@Autowired
	private RestService restservice;
	
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
