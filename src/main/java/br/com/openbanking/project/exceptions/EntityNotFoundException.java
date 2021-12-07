package br.com.openbanking.project.exceptions;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String mensage) {
		super(mensage);
	}
	
}
