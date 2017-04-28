package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class EmpresaVaciaException extends UserException {

	public EmpresaVaciaException() {
		super("Debe ingresar el simbolo y nombre de la empresa.");
	}
}