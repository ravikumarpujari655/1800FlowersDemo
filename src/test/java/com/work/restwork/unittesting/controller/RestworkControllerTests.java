package com.work.restwork.unittesting.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.restwork.controller.RestworkController;
import com.work.restwork.entities.Post;
import com.work.restwork.service.RestService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=RestworkController.class)
public class RestworkControllerTests 
{
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestService restservice;

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);		
	}
	
	@Test
	public void testgetdistinctuseridandcount() throws Exception{
	SortedMap<Integer,Integer> map1 = new TreeMap<Integer, Integer>();
	for(int i=1; i<11;i++)
		map1.put(i, 10);
	String expectedInJson = this.mapToJson(map1);
	String URI = "/api/task/getdistinctuseridandcount" ;
	Mockito.when(restservice.getDistinctUserIdAndCount()).thenReturn(map1);
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get(URI)
			.accept(MediaType.APPLICATION_JSON)
			.content(expectedInJson)
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	MockHttpServletResponse response = result.getResponse();
	String outputInJson = response.getContentAsString();
	assertThat(outputInJson).isEqualTo(expectedInJson);
	}
	
	@Test
	public void testgetmodifiedresponse() throws Exception{
	ArrayList<Post> posts = restservice.getModifiedResponse();
	String expectedInJson = this.mapToJson(posts);
	String URI = "/api/task/getmodifiedresponse" ;
	Mockito.when(restservice.getModifiedResponse()).thenReturn(posts);
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get(URI)
			.accept(MediaType.APPLICATION_JSON)
			.content(expectedInJson)
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	MockHttpServletResponse response = result.getResponse();
	String outputInJson = response.getContentAsString();
	assertThat(outputInJson).isEqualTo(expectedInJson);
	}
	
}
