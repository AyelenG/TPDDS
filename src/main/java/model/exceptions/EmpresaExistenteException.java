package model.exceptions;

import org.uqbar.commons.model.UserException;

public class EmpresaExistenteException extends UserException {

	public EmpresaExistenteException() {
		super("Ya existe esta empresa.");
	}
}
