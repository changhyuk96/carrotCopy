package src.test.web.util;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class ServiceUtil {
	
	Logger logger = LoggerFactory.getLogger(ServiceUtil.class);
	
	public MultiValueMap<String, String> requestToMap(HttpServletRequest request){
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	    
	    Enumeration<String> enumber = request.getParameterNames();
	    
	    while (enumber.hasMoreElements()) {
	        String key = enumber.nextElement().toString();
	        String[] values = request.getParameterValues(key);
	        String value=null;
	        if(values.length <= 1 ) {
	        	value = values[0];
	        	map.add(key, value);  
	        }
//	        else
//		        map.add(key, values);  
//	        
	    }
	    
	    return map;
	}
	
	public ResponseEntity<?> getResponseEntity(HttpServletRequest request, HttpServletResponse response, URI url, HttpMethod method, MediaType mediaType) {
		ResponseEntity<?> responseEntity = null ;

		try {
			
			Stream<Cookie> cookieStream = Stream.of(request.getCookies()).filter(n -> n.getName().equals("jwtToken"));
			String jwtToken = cookieStream.findFirst().get().getValue();		
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(mediaType, Charset.forName("UTF-8")));
			headers.add("jwtToken", jwtToken);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> requests = new HttpEntity<>(headers);
			responseEntity = restTemplate.exchange(url, method, requests, String.class);
			
		}catch(Unauthorized e) {
			
			Cookie cookie = new Cookie("jwtToken", "123");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.getAuthentication().setAuthenticated(false);
			
			logger.info(this.getClass() +" ::: "+ e.getMessage() + " redirect login.");
			try {
				response.sendRedirect("/login");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return ResponseEntity.ok().body("Please Login.");
			
		}catch(NoSuchElementException | NullPointerException e) {
			
			logger.info(this.getClass() +" ::: "+ e.getMessage() + " redirect login.");
			
			try {
				response.sendRedirect("/login");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return ResponseEntity.ok().body("Please Login.");
		}
		return responseEntity;
	}
	
	
	private String secret_key="sec_api_token";
	
	public Claims getClaims(String token) {
		
		try {
			Claims body = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
			
			return body;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
