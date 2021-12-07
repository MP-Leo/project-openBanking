package br.com.openbanking.project.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}
