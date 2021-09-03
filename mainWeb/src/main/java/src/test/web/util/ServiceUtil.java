package src.test.web.util;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceUtil {
	
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
	
	public ResponseEntity<?> getResponseEntity(HttpServletRequest request, HttpServletResponse response, URI url, HttpMethod method) {
		ResponseEntity<?> responseEntity = null ;

		try {
			
			Stream<Cookie> cookieStream = Stream.of(request.getCookies()).filter(n -> n.getName().equals("jwtToken"));
			String jwtToken = cookieStream.findFirst().get().getValue();		
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8")));
			headers.add("jwtToken", jwtToken);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<?> requests = new HttpEntity<>(headers);
			responseEntity = restTemplate.exchange(url, method, requests, String.class);
			
		}catch(BadRequest e) {
			
			Cookie cookie = new Cookie("jwtToken", "123");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			return ResponseEntity.ok().body("Please Login.");
			
		}catch(NoSuchElementException | NullPointerException e) {
			
			System.out.println(this.getClass() +" ::: "+ e.getMessage());
			
			return ResponseEntity.ok().body("Please Login.");
		}
		return responseEntity;
	}
}
