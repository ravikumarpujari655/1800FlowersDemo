package com.work.restwork.integrationtesting;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.restwork.RestworkApplication;
import com.work.restwork.entities.Post;
import com.work.restwork.service.RestService;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestworkApplication.class, 
webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTests {
	
	@LocalServerPort
    private int port;
	
	@InjectMocks
	RestService restservice = new RestService(new RestTemplateBuilder());
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	private HttpHeaders headers = new HttpHeaders();
	
	/* this utility method Maps an object into a JSON String. Uses a Jackson ObjectMapper */
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);		
	}
	
	/* This utility method to create the url for given uri */
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
	@Test
    public void getdistinctuseridandcountTest() throws Exception
    {
			
		String URI = "/api/task/getdistinctuseridandcount" ;
		SortedMap<Integer,Integer> map1 = new TreeMap<Integer, Integer>();
		for(int i=1; i<11;i++)
			map1.put(i, 10);
		String inputInJson = this.mapToJson(map1);
		HttpEntity<Map<Integer,Integer>> entity = new HttpEntity<Map<Integer,Integer>>(map1,headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort(URI),
				HttpMethod.GET, entity, String.class);
		String responseInJson = response.getBody();
		assertThat(responseInJson).isEqualTo(inputInJson);
    }
	
	@Test
	public void getmodifiedresponseTest() throws Exception {
		
		String URI = "/api/task/getmodifiedresponse" ;
		ArrayList<Post> posts = restservice.getModifiedResponse();
		String inputInJson = this.mapToJson(posts);
		HttpEntity<ArrayList<Post>> entity = new HttpEntity<ArrayList<Post>>(posts,headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort(URI),
				HttpMethod.GET, entity, String.class);
		String responseInJson = response.getBody();
		assertThat(responseInJson).isEqualTo(inputInJson);
			
	}
}


