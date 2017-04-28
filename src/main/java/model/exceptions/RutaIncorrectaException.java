package model.exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class RutaIncorrectaException extends UserException {
	
	public RutaIncorrectaException(Exception e){
		super("La ruta especificada es incorrecta, por favor ingrese una ruta válida.", e);
	}
}
