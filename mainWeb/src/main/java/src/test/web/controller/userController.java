package src.test.web.controller;


import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import src.test.web.util.ServiceUtil;

@RestController
public class userController {
	
	@Autowired
	ServiceUtil serviceUtil; 

	@GetMapping("/api/users/")
	public Object usersInfo(HttpServletRequest request, HttpServletResponse response) {
		
		URI url = URI.create("http://localhost:8090/api/user/members");

		
		return serviceUtil.getResponseEntity(request, response, url, HttpMethod.GET);
	}
	
	@GetMapping("/api/user/")
	public Object userInfo(HttpServletRequest request, HttpServletResponse response) {
		
		URI url = URI.create("http://localhost:8090/api/user/member");
		
		return serviceUtil.getResponseEntity(request, response,url, HttpMethod.GET);
	}
	
	

	
}
