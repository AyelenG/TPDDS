package model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.uqbar.commons.utils.Observable;

@Observable
@JsonIgnoreProperties({"changeSupport"})
public class Cuenta {
	private String nombre;
	private BigDecimal valor;
	
	public Cuenta() {
		
	}
	
	public Cuenta(String nombre, BigDecimal valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());
	}
	
	public String toString(){
		return this.nombre;
	}
	
	public Cuenta(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
