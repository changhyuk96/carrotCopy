package src.test.web.controller;

import javax.naming.ServiceUnavailableException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value= {ServiceUnavailableException.class})
	public String requestMethodHandler(ServiceUnavailableException e) {
		
		return "/errors/serviceError";
	}
	
	@ExceptionHandler(value= {HttpServerErrorException.class})
	public String requestMethodHandler(HttpServerErrorException e) {
		
		return "/errors/serverError";
	}
}
