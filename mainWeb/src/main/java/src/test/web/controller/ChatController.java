package src.test.web.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import src.test.web.util.ServiceUtil;

@RequestMapping("/api/chat")
@RestController
public class ChatController {
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	ServiceUtil serviceUtil; 
	
	@GetMapping("/")
	public Object getList(HttpServletRequest request, HttpServletResponse response) {
		
		
		URI url = URI.create("http://localhost:8090/api/chat/");
		ResponseEntity<?> responseEntity = serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET, MediaType.APPLICATION_FORM_URLENCODED);
		
		return responseEntity.getBody();
		
	}
	
}
