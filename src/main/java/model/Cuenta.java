package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

import lombok.Getter;

@Entity
@Observable
@JsonIgnoreProperties({ "changeSupport" })
public class Cuenta {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, length = 50, nullable=false)
	@Getter private String nombre = "";
	
	public Cuenta() {
		
	}

	public Cuenta(String nombre) {
		this.setNombre(nombre);
	}

	public String toString() {
		return this.getNombre();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}
}
