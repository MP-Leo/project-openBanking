package br.com.openbanking.project.exceptions;

import org.springframework.http.HttpStatus;

public class CustomError {
	
	private Integer status;
	private String message;
	
	// CONSTRUCTORS 
	public CustomError(HttpStatus status, String message) {
	        this.status = status.value();
	        this.message = message;
	   }

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
	
}
