package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class CuentaVaciaException extends UserException {

	public CuentaVaciaException() {
		super("Debe ingresar el periodo, nombre y valor de la cuenta.");
	}
}