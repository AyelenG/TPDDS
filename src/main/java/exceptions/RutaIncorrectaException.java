package exceptions;

@SuppressWarnings("serial")
public class RutaIncorrectaException extends RuntimeException {
	
	public RutaIncorrectaException(Exception e){
		super("La ruta especificada es incorrecta, por favor ingrese una ruta válida.", e);
	}
}
