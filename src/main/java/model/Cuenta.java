package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Cuenta {

	@Id
	@GeneratedValue
	private long cuen_id;
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
