package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class ArchivoConErroresException extends UserException {

	public ArchivoConErroresException(Exception e) {
		super("El archivo contiene errores en los datos, modifiquelo e intente otra vez.", e);
	}
}
