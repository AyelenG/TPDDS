package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class EmpresaExistenteException extends UserException {

	public EmpresaExistenteException() {
		super("Ya existe esta empresa.");
	}
}
