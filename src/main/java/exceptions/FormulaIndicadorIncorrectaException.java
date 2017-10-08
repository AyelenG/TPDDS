package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class FormulaIndicadorIncorrectaException extends UserException {

	public FormulaIndicadorIncorrectaException(){
		super("Sintaxis de formula incorrecta.");
	}
}
