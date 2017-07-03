package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Cuenta {

	private String nombre = "";
	
	public Cuenta() {
	}

	public Cuenta(String nombre) {
		this.setNombre(nombre);
	}

	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());
	}

}
