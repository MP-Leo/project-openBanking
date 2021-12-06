package br.com.openbanking.project.exceptions;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex,  HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
			    
	    CustomError custom = 
	      new CustomError(HttpStatus.BAD_REQUEST, "Credenciais do formulario"
	      		+ " invalidas");
	   
	    return new ResponseEntity(custom, HttpStatus.BAD_REQUEST);
	}
}
