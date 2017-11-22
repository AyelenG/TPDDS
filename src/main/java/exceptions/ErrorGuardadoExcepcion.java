package exceptions;

@SuppressWarnings("serial")
public class ErrorGuardadoExcepcion extends RuntimeException {
	
	public ErrorGuardadoExcepcion(Exception e){
		super("Error de escritura al guardar los datos. Consulte al administrador.", e);
	}
}