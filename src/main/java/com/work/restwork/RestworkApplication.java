package com.work.restwork;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestworkApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		
		SpringApplication.run(RestworkApplication.class, args);
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		
		RestService rest = new RestService(restTemplateBuilder);
		
		Post posts[] = rest.getModifiedResponse();
		
		System.out.println(rest.countEndpoints());
		
		System.out.println(rest.getDistinctUserIdAndCount());
		
		for(Post post : posts) {
			System.out.println(post.getUserId() + " " + post.getId() + " " + post.getTitle() + " " + post.getBody());
		}
	}

}
