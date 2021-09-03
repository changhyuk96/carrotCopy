package src.test.product.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value= {HttpRequestMethodNotSupportedException.class})
	public String requestMethodHandler(HttpRequestMethodNotSupportedException e) {
		
		return "유효하지 않은 Request입니다. Method Type을 확인해주세요.";
	}
	
	
}
