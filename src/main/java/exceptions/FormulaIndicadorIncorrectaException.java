package exceptions;

import org.uqbar.commons.model.UserException;

public class FormulaIndicadorIncorrectaException extends UserException {

	public FormulaIndicadorIncorrectaException(){
		super("Sintaxis de formula incorrecta.");
	}
}
