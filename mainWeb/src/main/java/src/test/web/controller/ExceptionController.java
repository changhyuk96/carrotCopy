package src.test.web.controller;

import javax.naming.ServiceUnavailableException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value= {ServiceUnavailableException.class})
	public String requestMethodHandler(ServiceUnavailableException e) {
		
		return "서비스 준비중입니다.";
	}
}
