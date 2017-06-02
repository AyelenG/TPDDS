package exceptions;

public class NoSePuedeEvaluarException extends RuntimeException {

	private String mensaje;

	public NoSePuedeEvaluarException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}
