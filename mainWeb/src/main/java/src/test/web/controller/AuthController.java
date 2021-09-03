package src.test.web.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import src.test.web.util.ServiceUtil;

@RestController
public class AuthController {

	@Autowired
	PasswordEncoder passEncoder;
	
	@Autowired
	ServiceUtil serviceUtil;
	
	RestTemplate restTemplate = new RestTemplate();

	@PostMapping("/loginAction")
	public Object loginAction(HttpServletRequest request, HttpServletResponse response) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");		
		headers.add("u_id", request.getParameter("u_id"));
		headers.add("u_password", request.getParameter("u_password"));
		
		HttpEntity<?> requests = new HttpEntity<>(headers);
		URI url = URI.create("http://localhost:8090/auth/login");
		
		ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requests, String.class);
		
		
		if(!responseEntity.hasBody()) {
			Cookie cookie = new Cookie("jwtToken", responseEntity.getHeaders().getFirst("jwtToken"));
			
			cookie.setMaxAge(2*60);
			cookie.setSecure(true);
			cookie.setHttpOnly(true);
			
			response.addCookie(cookie);
		}
		
		return ResponseEntity.ok().body(responseEntity.getBody());
	}
	
	
	@PostMapping("/signUpAction")
	public String signUpAction(HttpServletRequest request) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8")));
		
		URI url = URI.create("http://localhost:8090/auth/signUp");
		HttpEntity<?> requests = new HttpEntity<>(serviceUtil.requestToMap(request), headers);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requests, String.class);
		
		return response.getBody();
	}
}
