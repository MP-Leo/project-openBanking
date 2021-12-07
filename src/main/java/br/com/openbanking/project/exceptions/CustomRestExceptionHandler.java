package br.com.openbanking.project.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	


	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> entityNotFound(
	  EntityNotFoundException ex, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		CustomError custom = new CustomError(status, ex.getMessage());
		
		return new ResponseEntity<Object>(custom, status);

	}
	

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    	MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

    	CustomError custom = new CustomError(status, "Invalid form");
    	
    	return new ResponseEntity<Object>(custom, status);
    	
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
    		HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
    		HttpStatus status, WebRequest request){
    	
    	
    	CustomError custom = new CustomError(status, "Operation not allowed");
    	
    	return new ResponseEntity<Object>(custom, status);
    	
    }
    
}
