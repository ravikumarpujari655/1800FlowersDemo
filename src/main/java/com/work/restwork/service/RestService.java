package com.work.restwork.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.work.restwork.entities.Post;


@Service
public class RestService {
	
	private final RestTemplate restTemplate;
	private String url = "https://jsonplaceholder.typicode.com/posts";
	
	public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

	public ArrayList<Post> getPostsPlainJSON() {
		Post posts[] = this.restTemplate.getForObject(url, Post[].class);
        return new ArrayList<Post>(Arrays.asList(posts));
    }

	public Map<Integer,Integer> getDistinctUserIdAndCount() {
		
		ArrayList<Post> posts = getPostsPlainJSON();
		SortedMap<Integer,Integer> userMap = new TreeMap<Integer, Integer>();
		for(Post post : posts) {
    		
    		if(userMap.containsKey(post.getUserId())) {
    			int val = userMap.get(post.getUserId()) +1;
    			userMap.put(post.getUserId(), val);
    		} else {
    			userMap.put(post.getUserId(), 1);
    		}
    	}
		
		return userMap;
		
	}

	public ArrayList<Post> getModifiedResponse() {
		
		ArrayList<Post> posts = getPostsPlainJSON();
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
