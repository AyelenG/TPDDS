package exceptions;

@SuppressWarnings("serial")
public class ArchivoConErroresException extends RuntimeException {

	public ArchivoConErroresException(Exception e) {
		super("El archivo contiene errores en los datos, modifiquelo e intente otra vez.", e);
	}
}
