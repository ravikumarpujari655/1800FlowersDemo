package com.work.restwork;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping(value="/api/task")
public class RestworkController {
	
	private  final RestTemplate restTemplate;
	private String url = "https://jsonplaceholder.typicode.com/posts";
	
	public RestworkController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	public Post[] getPostsPlainJSON() {
        return this.restTemplate.getForObject(url, Post[].class);
    }
	
	@GetMapping(value="/countendpoints") 
	public int countEndpoints() {
	    	Post posts[] = this.restTemplate.getForObject(url, Post[].class);
	    	return posts.length;
	    }
	@GetMapping(value="/getdistinctuseridandcount")
	public String getDistinctUserIdAndCount() {
    	Post posts[] = this.restTemplate.getForObject(url, Post[].class);
    	SortedMap<Integer,Integer> map = new TreeMap();
    	
    	for(Post post : posts) {
    		
    		if(map.containsKey(post.getUserId())) {
    			int val = map.get(post.getUserId()) +1;
    			map.put(post.getUserId(), val);
    		} else {
    			map.put(post.getUserId(), 1);
    		}
    	}
    	
    	Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(map,gsonType);
        
    	return gsonString;
    }
	
	@GetMapping(value="/getmodifiedresponse")
	public Post[] getModifiedResponse() {
    	Post posts[] = this.restTemplate.getForObject(url, Post[].class);
    	
    	for (Post post : posts) {
    		if(post.getId()==4)
    		{
    		post.setTitle("1800Flowers");
    		post.setBody("1800Flowers");
     		}
    	}
    	return posts;
    }
	

}
