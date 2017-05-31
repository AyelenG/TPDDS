package exceptions;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class ErrorGuardadoExcepcion extends UserException {
	
	public ErrorGuardadoExcepcion(Exception e){
		super("Error de escritura al guardar los datos. Consulte al administrador.", e);
	}
}