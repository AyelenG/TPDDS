package model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.uqbar.commons.utils.Observable;

@Observable
@JsonIgnoreProperties({"changeSupport"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Cuenta {
	
	private String nombre = "";
	private BigDecimal valor;
	
	public Cuenta() {
		
	}
	
	public Cuenta(String nombre) {
		this.setNombre(nombre);
	}
	
	public Cuenta(String nombre, BigDecimal valor) {
		this.setNombre(nombre);
		this.setValor(valor);
	}
	
	public String toString(){
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());
	}

}
