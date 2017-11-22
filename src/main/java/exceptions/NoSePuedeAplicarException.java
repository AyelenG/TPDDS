package exceptions;

@SuppressWarnings("serial")
public class NoSePuedeAplicarException extends RuntimeException {

	public NoSePuedeAplicarException(String mensaje) {
		super(mensaje);
	}
}
