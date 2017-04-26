package model.exceptions;

import org.uqbar.commons.model.UserException;

public class ErrorConsultaException extends UserException {
	public ErrorConsultaException(){
		super("Debe seleccionar los filtros correspondientes");
	}
}
