package exceptions;

@SuppressWarnings("serial")
public class FormulaIndicadorIncorrectaException extends RuntimeException {

	public FormulaIndicadorIncorrectaException(){
		super("Sintaxis de formula incorrecta.");
	}
}
