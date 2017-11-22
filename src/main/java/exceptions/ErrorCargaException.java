package exceptions;

@SuppressWarnings("serial")
public class ErrorCargaException extends RuntimeException {
	
	public ErrorCargaException(Exception e){
//		super(e.getMessage(),e);
		super("Error de lectura al cargar los datos. Consulte al administrador.", e);
	}
}
