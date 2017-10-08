package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class NoSePuedeAplicarException extends UserException {

	public NoSePuedeAplicarException(String mensaje) {
		super(mensaje);
	}
}
