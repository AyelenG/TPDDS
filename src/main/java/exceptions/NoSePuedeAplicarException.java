package exceptions;

import org.uqbar.commons.model.UserException;

public class NoSePuedeAplicarException extends UserException {

	public NoSePuedeAplicarException(String mensaje) {
		super(mensaje);
	}
}
