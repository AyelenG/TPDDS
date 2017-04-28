package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private BigDecimal valor;
	
	public Cuenta() {
		
	}
	
	public Cuenta(String nombre, BigDecimal valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public boolean esIgual(Cuenta cuenta) {
		return this.getNombre().equals(cuenta.getNombre());// && this.getValor().equals(cuenta.getValor());
	} //para mi es igual solo si el nombre coincide, 
	 //asi si se quiere agregar una cuenta con el mismo nombre pero distinto valor
	 //no las agrega varias veces,actualiza el valor
	
	@Override
	public String toString(){
		return getNombre();
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
