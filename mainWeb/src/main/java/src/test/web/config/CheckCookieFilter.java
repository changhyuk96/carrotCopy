package src.test.web.config;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CheckCookieFilter implements Filter{	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//		
//        HttpServletRequest requests = (HttpServletRequest) request;
//        MutableHttpServletRequest newRequest = new MutableHttpServletRequest(requests);
//        
//        Cookie jwtToken = Stream.of(requests.getCookies()).filter(c -> c.getName().equals("jwtToken")).findFirst().orElse(null);
//        
//        if(jwtToken != null)
//        	newRequest.putHeader("Authorization",jwtToken.getValue() );
        
        
        chain.doFilter(request, response);
		
	}

}
