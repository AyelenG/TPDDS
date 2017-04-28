package model.exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class ErrorCargaException extends UserException {
	
	public ErrorCargaException(Exception e){
		super("Error de lectura al cargar los datos.", e);
	}
}
