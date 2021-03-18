package com.work.restwork.unittesting;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.work.restwork.entities.Post;
import com.work.restwork.service.RestService;

@SpringBootTest
class RestworkApplicationTests {
	
	@InjectMocks
	RestService restservice = new RestService(new RestTemplateBuilder());
	
	
	@Test
	public void getDistinctUserIdAndCountTest() {
		SortedMap<Integer,Integer> map1 = new TreeMap<Integer, Integer>();
		for(int i=1; i<11;i++)
			map1.put(i, 10);
		Map<Integer,Integer> map2 = restservice.getDistinctUserIdAndCount();
		assertEquals(map1.keySet(),map2.keySet());
	}
	
	@Test
	public void getModifiedResponseTest() {
		ArrayList<Post> posts = restservice.getModifiedResponse();
		assertEquals(100, posts.size());
		assertEquals("1800Flowers",posts.get(3).getTitle());
		assertEquals("1800Flowers",posts.get(3).getBody());		
	}

}
