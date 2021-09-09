package src.test.product.controller;

import javax.naming.ServiceUnavailableException;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value= {HttpRequestMethodNotSupportedException.class})
	public String requestMethodHandler(HttpRequestMethodNotSupportedException e) {
		
		return "유효하지 않은 Request입니다. Method Type을 확인해주세요.";
	}
	
	@ExceptionHandler(value= {ServiceUnavailable.class})
	public Object serviceUnavailable(ServiceUnavailable e) {
		return "서비스 사용 불가";
	}
	
}
